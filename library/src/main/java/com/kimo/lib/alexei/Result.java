package com.kimo.lib.alexei;

/**
 * Created by Kimo on 8/18/14.
 */
public class Result {

    private long mElapsedTime;
    private Object mResult;

    public Result(long time, Object result) {
        mElapsedTime = time;
        mResult = result;
    }

    public long getElapsedTime() {
        return mElapsedTime;
    }

    public void setElapsedTime(long mElapsedTime) {
        this.mElapsedTime = mElapsedTime;
    }

    public Object getResult() {
        return mResult;
    }

    public void setResult(Object mResult) {
        this.mResult = mResult;
    }
}
