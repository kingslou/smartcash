package com.pos.posscan.activity;

import android.app.Activity;
import android.bluetooth.BluetoothClass;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pos.posscan.AppConfig;
import com.pos.posscan.R;
import com.pos.posscan.api.RestDataSource;
import com.pos.posscan.bean.PosPayNotifyFeed;
import com.pos.posscan.bean.PosPayNotifyPoJo;
import com.pos.posscan.bean.PrePayBean;
import com.pos.posscan.bean.PrePayFeed;
import com.pos.posscan.utils.DateUtil;
import com.pos.posscan.utils.FunctionXinDaLu;


import java.util.Date;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

import static com.pos.posscan.utils.FunctionXinDaLu.XINDALUREQUESTCODE;

public class MainActivity extends AppCompatActivity {

    private int operation = 1;
    private static final int WXSCANNERCODE = 1001;
    private static final int ZFBSCANNERCODE = 1002;
    private static final int YHKSCANNERCODE = 1003;
    private PrePayBean currentPrePayBean;
    private String currentPayType;
    private String currentOrderCode;
    private int currentTransType;

    private static final String YHKPAYTYPE = "0";
    private static final String WXPAYTYPE = "1";
    private static final String ZFBPAYTYPE = "2";
    private static final String YLMPAYTYPE = "3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.item_yhk)
    public void onClickXianJinBtn() {
        FunctionXinDaLu functionXinDaLu = new FunctionXinDaLu(MainActivity.this);
        functionXinDaLu.bankCardPay(0.1f,"");
//        Intent intent = new Intent(MainActivity.this, ScanActivity.class);
//        startActivityForResult(intent, YHKSCANNERCODE);
    }

    @OnClick(R.id.item_zfb)
    public void onClickAliPayBtn() {
        Intent intent = new Intent(MainActivity.this, ScanActivity.class);
        startActivityForResult(intent, ZFBSCANNERCODE);
    }

    @OnClick(R.id.item_wx)
    public void onClickWeiChatPayBtn() {
        Intent intent = new Intent(MainActivity.this, ScanActivity.class);
        startActivityForResult(intent, WXSCANNERCODE);
    }

    private void notifyPc(Bundle bundle){
        String transInfo = "";
        if(bundle!=null){
            transInfo = bundle.getString("txndetail");
        }
        PosPayNotifyPoJo posPayNotifyPoJo = new PosPayNotifyPoJo(currentPrePayBean.getBizGuid());
        posPayNotifyPoJo.setDevice(android.os.Build.MODEL);
        posPayNotifyPoJo.setNotifyTimeEnd(DateUtil.getDate(new Date()));
        posPayNotifyPoJo.setPayType(currentPayType);
        posPayNotifyPoJo.setPreKey(AppConfig.HEADERX);
        posPayNotifyPoJo.setNotifyTransactionId(AppConfig.HEADERX);
        posPayNotifyPoJo.setTotalAmountPaid(currentPrePayBean.getTotalAmountDue());
        posPayNotifyPoJo.setNotifyResultCode("SUCCESS");
        posPayNotifyPoJo.setPosPayDetail(transInfo);
        posPayNotifyPoJo.setFailedReason("");

        RestDataSource.notify(currentPrePayBean,posPayNotifyPoJo, new Observer<PosPayNotifyFeed>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("eee", e.toString());
            }

            @Override
            public void onNext(PosPayNotifyFeed posPayNotifyFeed) {
                if (posPayNotifyFeed != null) {
                    if(posPayNotifyFeed.isSuccess()){
                        Toast.makeText(MainActivity.this, "通知成功", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MainActivity.this, "通知失败"+posPayNotifyFeed.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case XINDALUREQUESTCODE:
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        String msgTp = bundle.getString("msg_tp");
                        if (TextUtils.equals(msgTp, "0210")) {
                            switch (operation) {
                                case 1:
                                    Toast.makeText(this, "交易成功", Toast.LENGTH_LONG).show();
                                    //通知客户端更新字段
                                    notifyPc(bundle);
                                    break;
                                case 0:
                                    Toast.makeText(this, "撤销成功", Toast.LENGTH_LONG).show();
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    break;
                case WXSCANNERCODE:
                    if (null == data) {
                        return;
                    }
                    AppConfig.HEADERX = data.getStringExtra("data");
                    RestDataSource.scanRequest(new Observer<PrePayFeed>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("eee", e.toString());
                            Toast.makeText(MainActivity.this, "请求失败" + e.toString(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onNext(PrePayFeed prePayFeed) {
                            try {
                                if (prePayFeed != null) {
                                    PrePayBean prePayBean = new Gson().fromJson(prePayFeed.getResult(), PrePayBean.class);
                                    currentPrePayBean = prePayBean;
                                    currentPayType = WXPAYTYPE;
                                    AppConfig.NOTIFYURL = prePayBean.getCbUrl();
                                    FunctionXinDaLu functionXinDaLu = new FunctionXinDaLu(MainActivity.this);
                                    functionXinDaLu.weixinPay(Float.parseFloat(prePayBean.getTotalAmountDue()), prePayBean.getBizGuid());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    break;
                case ZFBSCANNERCODE:
                    if (null == data) {
                        return;
                    }
                    AppConfig.HEADERX = data.getStringExtra("data");
                    RestDataSource.scanRequest(new Observer<PrePayFeed>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("eee", e.toString());
                            Toast.makeText(MainActivity.this, "请求失败" + e.toString(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onNext(PrePayFeed prePayFeed) {
                            try {
                                if (prePayFeed != null) {
                                    PrePayBean prePayBean = new Gson().fromJson(prePayFeed.getResult(), PrePayBean.class);
                                    currentPrePayBean = prePayBean;
                                    currentPayType = ZFBPAYTYPE;
                                    AppConfig.NOTIFYURL = prePayBean.getCbUrl();
                                    FunctionXinDaLu functionXinDaLu = new FunctionXinDaLu(MainActivity.this);
                                    functionXinDaLu.aliPay(Float.parseFloat(prePayBean.getTotalAmountDue()), prePayBean.getBizGuid()+"2");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                onError(e);
                            }

                        }
                    });
                    break;

                case YHKSCANNERCODE:
                    if (null == data) {
                        return;
                    }
                    AppConfig.HEADERX = data.getStringExtra("data");
                    RestDataSource.scanRequest(new Observer<PrePayFeed>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("eee", e.toString());
                            Toast.makeText(MainActivity.this, "请求失败" + e.toString(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onNext(PrePayFeed prePayFeed) {
                            try {
                                if (prePayFeed != null) {
                                    currentPayType = YHKPAYTYPE;
                                    PrePayBean prePayBean = new Gson().fromJson(prePayFeed.getResult(), PrePayBean.class);
                                    currentPrePayBean = prePayBean;
                                    AppConfig.NOTIFYURL = prePayBean.getCbUrl();
                                    FunctionXinDaLu functionXinDaLu = new FunctionXinDaLu(MainActivity.this);
                                    functionXinDaLu.bankCardPay(Float.parseFloat(prePayBean.getTotalAmountDue()), "");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                onError(e);
                            }
                        }
                    });
                    break;
                default:
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
