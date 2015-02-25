package com.helieu.imageanalyser;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.helieu.imageanalyser.utility.ColorFinder;
import com.helieu.imageanalyser.utility.ColorUtility;
import com.helieu.imageanalyser.utility.FileUtility;
import com.helieu.imageanalyser.utility.ImageUtility;



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
            new ColorFinder(new ColorFinder.CallbackInterface() {
                @Override
                public void onCompleted(String hexColor) {
                    String colorName = ColorUtility.getColorName(hexColor);
                    Toast.makeText(MainActivity.this, "Most dominant color name[hex : "+hexColor+"] - " + colorName, Toast.LENGTH_LONG).show();
                }
            }).findDominantColor(bitmap);
        }

        ((ImageView) findViewById(R.id.content)).setImageBitmap(bitmap);
    }

    /**
     * Starts the image chooser
     */
    private void dispatchPickIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_IMAGE_PICK);
    }
}
