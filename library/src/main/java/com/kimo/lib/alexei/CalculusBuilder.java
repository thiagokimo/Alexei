package com.kimo.lib.alexei;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;

import java.util.concurrent.Executor;

/**
 * Build {@link com.kimo.lib.alexei.Calculus} objects that would be dispatched over Alexei's pool thread.
 */
public class CalculusBuilder<T> {

    private Bitmap image;
    private Calculus calculus;
    private Executor executor;

    public CalculusBuilder(Bitmap image) {
        this.image = image;
    }

    /**
     * Performs a given calculus
     * @param customCalculus can be a new instance of {@link com.kimo.lib.alexei.Calculus} or any object that is inside {@link com.kimo.lib.alexei.calculus}
     * @return itself
     */
    public CalculusBuilder<T> perform(Calculus<T> customCalculus) {
        calculus = customCalculus;
        return this;
    }

    /**
     * Executes the calculation. Must be called after a {@link com.kimo.lib.alexei.Calculus} is set.
     *
     * @param callback to use when the calculation finish. The callback will be called in UI thread.
     */
    public void showMe(Answer<T> callback) {
        CalculusTask<T> task = new CalculusTask(image,calculus,callback);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            if(executor == null) {
                executor = AsyncTask.THREAD_POOL_EXECUTOR;
            }

            task.executeOnExecutor(executor);
        } else {
            task.execute();
        }

    }

    /**
     *
     * Set a custom {@link Executor}
     *
     * @param executor your custom executor
     * @return itself
     */
    public CalculusBuilder<T> withExecutor(Executor executor) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            this.executor = executor;
            return this;
        } else {
            throw new RuntimeException("You cannot use a custom executor on pre honeycomb devices");
        }

    }

}
