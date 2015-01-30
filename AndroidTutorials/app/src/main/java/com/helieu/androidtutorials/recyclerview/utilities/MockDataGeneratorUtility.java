package com.helieu.androidtutorials.recyclerview.utilities;

import android.content.Context;

import com.helieu.androidtutorials.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Neil on 15/01/29.
 */
public class MockDataGeneratorUtility {

    private static final int TOTAL_MOCK_COUNT = 15;

    public static List<String> generateMockDataArray(Context context) {
        List<String> mockDataEntityArray = new ArrayList<>();

        for (int i = 0; i < TOTAL_MOCK_COUNT; i++) {
            mockDataEntityArray.add(context.getResources().getString(R.string.lorem_ipsum_long));
        }

        return mockDataEntityArray;
    }
}
