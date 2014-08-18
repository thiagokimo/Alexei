package com.kimo.lib.alexei;

import android.widget.ImageView;

import com.kimo.lib.alexei.calculus.ColorPallete;

/**
 * Created by Kimo on 8/18/14.
 */
public class RequestProcess {

    private final Alexei mAlexei;
    private final ImageView mImageView;
    private final int mImageResource;

    public RequestProcess(Alexei alexei, ImageView image, int imageResource) {
        this.mAlexei = alexei;
        this.mImageView = image;
        mImageResource = imageResource;
    }

    public Calculus calculate(int calculusFlag) {
        if(calculusFlag == ImageProcessingThing.COLOR_PALLETE)
            return new ColorPallete(mImageView);
        else
            return null;
    }

}
