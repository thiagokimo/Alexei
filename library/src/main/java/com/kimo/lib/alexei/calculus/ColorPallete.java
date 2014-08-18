package com.kimo.lib.alexei.calculus;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.kimo.lib.alexei.Calculus;
import com.kimo.lib.alexei.algorithms.Quantize;
import com.kimo.lib.alexei.helpers.PixelUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kimo on 8/18/14.
 */
public class ColorPallete extends Calculus{

    public static final int NUMBER_OF_COLORS = 5;

    private List<Integer> mColorPallete;

    public ColorPallete(ImageView image) {
        super(image);
    }

    @Override
    public void theCalculation(ImageView image) {

        Bitmap bitmap = PixelUtils.getBitmapFromImageView(image);
        mColorPallete = new ArrayList<Integer>();

        int [][] pixelsMatrix = PixelUtils.getPixelsMatrixFromBitmap(bitmap);
        int [] calculatedPallete = Quantize.quantizeImage(pixelsMatrix, NUMBER_OF_COLORS);

        for(int x = 0; x < calculatedPallete.length; x++)
            mColorPallete.add(new Integer(calculatedPallete[x]));
    }


    @Override
    public List<Integer> andGiveMeTheResults() {
        return mColorPallete;
    }
}
