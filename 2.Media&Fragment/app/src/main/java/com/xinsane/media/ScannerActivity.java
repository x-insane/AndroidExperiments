package com.xinsane.media;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.xinsane.media.R;
import com.xinsane.util.LogUtil;

public class ScannerActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        SurfaceView surfaceView = findViewById(R.id.main_content);
        surfaceView.getHolder().setKeepScreenOn(true);
        surfaceView.getHolder().addCallback(this);
    }

    private void initCamera() {
        Camera.Parameters parameters = camera.getParameters();
//        LogUtil.d(parameters.flatten(), "CameraInfo");
        Display display = getWindowManager().getDefaultDisplay();
        parameters.setPictureSize(display.getWidth(), display.getHeight());
        parameters.setJpegThumbnailQuality(100);
        parameters.setJpegQuality(100);
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        camera.setParameters(parameters);
        camera.setDisplayOrientation(90);
        camera.startPreview();
//        camera.cancelAutoFocus();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (camera == null) {
            try {
                camera = Camera.open();
                camera.setPreviewDisplay(holder);
                initCamera();
                camera.startPreview();
            } catch (Exception e) {
                LogUtil.e("Camera error: " + e.getMessage(), "ScannerActivity/surfaceCreated");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

}
