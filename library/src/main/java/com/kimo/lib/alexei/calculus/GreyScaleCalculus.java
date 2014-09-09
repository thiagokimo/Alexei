package com.kimo.lib.alexei.calculus;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.kimo.lib.alexei.Calculus;

/**
 * Calculates the grey scale of an image.
 */
public class GreyScaleCalculus extends Calculus<Bitmap> {

    public static final double RED_FACTOR = 0.299; // 30%
    public static final double GREEN_FACTOR = 0.587;// 59%
    public static final double BLUE_FACTOR = 0.114;// 11%

    public GreyScaleCalculus(Bitmap bitmap) { super(bitmap); }

    @Override
    protected Bitmap theCalculation(Bitmap image) throws Exception {

        Bitmap bitmap = image.copy(image.getConfig(), true);

        for(int i = 0; i < bitmap.getWidth(); i++)
            for(int j = 0; j < bitmap.getHeight(); j++) {
                int pixel = image.getPixel(i,j);

                int alpha = Color.alpha(pixel);
                int red = Color.red(pixel);
                int green = Color.green(pixel);
                int blue = Color.blue(pixel);

                red = green = blue = (int) (RED_FACTOR * red + GREEN_FACTOR * green + BLUE_FACTOR * blue);

                bitmap.setPixel(i,j,Color.argb(alpha,red,green,blue));
            }

        return bitmap;
    }
}
