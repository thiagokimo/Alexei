package com.kimo.lib.alexei.calculus;

import android.graphics.Bitmap;

import com.kimo.lib.alexei.Calculus;
import com.kimo.lib.alexei.algorithms.StackBlur;

/**
 * Blur effect
 */
public class BlurCalculus extends Calculus<Bitmap> {

    private int mRadius;

    public BlurCalculus(Bitmap image, int radius) {
        super(image);
        mRadius = radius;
    }

    @Override
    protected Bitmap theCalculation(Bitmap image) throws Exception {
        return StackBlur.fastBlur(image,mRadius);
    }
}
