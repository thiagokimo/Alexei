package com.kimo.lib.alexei;

import android.graphics.Bitmap;
import android.os.AsyncTask;

/**
 * Created by Kimo on 8/26/14.
 */
public class CalculusTask<T> extends AsyncTask<Void, Void, T> {

    private Bitmap image;
    private Calculus<T> calculus;
    private Answer<T> callback;
    private long elapsedTime;

    public CalculusTask(Bitmap image, Calculus<T> calculus, Answer<T> callback) {
        this.image = image;
        this.calculus = calculus;
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        callback.beforeExecution();
    }

    @Override
    protected T doInBackground(Void... params) {

        long startTime, endTime;

        T result = null;
        try {
            startTime = System.currentTimeMillis();

            result = calculus.perform();

            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;

            return result;
        } catch (Exception e) {
            callback.ifFails(e);
        }

        return result;
    }

    @Override
    protected void onPostExecute(T t) {
        super.onPostExecute(t);

        if(t != null)
            callback.afterExecution(t, elapsedTime);
    }
}
