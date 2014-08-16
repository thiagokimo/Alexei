package com.kimo.lib.alexei;

import android.widget.ImageView;

import com.kimo.lib.alexei.imageprocessingthings.AverageColor;
import com.kimo.lib.alexei.imageprocessingthings.ColorScheme;

/**
 * Created by Kimo on 8/14/14.
 */
public class Alexei {

    public static final String TAG = Alexei.class.getSimpleName();

    static Alexei singleton = null;

    private ImageView mImageView;

    Alexei(ImageView image) {
        mImageView = image;
    }

    public AlexeiAnswer calculate(int thing) {
        switch (thing) {
            case ImageProcessingThing.AVERAGE_RGB:
                return new AverageColor(mImageView);
            case ImageProcessingThing.COLOR_PALLETE:
                return new ColorScheme(mImageView);
            default:
                throw new IllegalArgumentException("I still don't know this!");
        }
    }

    public static Alexei analize(ImageView someImage) {
        if (singleton == null) {
            synchronized (Alexei.class) {
                if (singleton == null)
                    singleton = new Alexei(someImage);
            }
        }
        return singleton;
    }
}
