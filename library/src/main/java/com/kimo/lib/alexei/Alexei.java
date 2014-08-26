package com.kimo.lib.alexei;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Main class that configures operations over images. You need to pass an {@link android.widget.ImageView} or a {@link android.graphics.Bitmap}
 * and the {@link com.kimo.lib.alexei.Calculus} that you be applied over the given image. Due to this, retrieving the result requires a callback as an
 * {@link com.kimo.lib.alexei.Answer}
 */
public class Alexei {

    private static Alexei singleton = null;
    private Context mContext;
    private Bitmap mImage;

    private Alexei(Context context) {
        mContext = context;
    }

    public static Alexei with(Context context) {
        if (singleton == null) {
            synchronized (Alexei.class) {
                if (singleton == null)
                    singleton = new Alexei(context);
            }
        }

        return singleton;
    }

    public CalculusBuilder analize(ImageView image) {

        if(image == null)
            throw new IllegalArgumentException("Image must not be null");

        mImage = AlexeiUtils.getBitmapFromImageView(image);

        return new CalculusBuilder(mImage);
    }

    public CalculusBuilder analize(Bitmap image) {

        if(image == null)
            throw new IllegalArgumentException("Image must not be null");

        mImage = image;

        return new CalculusBuilder(mImage);
    }
}
