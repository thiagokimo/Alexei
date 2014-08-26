package com.kimo.lib.alexei;

import android.graphics.Bitmap;

/**
 * Abstract class that represents Calculus that Alexei will handle
 */
public abstract class Calculus<T> {

    protected Bitmap mImage;
    protected T mResult;

    protected Calculus(Bitmap image) {
        mImage = image;
    }

    public Calculus() {}

    /**
     * Method that will contain the calculation algorithm
     * @param image object of interest
     * @return the calculus results
     * @throws Exception in case of errors
     */
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
