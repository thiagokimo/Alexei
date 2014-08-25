package com.kimo.lib.alexei;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.kimo.lib.alexei.calculus.ColorPalette;
import com.kimo.lib.alexei.calculus.DominantColor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Kimo on 8/14/14.
 */
public class Alexei {

    public static final String TAG = Alexei.class.getSimpleName();

    static Alexei singleton = null;

    private Bitmap mImage;
    private Calculus mCalculus;

    public static Alexei analize(ImageView image) {

        if(image == null)
            throw new IllegalArgumentException("Image must not be null");

        getDefault().mImage = AlexeiUtils.getBitmapFromImageView(image);
        return singleton;
    }

    public static Alexei analize(Bitmap image) {

        if(image == null)
            throw new IllegalArgumentException("Image must not be null");

        getDefault().mImage = image;
        return singleton;
    }

    public Alexei perform(int predefinedCalculusFlag) {

        switch (predefinedCalculusFlag) {
            case ImageProcessingThing.DOMINANT_COLOR:
                mCalculus = new DominantColor(mImage);
                return this;
            case ImageProcessingThing.COLOR_PALETTE:
                mCalculus = new ColorPalette(mImage);
                return this;
            default:
                throw new IllegalArgumentException("Predefined flag is not matching.");
        }
    }

    public Alexei perform(Calculus customCalculus) {
        mCalculus = customCalculus;
        return this;
    }

    public void andGiveMe(Answer answerCallback) {

        if(mCalculus == null)
            throw new IllegalArgumentException("You must inform which calculus Alexei needs to perform");

        answerCallback.beforeExecution();

        ExecutorService calculator = AlexeiUtils.getDefaultCalculator();

        calculator.execute(mCalculus);
        calculator.shutdown();
        try {
            calculator.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            answerCallback.afterExecution(mCalculus.mResult, mCalculus.mElapsedTime);
        } catch (InterruptedException e) {
            answerCallback.ifFails(e);
        }
    }

    private static synchronized Alexei getDefault() {
        if(singleton == null)
            singleton = new Alexei();
        return singleton;
    }
}
