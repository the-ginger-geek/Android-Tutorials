package com.helieu.zxingscannerintegration.zxing;

import android.app.Activity;
import android.graphics.Rect;
import android.hardware.Camera;
import android.view.Surface;

import com.google.zxing.PlanarYUVLuminanceSource;

/**
 * Camera manager
 */
public class CameraManager {

    /**
     * Fraction of bounds size in view
     */
    private static final double BOUNDS_FRACTION = 0.6f;
    /**
     * Fraction of height of bounds in view
     */
    private static final double VERTICAL_HEIGHT_FRACTION = 0.3f;

	/**
	 * Camera instance
	 */
	private Camera camera;
	/**
	 * Id of camera instance
	 */
	private int cameraId;
	/**
	 * Current orientation of camera
	 * Possible values : 0, 90, 180, 270
	 */
	private int orientation;

	public CameraManager() {
		this.camera = getCameraInstance();
	}

	/**
	 * Getter for camera
	 *
	 * @return camera instance, if it has been initialized
	 */
	public Camera getCamera() {
		return camera;
	}

	/**
	 * Starts preview of camera, if it has been initialized
	 */
	public synchronized void startPreview() {
		if (camera != null) {
			camera.startPreview();
		}
	}

	/**
	 * Stops preview of camera, if it has been initialized
	 */
	public synchronized void stopPreview() {
		if (camera != null) {
			camera.stopPreview();
		}
	}

	/**
	 * Release camera, if it has been initialized
	 */
	public synchronized void release() {
		if (camera != null) {
			camera.stopPreview();
			camera.release();
			camera = null;
		}
	}

	/**
	 * @return if camera has been initialized<br/>( <code>camera != null</code> )
	 */
	public synchronized boolean hasCamera() {
		return camera != null;
	}

	/**
	 * @return bounding rect for ui
	 */
	public final synchronized Rect getBoundingRectUi(int uiWidth, int uiHeight) {
		double heightFraction = BOUNDS_FRACTION;
		double widthFraction = BOUNDS_FRACTION;
		if (orientation == 90 || orientation == 270) {
			heightFraction = VERTICAL_HEIGHT_FRACTION;
		}

		int height = (int) (uiHeight * heightFraction);
		int width = (int) (uiWidth * widthFraction);
		int left = (int) (uiWidth * ((1 - widthFraction) / 2));
		int top = (int) (uiHeight * ((1 - heightFraction) / 2));
		int right = left + width;
		int bottom = top + height;

		return new Rect(left, top, right, bottom);
	}

	/**
	 * @return bounding rect for camera
	 */
	public final synchronized Rect getBoundingRect() {
		try {
			if (camera != null) {
				Camera.Size previewSize = camera.getParameters().getPreviewSize();
				int previewHeight = previewSize.height;
				int previewWidth = previewSize.width;
	
				double heightFraction = VERTICAL_HEIGHT_FRACTION;
				double widthFraction = BOUNDS_FRACTION;
	
				int height = (int) (previewHeight * heightFraction);
				int width = (int) (previewWidth * widthFraction);
				int left = (int) (previewWidth * ((1 - widthFraction) / 2));
				int top = (int) (previewHeight * ((1 - heightFraction) / 2));
				int right = left + width;
				int bottom = top + height;
	
				return new Rect(top, left, bottom, right);
			}
		} catch (Exception e) {
			
		}
		return null;
	}

	/**
	 * executes <br/> <code>camera.setOneShotPreviewCallback(callback)</code> if <br/>
	 * <code>camera != null</code>
	 * @param callback callback to provide
	 */
	public synchronized void requestNextFrame(Camera.PreviewCallback callback) {
    	try {
			if (camera != null) {
				camera.setOneShotPreviewCallback(callback);
			}
    	} catch (Exception e ) {
    		
    	}
	}

	/**
	 * A factory method to build the appropriate LuminanceSource object based on the format
	 * of the preview buffers, as described by Camera.Parameters.
	 *
	 * @param data   A preview frame.
	 * @param width  The width of the image.
	 * @param height The height of the image.
	 * @return A PlanarYUVLuminanceSource instance.
	 */
	public synchronized PlanarYUVLuminanceSource buildLuminanceSource(byte[] data, int width, int height, Rect boundingRect) {
		if (boundingRect != null) {
			switch (orientation) {
			case 0:
				//data = flip(data);
				break;
			case 90:
				rotate90(data, width, height);
				return new PlanarYUVLuminanceSource(data, height, width, boundingRect.top, boundingRect.left,
						boundingRect.height(), boundingRect.width(), false);
	
			case 180:
				break;
			case 270:
				rotate90(data, width, height);
				break;
			}
	
			return new PlanarYUVLuminanceSource(data, width, height, boundingRect.top, boundingRect.left,
					boundingRect.height(), boundingRect.width(), false);
		} else {
			return null;
		}
	}

	/**
	 * Rotates image data
	 * @param data raw image data
	 * @param width width of image
	 * @param height height of image
	 */
	public void rotate90(byte[] data, int width, int height) {
		int length = height * width;
		int lengthDec = length - 1;
		int i = 0;
		do {
			int k = (i * height) % lengthDec;
			while (k > i) k = (height * k) % lengthDec;
			if (k != i) swap(data, k, i);
		} while (++i <= (length - 2));
	}

	/**
	 * Sets camera display orientation depending on current activity orientation
	 * @param activity activity, which holds camera preview
	 */
	public void setCameraDisplayOrientation(Activity activity) {    	
		Camera.CameraInfo info = new Camera.CameraInfo();
		Camera.getCameraInfo(cameraId, info);
		int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
		int degrees = 0;
		switch (rotation) {
		case Surface.ROTATION_0:
			degrees = 0;
			break;
		case Surface.ROTATION_90:
			degrees = 90;
			break;
		case Surface.ROTATION_180:
			degrees = 180;
			break;
		case Surface.ROTATION_270:
			degrees = 270;
			break;
		}

		int result = 0;
		if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
			result = (info.orientation + degrees) % 360;
			result = (360 - result) % 360; // compensate the mirror
		} else { // back-facing
			result = (info.orientation - degrees + 360) % 360;
		}
		orientation = result;
		camera.setDisplayOrientation(result);
	}


	/**
	 * A safe way to get an instance of the Camera object.
	 */
	private Camera getCameraInstance() {
		Camera c = null;
		try {
			cameraId = 0;
			c = Camera.open(); // attempt to get a Camera instance
			Camera.Parameters p = c.getParameters();
			p.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
			c.setParameters(p);
		} catch (Exception e) {
			// Camera is not available (in use or does not exist)
		}
		return c; // returns null if camera is unavailable
	}

	/**
	 * Swaps two elements in array
	 * @param data array
	 * @param k first element to swap
	 * @param i second element to swap
	 */
	private static void swap(byte[] data, int k, int i) {
		try{
			byte temp = data[k];
			data[k] = data[i];
			data[i] = temp;
		} catch (Exception e) {}
	}
}
