package com.kimo.lib.alexei;

import android.graphics.Bitmap;

/**
 * Created by Kimo on 8/18/14.
 */
public abstract class Calculus implements Runnable {

    protected Bitmap mImage;
    protected long mElapsedTime = 0;

    protected Calculus(Bitmap image) {
        mImage = image;
        run();
    }

    protected abstract void theCalculation(Bitmap image);

    @Override
    public void run() {
        
        long startTime, endTime;

        startTime = System.currentTimeMillis();

        theCalculation(mImage);
        endTime = System.currentTimeMillis();

        mElapsedTime = endTime - startTime;
    }

    /**
     * Returns
     * @return Object - Object with the result
     */
    public abstract Result andGiveMeTheResults();

    public long getElapsedTime() {
        return mElapsedTime;
    }
}
