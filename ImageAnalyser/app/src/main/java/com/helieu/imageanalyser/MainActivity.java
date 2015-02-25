package com.helieu.imageanalyser;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.helieu.imageanalyser.utility.PixelDensity;
import com.helieu.imageanalyser.utility.ColorUtility;
import com.helieu.imageanalyser.utility.FileUtility;
import com.helieu.imageanalyser.utility.ImageUtility;
import com.helieu.imageanalyser.utility.PixelLuminance;


public class MainActivity extends ActionBarActivity {
    private static final int REQUEST_IMAGE_PICK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        findViewById(R.id.openButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchPickIntent();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK) {
            String path = FileUtility.getRealPathFromURI(this, data.getData());
            processImage(path);
        }
    }

    private void processImage(String path) {
        Bitmap bitmap = ImageUtility.loadStandardBitmapFromPath(path);
        if (bitmap != null) {
            getPixelDensity(bitmap);

            getPixelLuminance(bitmap);
        }

        ((ImageView) findViewById(R.id.content)).setImageBitmap(bitmap);
    }

    private void getPixelDensity(Bitmap bitmap) {
        new PixelDensity(new PixelDensity.CallbackInterface() {
            @Override
            public void onCompleted(String hexColor) {
                setDensity(hexColor);
            }
        }).findDominantColor(bitmap);
    }

    private void getPixelLuminance(Bitmap bitmap) {
        new PixelLuminance(new PixelLuminance.CallbackInterface() {
            @Override
            public void onCompleted(String hexColor) {
                setLuminance(hexColor);
            }
        }).findDominantColor(bitmap);
    }

    private void setLuminance(String hexColor) {
        int color = Color.parseColor(hexColor);
        findViewById(R.id.luminance).setBackgroundColor(color);
        ((TextView)findViewById(R.id.luminanceText)).setTextColor(ColorUtility.getOptimizedTextColor(color, 0f));
        ((TextView)findViewById(R.id.luminanceText)).setText("Brightest Pixel Color\n" + hexColor);
    }

    private void setDensity(String hexColor) {
        int color = Color.parseColor(hexColor);
        findViewById(R.id.density).setBackgroundColor(Color.parseColor(hexColor));
        ((TextView)findViewById(R.id.densityText)).setTextColor(ColorUtility.getOptimizedTextColor(color, 0f));
        ((TextView)findViewById(R.id.densityText)).setText("Densest Pixel Color\n" + hexColor);
    }

    private void dispatchPickIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_IMAGE_PICK);
    }
}
