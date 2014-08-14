package com.kimo.lib.alexei.imageprocessingthings;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.kimo.lib.alexei.AlexeiAnswer;


/**
 * Created by Kimo on 8/14/14.
 */
public class MedianCutAlgorithm extends AlexeiAnswer {

    private Color mResult;

    public MedianCutAlgorithm(ImageView image) {
        super(image);
    }

    @Override
    protected void calculate(ImageView image) {
        //TODO: Calculate the average color and set the mResult with the final value

        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();

        

    }


    @Override
    public Color andTellMeTheResult() {
        return mResult;
    }
}
