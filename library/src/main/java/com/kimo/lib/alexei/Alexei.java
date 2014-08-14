package com.kimo.lib.alexei;

import android.widget.ImageView;

import com.kimo.lib.alexei.imageprocessingthings.MedianCutAlgorithm;

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

    public AlexeiAnswer calculate(int thingFlag) {
        switch (thingFlag) {
            case ImageProcessingThing.AVERAGE_RGB:
                return new MedianCutAlgorithm(mImageView);
            default:
                throw new IllegalArgumentException("Are you sure this thing even exists??");
        }
    }

    public static Alexei analize(ImageView someImage) {
        if (singleton == null) {
            synchronized (Alexei.class) {
                if (singleton == null)
                    singleton = new Builder(someImage).build();
            }
        }
        return singleton;
    }

    public static class Builder {

        private final ImageView image;

        public Builder(ImageView image) {
            if (image == null)
                throw new IllegalArgumentException("ImageView must not be null.");

            this.image = image;
        }

        public Alexei build() {
            return new Alexei(this.image);
        }

    }
}
