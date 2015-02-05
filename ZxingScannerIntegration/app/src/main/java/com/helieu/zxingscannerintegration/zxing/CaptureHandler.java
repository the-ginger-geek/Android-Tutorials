package com.helieu.zxingscannerintegration.zxing;

import android.os.Handler;
import android.os.Message;

import com.helieu.zxingscannerintegration.R;

/**
 */
public class CaptureHandler extends Handler {
    public static final String DECODED_DATA = "decoded_data";
    private CameraManager cameraManager;
    private OnDecodedCallback callback;

    public CaptureHandler(CameraManager cameraManager, OnDecodedCallback callback) {
        this.cameraManager = cameraManager;
        this.callback = callback;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case R.id.decoded:
                String data = msg.getData().getString(DECODED_DATA);

                if (callback != null){
                    callback.onDecoded(data);
                }
                break;
            case R.id.decode_failed:
                cameraManager.requestNextFrame(new PreviewCallback(this, cameraManager));
                break;
        }
    }

    public static interface OnDecodedCallback {
        void onDecoded(String decodedData);
    }
}
