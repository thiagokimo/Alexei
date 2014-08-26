package com.kimo.lib.alexei;

import android.graphics.Bitmap;

/**
 * Created by Kimo on 8/18/14.
 */
public abstract class Calculus<T> {

    protected Bitmap mImage;
    protected T mResult;

    protected Calculus(Bitmap image) {
        mImage = image;
    }

    public Calculus() {}

    protected abstract T theCalculation(Bitmap image) throws Exception;

    /**
     * Trigger that starts the calculations.
     * @return something based on what will be calculated
     * @throws Exception if something wrong happens
     */
    public T perform() throws Exception {
        return theCalculation(mImage);
    }

}
