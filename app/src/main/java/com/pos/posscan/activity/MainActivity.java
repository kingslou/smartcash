package com.pos.posscan.activity;

import android.app.Activity;
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
import com.pos.posscan.bean.PrePayBean;
import com.pos.posscan.bean.PrePayFeed;
import com.pos.posscan.utils.Arith;
import com.pos.posscan.utils.FunctionXinDaLu;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

import static com.pos.posscan.utils.FunctionXinDaLu.XINDALUREQUESTCODE;

public class MainActivity extends AppCompatActivity {

    private int operation = 1;
    private static final int WXSCANNERCODE = 1001;
    private static final int ZFBSCANNERCODE = 1002;
    private static final int YHKSCANNERCODE = 1003;
    private PrePayBean prePayBean;

    private String currentOrderCode;
    private int currentTransType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ZXingLibrary.initDisplayOpinion(this);
    }

    @OnClick(R.id.item_yhk)
    public void onClickXianJinBtn() {
//        Intent intent = new Intent(this, ScannerActivity.class);
//        startActivityForResult(intent, YHKSCANNERCODE);

//        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
//        startActivityForResult(intent, YHKSCANNERCODE);

        AppConfig.HEADERX = "A8888888888888888888888888888888Z";
        RestDataSource.scanRequest(new Observer<PrePayFeed>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("eee", e.toString());
            }

            @Override
            public void onNext(PrePayFeed prePayFeed) {
                if (prePayFeed != null) {
                    String payStr = prePayFeed.getResult();
                    prePayBean = new Gson().fromJson(payStr,PrePayBean.class);
                    AppConfig.NOTIFYURL = prePayBean.getCbUrl();
                    RestDataSource.notify(prePayBean, new Observer<PosPayNotifyFeed>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("eee", e.toString());
                        }

                        @Override
                        public void onNext(PosPayNotifyFeed posPayNotifyFeed) {
                            if(posPayNotifyFeed!=null){

                            }
                        }
                    });
                }
            }
        });
    }

    @OnClick(R.id.item_zfb)
    public void onClickAliPayBtn() {
//        Intent intent = new Intent(this, ScannerActivity.class);
//        startActivityForResult(intent, ZFBSCANNERCODE);

        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
        startActivityForResult(intent, ZFBSCANNERCODE);
    }

    @OnClick(R.id.item_wx)
    public void onClickWeiChatPayBtn() {
//        Intent intent = new Intent(this, ScannerActivity.class);
//        startActivityForResult(intent, WXSCANNERCODE);

        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
        startActivityForResult(intent, WXSCANNERCODE);
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
                    if (data.getExtras().getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                        String result = data.getExtras().getString(CodeUtils.RESULT_STRING);
                        AppConfig.HEADERX = result;
                        RestDataSource.scanRequest(new Observer<PrePayFeed>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("eee", e.toString());
                            }

                            @Override
                            public void onNext(PrePayFeed prePayFeed) {
                                if (prePayFeed != null) {

                                }
                            }
                        });
                    }
                    break;
                case ZFBSCANNERCODE:
                    if (null == data) {
                        return;
                    }
                    if (data.getExtras().getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                        String result = data.getExtras().getString(CodeUtils.RESULT_STRING);
                        AppConfig.HEADERX = " A8888888888888888888888888888888Z";
                        RestDataSource.scanRequest(new Observer<PrePayFeed>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("eee", e.toString());
                            }

                            @Override
                            public void onNext(PrePayFeed prePayFeed) {
                                if (prePayFeed != null) {

                                }
                            }
                        });
                    }

                    break;

                case YHKSCANNERCODE:
                    if (null == data) {
                        return;
                    }
                    if (data.getExtras().getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                        String result = data.getExtras().getString(CodeUtils.RESULT_STRING);
                        AppConfig.HEADERX = result;
                        RestDataSource.scanRequest(new Observer<PrePayFeed>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("eee", e.toString());
                            }

                            @Override
                            public void onNext(PrePayFeed prePayFeed) {
                                if (prePayFeed != null) {

                                }
                            }
                        });
                    }
                    break;
                default:
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
