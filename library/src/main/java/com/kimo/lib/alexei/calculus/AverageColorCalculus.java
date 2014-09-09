package com.kimo.lib.alexei.calculus;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.kimo.lib.alexei.Calculus;

/**
 * Calculates the average RGB color of an image
 */
public class AverageColorCalculus extends Calculus<Integer> {

    public static final String TAG = AverageColorCalculus.class.getSimpleName();

    public AverageColorCalculus(Bitmap image) {
        super(image);
    }

    @Override
    protected Integer theCalculation(Bitmap image) {

        if (null == image) return Color.TRANSPARENT;
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

            return Color.rgb(red, green, blue);
        }
    }
}
