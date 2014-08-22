package com.kimo.lib.alexei;

import android.graphics.Bitmap;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Kimo on 8/18/14.
 */
public abstract class Calculus<T> implements Runnable {

    protected Bitmap mImage;
    protected long mElapsedTime = 0;
    protected T mResult;

    protected Calculus(Bitmap image) {
        mImage = image;
    }

    protected abstract T theCalculation(Bitmap image);

    @Override
    public void run() {
        
        long startTime, endTime;

        startTime = System.currentTimeMillis();
        mResult = theCalculation(mImage);
        endTime = System.currentTimeMillis();

        mElapsedTime = endTime - startTime;
    }

    /**
     * Returns
     * @return Object - Object with the result
     */
    public void andGiveMe(Answer<T> answerCallback) {
        answerCallback.beforeExecution();

        ExecutorService calculator = AlexeiUtils.getDefaultCalculator();

        calculator.execute(this);
        calculator.shutdown();
        try {
            calculator.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            answerCallback.afterExecution(mResult, mElapsedTime);
        } catch (InterruptedException e) {
            answerCallback.ifFails(e);
        }
    }
}
