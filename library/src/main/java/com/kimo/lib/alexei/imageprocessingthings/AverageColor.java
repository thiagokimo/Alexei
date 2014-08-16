package com.kimo.lib.alexei.imageprocessingthings;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.kimo.lib.alexei.AlexeiAnswer;

/**
 * Created by Kimo on 8/15/14.
 */
public class AverageColor extends AlexeiAnswer {

    public static final String TAG = AverageColor.class.getSimpleName();

    private int mAverageColor;

    public AverageColor(ImageView image) {
        super(image);
    }

    @Override
    protected void calculate(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();

        if (null == bitmap) mAverageColor = Color.TRANSPARENT;
        else {

            int pixelCount = bitmap.getWidth() * bitmap.getHeight();
            int red, green, blue;
            red = green = blue = 0;

            for(int i = 0; i < bitmap.getWidth(); i++)
                for(int j = 0; j < bitmap.getHeight(); j++) {
                    int pixel = bitmap.getPixel(i,j);

                    red += Color.red(pixel);
                    green += Color.green(pixel);
                    blue += Color.blue(pixel);
                }

            red /= pixelCount;
            green /= pixelCount;
            blue /= pixelCount;

            mAverageColor = Color.rgb(red,green,blue);
        }
    }

    @Override
    public Integer andTellMeTheResult() {
        return mAverageColor;
    }
}
