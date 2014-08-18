package com.kimo.lib.alexei;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.kimo.lib.alexei.calculus.ColorPallete;

/**
 * Created by Kimo on 8/18/14.
 */
public class RequestProcess {

    private final Alexei mAlexei;
    private final Bitmap mImage;
    private final int mImageResource;

    public RequestProcess(Alexei alexei, ImageView image, int imageResource) {
        mAlexei = alexei;
        mImage = Utils.getBitmapFromImageView(image);
        mImageResource = imageResource;
    }

    public RequestProcess(Alexei alexei, Bitmap image, int imageResource) {
        mAlexei = alexei;
        mImage = image;
        mImageResource = imageResource;
    }

    public Calculus calculate(int calculusFlag) {
        if(calculusFlag == ImageProcessingThing.COLOR_PALLETE)
            return new ColorPallete(mImage);
        else
            return null;
    }

}
