package com.pos.posscan.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.Toast;
import com.pos.posscan.App;
import java.util.Date;


/**
 * 新大陆设备打印以及其他功能
 *
 * @author loujin
 * @date 2017-11-22
 * Created by Administrator on 2017/11/22.
 */

public class FunctionXinDaLu {
    private Activity mActivity;
    public static final int XINDALUREQUESTCODE = 8001;
    public static final int SYSTEMSCANREQUESTCODE = 9001; //扫描系统的二维码
    /*
     *系统二维码结构
     *  { "ordercode":2001,"orderMoney":80}
     *
     */

    private static FunctionXinDaLu instance;

    private FunctionXinDaLu(){
        mActivity = (Activity) App.getContext();
    }

    public static FunctionXinDaLu getInstance() {
        if (instance == null) {
            synchronized (FunctionXinDaLu.class) {
                if (instance == null) {
                    instance = new FunctionXinDaLu();
                }
            }
        }
        return  instance;
    }


    public void cancelTrans(String transCode) {
        try {
            ComponentName component = new ComponentName("com.newland.caishen", "com.newland.caishen.ui.activity.MainActivity");
            Intent intent = new Intent();
            intent.setComponent(component);

            Bundle bundle = new Bundle();
            bundle.putString("msg_tp", "0200");
            bundle.putString("pay_tp", "0");
            bundle.putString("proc_tp", "00");
            bundle.putString("proc_cd", "200000");
            bundle.putString("amt", "");
            bundle.putString("order_no", transCode);
            bundle.putString("appid", "com.nld.trafficmanage");
            bundle.putString("time_stamp", DateUtil.getDate(new Date()));
            bundle.putString("print_info", "这是我的测试");
            intent.putExtras(bundle);
            mActivity.startActivityForResult(intent, XINDALUREQUESTCODE);
        } catch (ActivityNotFoundException e) {
            //TODO:
        } catch (Exception e) {
            //TODO:
        }
    }

    public void cancelTransWeiXin(String transCode) {
        try {
            ComponentName component = new ComponentName("com.newland.caishen", "com.newland.caishen.ui.activity.MainActivity");
            Intent intent = new Intent();
            intent.setComponent(component);

            Bundle bundle = new Bundle();
            bundle.putString("msg_tp", "0200");
            bundle.putString("pay_tp", "0");
            bundle.putString("proc_tp", "00");
            bundle.putString("proc_cd", "680000");
            bundle.putString("appid", "com.nld.trafficmanage");
            bundle.putString("time_stamp", DateUtil.getDate(new Date()));
            bundle.putString("print_info", "这是我的测试");
            intent.putExtras(bundle);
            mActivity.startActivityForResult(intent, XINDALUREQUESTCODE);
        } catch (ActivityNotFoundException e) {
            //TODO:
        } catch (Exception e) {
            //TODO:
        }
    }

    public void cancelTransAliPay(String transCode) {
        try {
            ComponentName component = new ComponentName("com.newland.caishen", "com.newland.caishen.ui.activity.MainActivity");
            Intent intent = new Intent();
            intent.setComponent(component);

            Bundle bundle = new Bundle();
            bundle.putString("msg_tp", "0200");
            bundle.putString("pay_tp", "0");
            bundle.putString("proc_tp", "00");
            bundle.putString("proc_cd", "680000");
            bundle.putString("appid", "com.nld.trafficmanage");
            bundle.putString("time_stamp", DateUtil.getDate(new Date()));
            bundle.putString("print_info", "这是我的测试");
            intent.putExtras(bundle);
            mActivity.startActivityForResult(intent, XINDALUREQUESTCODE);
        } catch (ActivityNotFoundException e) {
            //TODO:
        } catch (Exception e) {
            //TODO:
        }
    }



    public void bankCardPay(float money, String jiaoYiUuid) {
        try {
            ComponentName component = new ComponentName("com.newland.caishen", "com.newland.caishen.ui.activity.MainActivity");
            Intent intent = new Intent();
            intent.setComponent(component);

            Bundle bundle = new Bundle();
            bundle.putString("msg_tp", "0200");
            bundle.putString("pay_tp", "0");
            bundle.putString("proc_tp", "00");
            bundle.putString("proc_cd", "000000");
            bundle.putString("amt", Arith.getDecimalString(money));
            bundle.putString("order_no", jiaoYiUuid);
            bundle.putString("appid", "com.nld.trafficmanage");
            bundle.putString("time_stamp", DateUtil.getDate(new Date()));
            bundle.putString("print_info", "订单商品明细单价等xxxxxx");
            intent.putExtras(bundle);
            mActivity.startActivityForResult(intent, XINDALUREQUESTCODE);
        } catch (ActivityNotFoundException e) {
            //TODO:
            Toast.makeText(mActivity, "当前设备没有新大陆支付环境", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            //TODO:
        }
    }

    public void weixinPay(float money,String orderCode) {
        try {
            ComponentName component = new ComponentName("com.newland.caishen", "com.newland.caishen.ui.activity.MainActivity");
            Intent intent = new Intent();
            intent.setComponent(component);

            Bundle bundle = new Bundle();
            bundle.putString("msg_tp", "0200");
            bundle.putString("pay_tp", "1");
            bundle.putString("proc_tp", "00");
            bundle.putString("proc_cd", "660000");
            bundle.putString("amt", Arith.getDecimalString(money));
            bundle.putString("order_no", orderCode);
            bundle.putString("appid", "com.nld.trafficmanage");
            bundle.putString("time_stamp", DateUtil.getDate(new Date()));
            bundle.putString("print_info", "订单商品明细单价等xxxxxx");
            intent.putExtras(bundle);
            mActivity.startActivityForResult(intent, XINDALUREQUESTCODE);
        } catch (ActivityNotFoundException e) {
            //TODO:
            Toast.makeText(mActivity, "当前设备没有新大陆支付环境", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            //TODO:
        }
    }

    public void aliPay(float money,String orderCode) {
        try {
            ComponentName component = new ComponentName("com.newland.caishen", "com.newland.caishen.ui.activity.MainActivity");
            Intent intent = new Intent();
            intent.setComponent(component);

            Bundle bundle = new Bundle();
            bundle.putString("msg_tp", "0200");
            bundle.putString("pay_tp", "1");
            bundle.putString("proc_tp", "00");
            bundle.putString("proc_cd", "660000");
            bundle.putString("amt", Arith.getDecimalString(money));
            bundle.putString("order_no", orderCode);
            bundle.putString("appid", "com.nld.trafficmanage");
            bundle.putString("time_stamp", DateUtil.getDate(new Date()));
            bundle.putString("print_info", "订单商品明细单价等xxxxxx");
            intent.putExtras(bundle);
            mActivity.startActivityForResult(intent, XINDALUREQUESTCODE);
        } catch (ActivityNotFoundException e) {
            //TODO:
            Toast.makeText(mActivity, "当前设备没有新大陆支付环境", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            //TODO:
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data, int operation) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case XINDALUREQUESTCODE:
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        String msgTp = bundle.getString("msg_tp");
                        if (TextUtils.equals(msgTp, "0210")) {
                            switch (operation) {
                                case 1:
                                    Toast.makeText(mActivity, "交易成功", Toast.LENGTH_LONG).show();
                                    break;
                                case 0:
                                    Toast.makeText(mActivity, "撤销成功", Toast.LENGTH_LONG).show();
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

}
