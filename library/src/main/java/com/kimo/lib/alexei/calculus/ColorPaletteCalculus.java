package com.kimo.lib.alexei.calculus;

import android.graphics.Bitmap;

import com.kimo.lib.alexei.Utils;
import com.kimo.lib.alexei.Calculus;
import com.kimo.lib.alexei.algorithms.Quantize;

import java.util.ArrayList;
import java.util.List;

/**
 * Calculates the most common colors of an image. It can be instantiated with an extra parameter that
 * represents the maximum number of colors of the palette.
 */
public class ColorPaletteCalculus extends Calculus<List<Integer>> {

    private List<Integer> mColorPallete;
    private int mNumberOfColors;

    public ColorPaletteCalculus(Bitmap image, int numberOfColors) {
        super(image);
        mNumberOfColors = numberOfColors;
    }

    public ColorPaletteCalculus(Bitmap image) {
        super(image);
    }

    @Override
    protected List<Integer> theCalculation(Bitmap image) {
        mColorPallete = new ArrayList<Integer>();

        int [][] pixelsMatrix = Utils.getPixelsMatrixFromBitmap(image);
        int [] calculatedPalette = Quantize.quantizeImage(pixelsMatrix, mNumberOfColors);

        for(int x = 0; x < calculatedPalette.length; x++)
            mColorPallete.add(new Integer(calculatedPalette[x]));

        return mColorPallete;
    }

}
