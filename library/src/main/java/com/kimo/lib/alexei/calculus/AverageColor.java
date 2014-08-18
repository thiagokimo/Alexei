package com.kimo.lib.alexei.calculus;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.kimo.lib.alexei.Calculus;
import com.kimo.lib.alexei.Result;

/**
 * Created by Kimo on 8/15/14.
 */
public class AverageColor extends Calculus {

    public static final String TAG = AverageColor.class.getSimpleName();

    private int mAverageColor;

    public AverageColor(Bitmap image) {
        super(image);
    }

    @Override
    protected void theCalculation(Bitmap image) {

        if (null == image) mAverageColor = Color.TRANSPARENT;
        else {

            int pixelCount = image.getWidth() * image.getHeight();
            int red, green, blue;
            red = green = blue = 0;

            for (int i = 0; i < image.getWidth(); i++)
                for (int j = 0; j < image.getHeight(); j++) {
                    int pixel = image.getPixel(i, j);

                    red += Color.red(pixel);
                    green += Color.green(pixel);
                    blue += Color.blue(pixel);
                }

            red /= pixelCount;
            green /= pixelCount;
            blue /= pixelCount;

            mAverageColor = Color.rgb(red, green, blue);
        }

    }

    @Override
    public Result andGiveMeTheResults() {
        return new Result(mElapsedTime, mAverageColor);
    }
}
