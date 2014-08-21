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
        if(singleton == null)
            synchronized (Alexei.class) {
                if(singleton == null)
                    singleton = new Builder(image).build();
            }
        return singleton;
    }

    public static Alexei analize(Bitmap image) {
        if(singleton == null)
            synchronized (Alexei.class) {
                if(singleton == null)
                    singleton = new Builder(image).build();
            }
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

    public static class Builder {
        private final Bitmap image;

        public Builder(ImageView image) {
            if(image == null)
                throw new IllegalArgumentException("Image must not be null");
            this.image = Utils.getBitmapFromImageView(image);
        }

        public Builder(Bitmap image) {
            if(image == null)
                throw new IllegalArgumentException("Image must not be null");
            this.image = image;
        }

        public Alexei build() {
            return new Alexei(this.image);
        }
    }
}
