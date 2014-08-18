package com.kimo.lib.alexei.calculus;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.ImageView;

import com.kimo.lib.alexei.Calculus;
import com.kimo.lib.alexei.helpers.PixelUtils;

/**
 * Created by Kimo on 8/15/14.
 */
public class AverageColor extends Calculus {

    public static final String TAG = AverageColor.class.getSimpleName();

    private int mAverageColor;

    public AverageColor(ImageView image) {
        super(image);
    }

    @Override
    protected void theCalculation(ImageView image) {

        Bitmap bitmap = PixelUtils.getBitmapFromImageView(image);

        if (null == bitmap) mAverageColor = Color.TRANSPARENT;
        else {

            int pixelCount = bitmap.getWidth() * bitmap.getHeight();
            int red, green, blue;
            red = green = blue = 0;

            for (int i = 0; i < bitmap.getWidth(); i++)
                for (int j = 0; j < bitmap.getHeight(); j++) {
                    int pixel = bitmap.getPixel(i, j);

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
    public Integer andGiveMeTheResults() {
        return mAverageColor;
    }
}
