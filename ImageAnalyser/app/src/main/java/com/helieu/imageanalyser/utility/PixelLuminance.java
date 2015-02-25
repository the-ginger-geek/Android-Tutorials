package com.helieu.imageanalyser.utility;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Neil on 15/02/23.
 */
public class PixelLuminance {
    private static final String TAG = PixelLuminance.class.getSimpleName();

    private CallbackInterface callback;

    public PixelLuminance(CallbackInterface callback) {
        this.callback = callback;
    }

    public void findDominantColor(Bitmap bitmap) {
        new GetDominantColor().execute(bitmap);
    }

    private int getDominantColorFromBitmap(Bitmap bitmap) {
        Bitmap resizedBitmap = getResizedBitmap(bitmap, 0.1f);
        int [] pixels = new int[resizedBitmap.getWidth()*resizedBitmap.getHeight()];
        resizedBitmap.getPixels(pixels,0,resizedBitmap.getWidth(),0,0, resizedBitmap.getWidth(), resizedBitmap.getHeight());

        return getMostDominantPixel(pixels);
    }

    private Bitmap getResizedBitmap(Bitmap image, float resizeRatio) {
        int width = image.getWidth();
        int height = image.getHeight();
        int resizedWidth = (int) (width*resizeRatio);
        int resizedHeight = (int) (height*resizeRatio);

        return Bitmap.createScaledBitmap(image, resizedWidth, resizedHeight, true);
    }

    private int getMostDominantPixel(int [] pixels) {
        int dominantColor = 0;
        int resultPixel = 0;
        for (int pixel : pixels) {
            int luminance = calculatePixelLuminance(pixel);
            if (luminance > dominantColor) {
                dominantColor = luminance;
                resultPixel = pixel;
            }
        }

        return resultPixel;
    }

    private int calculatePixelLuminance(int pixel) {
        int inputColorRed = ColorUtility.getRedFromColor(pixel);
        int inputColorGreen = ColorUtility.getGreenFromColor(pixel);
        int inputColorBlue = ColorUtility.getBlueFromColor(pixel);
        return (int) (0.2126*inputColorRed + 0.7152*inputColorGreen + 0.0722*inputColorBlue);
    }

    private class GetDominantColor extends AsyncTask<Bitmap, Integer, Integer> {

        @Override
        protected Integer doInBackground(Bitmap... params) {
            return getDominantColorFromBitmap(params[0]);
        }

        @Override
        protected void onPostExecute(Integer dominantColor) {
            String hexColor = ColorUtility.colorToHex(dominantColor);
            if (callback != null)
                callback.onCompleted(hexColor);
        }
    }

    public interface CallbackInterface {
        public void onCompleted(String dominantColor);
    }
}
