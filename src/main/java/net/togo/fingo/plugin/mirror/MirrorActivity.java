package net.togo.fingo.plugin.mirror;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.Window;
//import android.view.WindowManager;

//public class MirrorAction extends Activity {
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//		setContentView(R.layout.activity_memo_action);
//		finish();
//
//	}
//}


import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class MirrorActivity extends Activity {

	private Preview mPreview;
	Camera mCamera;
	int numberOfCameras;
	int cameraCurrentlyLocked;
	private Context context;

	// The first rear facing camera
	int defaultCameraId;
	private WindowManager mWM;

	final private String resource = "mirror";
	private WindowManager window;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getApplicationContext();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.activity_mirror);
        finish();
//		window = (WindowManager) context
//				.getSystemService(Context.WINDOW_SERVICE);
//		start();
	}

//	public void create() {
//	}
//
//	public void delete() {
//		mWM.removeView(mPreview);
//	}
//
//	public void start() {
//		cameraCurrentlyLocked = 0;
//		mWM = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//		int size = getPreviewSize();
//		mPreview = new Preview(this, size);
//		mWM.addView(mPreview, mPreview.getLayoutParams());
//
//		numberOfCameras = Camera.getNumberOfCameras();
//
//		// Find the ID of the default camera
//		CameraInfo cameraInfo = new CameraInfo();
//		for (int i = 0; i < numberOfCameras; i++) {
//			Camera.getCameraInfo(i, cameraInfo);
//			if (cameraInfo.facing == CameraInfo.CAMERA_FACING_BACK) {
//				defaultCameraId = i;
//			}
//		}
//
//		mCamera = Camera.open((cameraCurrentlyLocked + 1) % numberOfCameras);
//		cameraCurrentlyLocked = (cameraCurrentlyLocked + 1) % numberOfCameras;
//		mPreview.setCamera(mCamera);
//
//	}
//
//	private int getPreviewSize() {
//		DisplayMetrics displaymetrics = new DisplayMetrics();
//
//		mWM.getDefaultDisplay().getMetrics(displaymetrics);
//		int w = displaymetrics.widthPixels;
//		return w;
//	}
//
//	public void stop() {
//		if (mCamera != null) {
//			mPreview.setCamera(null);
//			mCamera.release();
//			mCamera = null;
//			mWM.removeView(mPreview);
//		}
//		finish();
//	}
//
//	public Context getContext() {
//		return context;
//	}
//}
//
//// ----------------------------------------------------------------------
//
///**
// * A simple wrapper around a Camera and a SurfaceView that renders a centered
// * preview of the Camera to the surface. We need to center the SurfaceView
// * because not all devices have cameras that support preview sizes at the same
// * aspect ratio as the device's display.
// */
//class Preview extends ViewGroup implements SurfaceHolder.Callback,
//		OnTouchListener {
//	final public static String LOGTAG = "MirrorAction";
//
//	private final String TAG = "Preview";
//
//	SurfaceView mSurfaceView;
//	SurfaceHolder mHolder;
//	Size mPreviewSize;
//	List<Size> mSupportedPreviewSizes;
//	Camera mCamera;
//	MirrorActivity mirrorAction;
//
//	Preview(MirrorActivity ma, int length) {
//
//		super(ma.getContext());
//		mirrorAction = ma;
//
//		WindowManager.LayoutParams layoutParams;
//
//		layoutParams = new WindowManager.LayoutParams(length, length,
//				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
//				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//						| WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
//						| WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
//				PixelFormat.TRANSLUCENT);
//		mSurfaceView = new SurfaceView(mirrorAction.getContext());
//		addView(mSurfaceView);
//		this.setLayoutParams(layoutParams);
//		setOnTouchListener(this);
//		// Install a SurfaceHolder.Callback so we get notified when the
//		// underlying surface is created and destroyed.
//		mHolder = mSurfaceView.getHolder();
//		mHolder.addCallback(this);
//	}
//
//	public void setCamera(Camera camera) {
//		mCamera = camera;
//		if (mCamera != null) {
//			Camera.Parameters parameters = mCamera.getParameters();
//			mSupportedPreviewSizes = parameters.getSupportedPreviewSizes();
//			parameters.set("orientation", "portrait");
//			mCamera.setDisplayOrientation(90);
//			requestLayout();
//		}
//	}
//
//	public void switchCamera(Camera camera) {
//		setCamera(camera);
//		try {
//			camera.setPreviewDisplay(mHolder);
//		} catch (IOException exception) {
//			Log.e(TAG, "IOException caused by setPreviewDisplay()", exception);
//		}
//		Camera.Parameters parameters = camera.getParameters();
//		parameters.setPreviewSize(mPreviewSize.width, mPreviewSize.height);
//		requestLayout();
//
//		camera.setParameters(parameters);
//	}
//
//	@Override
//	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		// We purposely disregard child measurements because act as a
//		// wrapper to a SurfaceView that centers the camera preview instead
//		// of stretching it.
//		final int width = resolveSize(getSuggestedMinimumWidth(),
//				widthMeasureSpec);
//		final int height = resolveSize(getSuggestedMinimumHeight(),
//				heightMeasureSpec);
//		setMeasuredDimension(width, height);
//
//		if (mSupportedPreviewSizes != null) {
//			mPreviewSize = getOptimalPreviewSize(mSupportedPreviewSizes, width,
//					height);
//		}
//	}
//
//	@Override
//	protected void onLayout(boolean changed, int l, int t, int r, int b) {
//		if (changed && getChildCount() > 0) {
//			final View child = getChildAt(0);
//
//			final int width = r - l;
//			final int height = b - t;
//
//			int previewWidth = width;
//			int previewHeight = height;
//			if (mPreviewSize != null) {
//				previewWidth = mPreviewSize.width;
//				previewHeight = mPreviewSize.height;
//			}
//
//			// Center the child SurfaceView within the parent.
//			if (width * previewHeight > height * previewWidth) {
//				final int scaledChildWidth = previewWidth * height
//						/ previewHeight;
//				child.layout((width - scaledChildWidth) / 2, 0,
//						(width + scaledChildWidth) / 2, height);
//			} else {
//				final int scaledChildHeight = previewHeight * width
//						/ previewWidth;
//				child.layout(0, (height - scaledChildHeight) / 2, width,
//						(height + scaledChildHeight) / 2);
//			}
//		}
//	}
//
//	public void surfaceCreated(SurfaceHolder holder) {
//		// The Surface has been created, acquire the camera and tell it where
//		// to draw.
//		try {
//			if (mCamera != null) {
//				mCamera.setPreviewDisplay(holder);
//			}
//		} catch (IOException exception) {
//			Log.e(TAG, "IOException caused by setPreviewDisplay()", exception);
//		}
//	}
//
//	public void surfaceDestroyed(SurfaceHolder holder) {
//		// Surface will be destroyed when we return, so stop the preview.
//		if (mCamera != null) {
//			mCamera.stopPreview();
//
//		}
//	}
//
//	private Size getOptimalPreviewSize(List<Size> sizes, int w, int h) {
//		final double ASPECT_TOLERANCE = 0.1;
//		double targetRatio = (double) w / h;
//		if (sizes == null)
//			return null;
//
//		Size optimalSize = null;
//		double minDiff = Double.MAX_VALUE;
//
//		int targetHeight = h;
//
//		// Try to find an size match aspect ratio and size
//		for (Size size : sizes) {
//			double ratio = (double) size.width / size.height;
//			if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
//				continue;
//			if (Math.abs(size.height - targetHeight) < minDiff) {
//				optimalSize = size;
//				minDiff = Math.abs(size.height - targetHeight);
//			}
//		}
//
//		// Cannot find the one match the aspect ratio, ignore the requirement
//		if (optimalSize == null) {
//			minDiff = Double.MAX_VALUE;
//			for (Size size : sizes) {
//				if (Math.abs(size.height - targetHeight) < minDiff) {
//					optimalSize = size;
//					minDiff = Math.abs(size.height - targetHeight);
//				}
//			}
//		}
//		return optimalSize;
//	}
//
//	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
//		// Now that the size is known, set up the camera parameters and begin
//		// the preview.
//		Camera.Parameters parameters = mCamera.getParameters();
//		parameters.setPreviewSize(mPreviewSize.width, mPreviewSize.height);
//		requestLayout();
//
//		mCamera.setParameters(parameters);
//		mCamera.startPreview();
//		// switchCamera(mCamera);
//	}
//
//	public boolean onTouch(View v, MotionEvent event) {
//		int action = event.getAction();
//		float x = event.getX();
//		float y = event.getY();
//
//		if (event.getAction() == MotionEvent.ACTION_UP) {
//			mirrorAction.stop();
//		}
//		return true;
//		// return false;
//	}
}