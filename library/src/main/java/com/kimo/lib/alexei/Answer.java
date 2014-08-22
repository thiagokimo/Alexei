package com.kimo.lib.alexei;

/**
 * Created by Kimo on 8/21/14.
 */
public interface Answer<T> {

    void beforeExecution();
    void afterExecution(T answer, long elapsedTime);

    void ifFails(Exception error);
}
