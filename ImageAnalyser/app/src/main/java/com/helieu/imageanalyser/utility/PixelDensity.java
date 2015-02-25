package com.helieu.imageanalyser.utility;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Neil on 15/02/23.
 */
public class PixelDensity {
    private static final String TAG = PixelDensity.class.getSimpleName();
    private static final int ACCEPT_DIFFERENTIAL_THRESHOLD = 40;
    private static final int REJECT_DIFFERENTIAL_THRESHOLD = 100;

    private CallbackInterface callback;

    public PixelDensity(CallbackInterface callback) {
        this.callback = callback;
    }

    public void findDominantColor(Bitmap bitmap) {
        new GetDominantColor().execute(bitmap);
    }

    private int getDominantColorFromBitmap(Bitmap bitmap) {
        Bitmap resizedBitmap = getResizedBitmap(bitmap, 0.02f);
        int [] pixels = new int[resizedBitmap.getWidth()*resizedBitmap.getHeight()];
        resizedBitmap.getPixels(pixels,0,resizedBitmap.getWidth(),0,0, resizedBitmap.getWidth(), resizedBitmap.getHeight());

        Map<Integer, PixelObject> pixelMap = refactorMapByPixelComparison(getMostDominantPixelMap(pixels));
        return getDominantPixel(pixelMap);
    }

    private Bitmap getResizedBitmap(Bitmap image, float resizeRatio) {
        int width = image.getWidth();
        int height = image.getHeight();
        int resizedWidth = (int) (width*resizeRatio);
        int resizedHeight = (int) (height*resizeRatio);

        return Bitmap.createScaledBitmap(image, resizedWidth, resizedHeight, true);
    }

    private Map<Integer, PixelObject> getMostDominantPixelMap(int [] pixels) {
        Map<Integer, PixelObject> pixelMap = new HashMap<>();

        for (int pixel : pixels) {
            if (pixelMap.containsKey(pixel)) {
                pixelMap.get(pixel).pixelCount++;
            } else {
                pixelMap.put(pixel, new PixelObject(pixel, 1));
            }
        }

        return pixelMap;
    }

    /**
     * ((r2 - r1)2 + (g2 - g1)2 + (b2 - b1)2)1/2
     */
    private double getShadeComparison(int inputColor, int comparisonColor) {
        int inputColorRed = ColorUtility.getRedFromColor(inputColor);
        int inputColorGreen = ColorUtility.getGreenFromColor(inputColor);
        int inputColorBlue = ColorUtility.getBlueFromColor(inputColor);

        int comparisonColorRed = ColorUtility.getRedFromColor(comparisonColor);
        int comparisonColorGreen = ColorUtility.getGreenFromColor(comparisonColor);
        int comparisonColorBlue = ColorUtility.getBlueFromColor(comparisonColor);

        return Math.pow((Math.pow((comparisonColorRed - inputColorRed), 2f) +
                Math.pow((comparisonColorGreen - inputColorGreen), 2f) +
                Math.pow(comparisonColorBlue - inputColorBlue, 2f)), 0.5f);
    }

    private Map<Integer, PixelObject> refactorMapByPixelComparison(Map<Integer, PixelObject> pixelMap) {
        PixelObject comparisonObject = null;
        PixelObject currentPixelObject = null;

        boolean isBreakingObject = false;
        Iterator<Map.Entry<Integer, PixelObject>> iterator = pixelMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, PixelObject> entry = iterator.next();
            if (comparisonObject != null) {

                currentPixelObject = entry.getValue();

                double comparisonValue = getShadeComparison(currentPixelObject.pixel, comparisonObject.pixel);
                if (comparisonValue <= ACCEPT_DIFFERENTIAL_THRESHOLD) {
                    int currentObjectPixelCount = currentPixelObject.pixelCount;
                    int comparisonObjectPixelCount = comparisonObject.pixelCount;
                    currentPixelObject.pixelCount = currentObjectPixelCount + comparisonObjectPixelCount;
                    isBreakingObject = true;
                    break;
                } else if (comparisonValue <= REJECT_DIFFERENTIAL_THRESHOLD) {
                    iterator.remove();
                    continue;
                }
            }

            comparisonObject = entry.getValue();
        }

        if (isBreakingObject) {
            pixelMap.remove(comparisonObject.pixel);
            pixelMap.remove(currentPixelObject.pixel);

            currentPixelObject.pixel = ColorUtility.blendColors(currentPixelObject.pixel, comparisonObject.pixel, 0.5f);
            pixelMap.put(currentPixelObject.pixel, currentPixelObject);
            refactorMapByPixelComparison(pixelMap);
        }

        return pixelMap;
    }

    private int getDominantPixel(Map<Integer, PixelObject> pixelMap) {
        int dominantColor = 0;
        int largestCount = 0;
        for (Map.Entry<Integer, PixelObject> entry : pixelMap.entrySet()) {
            PixelObject pixelObject = entry.getValue();

            if (pixelObject.pixelCount > largestCount) {
                largestCount = pixelObject.pixelCount;
                dominantColor = pixelObject.pixel;
            }
        }

        return dominantColor;
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

    private class PixelObject {
        public int pixel;
        public int pixelCount;

        public PixelObject(int pixel, int pixelCount) {
            this.pixel = pixel;
            this.pixelCount = pixelCount;
        }
    }

    public interface CallbackInterface {
        public void onCompleted(String dominantColor);
    }
}
