package com.pos.posscan.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.pos.posscan.R;
import com.pos.posscan.utils.Arith;
import com.pos.posscan.utils.FunctionXinDaLu;

import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pos.posscan.utils.FunctionXinDaLu.XINDALUREQUESTCODE;

public class MainActivity extends AppCompatActivity {

    private int operation = 1;
    private static final int WXSCANNERCODE = 1001;
    private static final int ZFBSCANNERCODE = 1002;
    private static final int YHKSCANNERCODE = 1003;

    private String currentOrderCode;
    private int currentTransType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.item_yhk)
    public void onClickXianJinBtn() {
        Intent intent = new Intent(this, ScannerActivity.class);
        startActivityForResult(intent, YHKSCANNERCODE);
    }

    @OnClick(R.id.item_zfb)
    public void onClickAliPayBtn() {
        Intent intent = new Intent(this, ScannerActivity.class);
        startActivityForResult(intent, ZFBSCANNERCODE);
    }

    @OnClick(R.id.item_wx)
    public void onClickWeiChatPayBtn() {
        Intent intent = new Intent(this, ScannerActivity.class);
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
                    String datastr = data.getStringExtra("data");
                    try {
                        JSONObject item = new JSONObject(datastr);
                        String orderCode = item.getString("orderCode");
                        String money = item.getString("money");
                        if (!TextUtils.isEmpty(orderCode) && !TextUtils.isEmpty(money)) {
                            FunctionXinDaLu.getInstance().weixinPay(Float.parseFloat(money), orderCode);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case ZFBSCANNERCODE:
                    if (null == data) {
                        return;
                    }
                    String datastr1 = data.getStringExtra("data");
                    try {
                        JSONObject item = new JSONObject(datastr1);
                        String orderCode = item.getString("orderCode");
                        String money = item.getString("money");
                        if (!TextUtils.isEmpty(orderCode) && !TextUtils.isEmpty(money)) {
                            FunctionXinDaLu.getInstance().aliPay(Float.parseFloat(money), orderCode);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case YHKSCANNERCODE:
                    if (null == data) {
                        return;
                    }
                    String datastr2 = data.getStringExtra("data");
                    try {
                        JSONObject item = new JSONObject(datastr2);
                        String orderCode = item.getString("orderCode");
                        String money = item.getString("money");
                        if (!TextUtils.isEmpty(orderCode) && !TextUtils.isEmpty(money)) {
                            FunctionXinDaLu.getInstance().bankCardPay(Float.parseFloat(money), orderCode);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
