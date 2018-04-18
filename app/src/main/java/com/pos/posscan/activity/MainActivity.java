package com.pos.posscan.activity;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.pos.posscan.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import static com.pos.posscan.utils.FunctionXinDaLu.XINDALUREQUESTCODE;

public class MainActivity extends AppCompatActivity {

    private int operation = 0;
    private static final int SCANNERCODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.item_yhk)
    public void onClickXianJinBtn() {
        Intent intent = new Intent(this,ScannerActivity.class);
        startActivityForResult(intent,SCANNERCODE);
    }

    @OnClick(R.id.item_zfb)
    public void onClickAliPayBtn() {
        Intent intent = new Intent(this,ScannerActivity.class);
        startActivityForResult(intent,SCANNERCODE);
    }

    @OnClick(R.id.item_wx)
    public void onClickWeiChatPayBtn() {
        Intent intent = new Intent(this,ScannerActivity.class);
        startActivityForResult(intent,SCANNERCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case  XINDALUREQUESTCODE:
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
                case SCANNERCODE:
                    if (null == data) {
                        return;
                    }
                    String datastr = data.getStringExtra("data");
                    Toast.makeText(this, "扫码成功"+datastr, Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
