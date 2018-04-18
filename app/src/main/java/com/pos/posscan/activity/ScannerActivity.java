package com.pos.posscan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.pos.posscan.R;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * 二维码扫描
 * Created by jin on 2016.05.12.
 */
public class ScannerActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler {
    private ZBarScannerView mScannerView;
    @BindView(R.id.content_frame)
    ViewGroup contentFrame;
    @BindView(R.id.btn_toolbar_back)
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_layout);
        ButterKnife.bind(this);
        mScannerView = new ZBarScannerView(this);
        mScannerView.setAutoFocus(true);
        contentFrame.addView(mScannerView);
    }

    @OnClick(R.id.btn_toolbar_back)
    void gotoBack() {
        finish();
    }

    @Override
    public void handleResult(Result rawResult) {
        // Note:
        // * Wait 2 seconds to resume the preview.
        // * On older devices continuously stopping and resuming camera preview can result in freezing the app.
        // * I don't know why this is the case but I don't have the time to figure out.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (null != mScannerView) {
                    mScannerView.resumeCameraPreview(ScannerActivity.this);
                }

            }
        }, 2000);

        Intent intent = new Intent();
        String datastr = "";
        datastr = rawResult.getContents();
        try {
            datastr = URLDecoder.decode(datastr, "UTF-8");
            intent.putExtra("data", datastr);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mScannerView = null;
    }
}
