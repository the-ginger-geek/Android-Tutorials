package com.helieu.zxingscannerintegration.zxing.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.helieu.zxingscannerintegration.zxing.CameraManager;

/**
 * View for displaying bounds for active camera region
 */
public class BoundingView extends View {
    /**
     * Camera manager
     */
    private CameraManager cameraManager;

    public BoundingView(Context context) {
    	super(context);
    }
    
    public BoundingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Sets camera manger
     * @param cameraManager
     */
    public void setCameraManager(CameraManager cameraManager) {
        this.cameraManager = cameraManager;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        try {
	        if (cameraManager != null) {
	            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	            paint.setStyle(Style.STROKE);
	            paint.setStrokeWidth(3);
	            paint.setColor(Color.parseColor("#44000000"));
	            
	            Rect boundingRect = cameraManager.getBoundingRect();
//	            canvas.drawRect(boundingRect, paint);
	        }
        } catch (Exception e) {
        	
        }
    }
}
