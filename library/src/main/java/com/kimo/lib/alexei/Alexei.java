package com.kimo.lib.alexei;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.kimo.lib.alexei.calculus.ColorPallete;
import com.kimo.lib.alexei.calculus.DominantColor;

/**
 * Created by Kimo on 8/14/14.
 */
public class Alexei {

    public static final String TAG = Alexei.class.getSimpleName();

    static Alexei singleton = null;
    public static Utils UTILS = new Utils();

    private Bitmap mImage;

    private Alexei(Bitmap image) {
        mImage = image;
    }

    public static Alexei analize(ImageView image) {

        if(image == null)
            throw new IllegalArgumentException("Image must not be null");

        if(singleton == null)
            synchronized (Alexei.class) {
                if(singleton == null)
                    singleton = new Alexei(Utils.getBitmapFromImageView(image));
            }
        else
            singleton.mImage = Utils.getBitmapFromImageView(image);

        return singleton;
    }

    public static Alexei analize(Bitmap image) {

        if(image == null)
            throw new IllegalArgumentException("Image must not be null");

        if(singleton == null)
            synchronized (Alexei.class) {
                if(singleton == null)
                    singleton = new Alexei(image);
            }
        else
            singleton.mImage = image;
        return singleton;
    }

    public Calculus perform(int predefinedCalculusFlag) {

        switch (predefinedCalculusFlag) {
            case ImageProcessingThing.DOMINANT_COLOR:
                return new DominantColor(mImage);
            case ImageProcessingThing.COLOR_PALLETE:
                return new ColorPallete(mImage);
            default:
                throw new IllegalArgumentException("Predefined flag is not matching.");
        }
    }
}
