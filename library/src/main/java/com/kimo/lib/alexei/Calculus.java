package com.kimo.lib.alexei;

import android.widget.ImageView;

/**
 * Created by Kimo on 8/18/14.
 */
public abstract class Calculus implements Runnable{

    protected ImageView mImage;
    protected long mElapsedTime = 0;

    protected Calculus(ImageView image) {
        mImage = image;
        run();
    }

    protected abstract void theCalculation(ImageView image);

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
    public abstract Object andGiveMeTheResults();

    public long getElapsedTime() {
        return mElapsedTime;
    }
}
