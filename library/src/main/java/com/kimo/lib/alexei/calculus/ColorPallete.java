package com.kimo.lib.alexei.calculus;

import android.graphics.Bitmap;

import com.kimo.lib.alexei.Calculus;
import com.kimo.lib.alexei.Result;
import com.kimo.lib.alexei.Utils;
import com.kimo.lib.alexei.algorithms.Quantize;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kimo on 8/18/14.
 */
public class ColorPallete extends Calculus{

    public static final int NUMBER_OF_COLORS = 5;

    private List<Integer> mColorPallete;

    public ColorPallete(Bitmap image) {
        super(image);
    }

    @Override
    public void theCalculation(Bitmap image) {

        mColorPallete = new ArrayList<Integer>();

        int [][] pixelsMatrix = Utils.getPixelsMatrixFromBitmap(image);
        int [] calculatedPallete = Quantize.quantizeImage(pixelsMatrix, NUMBER_OF_COLORS);

        for(int x = 0; x < calculatedPallete.length; x++)
            mColorPallete.add(new Integer(calculatedPallete[x]));
    }


    @Override
    public Result andGiveMeTheResults() {
        return new Result(mElapsedTime, mColorPallete);
    }
}
