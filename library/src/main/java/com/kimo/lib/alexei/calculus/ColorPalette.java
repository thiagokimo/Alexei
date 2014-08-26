package com.kimo.lib.alexei.calculus;

import android.graphics.Bitmap;

import com.kimo.lib.alexei.AlexeiUtils;
import com.kimo.lib.alexei.Calculus;
import com.kimo.lib.alexei.algorithms.Quantize;

import java.util.ArrayList;
import java.util.List;

/**
 * Calculates the most common colors of an image. It can be instantiated with an extra parameter that
 * represents the maximum number of colors of the palette.
 */
public class ColorPalette extends Calculus<List<Integer>> {

    public static final int NUMBER_OF_COLORS = 10;

    private List<Integer> mColorPallete;
    private int mNumberOfColors = NUMBER_OF_COLORS;

    public ColorPalette(Bitmap image, int numberOfColors) {
        super(image);
        mNumberOfColors = numberOfColors;
    }

    public ColorPalette(Bitmap image) {
        super(image);
    }

    @Override
    protected List<Integer> theCalculation(Bitmap image) {
        mColorPallete = new ArrayList<Integer>();

        int [][] pixelsMatrix = AlexeiUtils.getPixelsMatrixFromBitmap(image);
        int [] calculatedPalette = Quantize.quantizeImage(pixelsMatrix, NUMBER_OF_COLORS);

        for(int x = 0; x < calculatedPalette.length; x++)
            mColorPallete.add(new Integer(calculatedPalette[x]));

        return mColorPallete;
    }

}
