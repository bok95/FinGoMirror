package net.togo.fingo.plugin.mirror;

import android.content.Context;
import android.hardware.Camera;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MirrorActionReceiver extends AbstractActionReceiver implements Preview.TouchListener {

    private Preview mPreview;
    Camera mCamera;
    int numberOfCameras;
    int cameraCurrentlyLocked;
    int defaultCameraId;
    private WindowManager mWM;

	@Override
	public void action1() {
        start();
        Toast.makeText(context, "action 1", Toast.LENGTH_SHORT).show();

	}

	@Override
	public void action2() {
	}

	@Override
	public void action3() {
	}

	@Override
	protected String getClassName() {
		return MirrorActivity.class.getName();
	}

    public void start() {
        cameraCurrentlyLocked = 0;
        mWM = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int size = getPreviewSize();
        mPreview = new Preview(context, size);
        mPreview.registerTouchListener(this);
        mWM.addView(mPreview, mPreview.getLayoutParams());

        numberOfCameras = Camera.getNumberOfCameras();

        // Find the ID of the default camera
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                defaultCameraId = i;
            }
        }

        mCamera = Camera.open((cameraCurrentlyLocked + 1) % numberOfCameras);
        cameraCurrentlyLocked = (cameraCurrentlyLocked + 1) % numberOfCameras;
        mPreview.setCamera(mCamera);

    }

    private int getPreviewSize() {
        DisplayMetrics displaymetrics = new DisplayMetrics();

        mWM.getDefaultDisplay().getMetrics(displaymetrics);
        int w = displaymetrics.widthPixels;
        return w;
    }

    public void stop() {
        if (mCamera != null) {
            mPreview.unregisterTouchListener();
            mPreview.setCamera(null);
            mCamera.release();
            mCamera = null;
            mWM.removeView(mPreview);
            mPreview = null;
        }
    }


    public Context getContext() {
        return context;
    }


    @Override
    public void onTouch() {
        stop();
    }
}
