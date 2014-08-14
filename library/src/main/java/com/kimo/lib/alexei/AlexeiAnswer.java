package com.kimo.lib.alexei;

import android.widget.ImageView;

/**
 * Created by Kimo on 8/14/14.
 */
public abstract class AlexeiAnswer {
    protected ImageProcessingThing question;
    protected Object result;

    public AlexeiAnswer(ImageView image) {
        calculate(image);
    }
    protected abstract void calculate(ImageView image);
    abstract public Object andTellMeTheResult();

}

