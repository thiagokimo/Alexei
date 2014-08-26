package com.kimo.lib.alexei;

/**
 * Callback interface that Calculus injects the calculated results.
 */
public interface Answer<T> {

    void beforeExecution();
    void afterExecution(T answer, long elapsedTime);

    void ifFails(Exception error);
}
