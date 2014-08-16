package com.kimo.lib.alexei.imageprocessingthings;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.kimo.lib.alexei.AlexeiAnswer;
import com.kimo.lib.alexei.algorithms.Quantize;
import com.kimo.lib.alexei.helpers.PixelUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kimo on 8/15/14.
 */
public class ColorScheme extends AlexeiAnswer {

    private List<Integer> mColorPallete;

    public ColorScheme(ImageView image) {
        super(image);
    }

    @Override
    protected void calculate(ImageView image) {

        mColorPallete = new ArrayList<Integer>();
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();

        int [][] pixelsMatrix = PixelUtils.getPixelsMatrix(bitmap);
        int [] calculatedPallete = Quantize.quantizeImage(pixelsMatrix, 10);

        for(int x = 0; x < calculatedPallete.length; x++)
            mColorPallete.add(new Integer(calculatedPallete[x]));

    }

    @Override
    public List<Integer> andTellMeTheResult() {
        return mColorPallete;
    }
}
