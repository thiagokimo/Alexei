package com.kimo.lib.alexei;

import android.graphics.Bitmap;

import com.kimo.lib.alexei.calculus.ColorPalette;
import com.kimo.lib.alexei.calculus.DominantColor;

/**
 * Build {@link com.kimo.lib.alexei.Calculus} objects that would be dispatched over Alexei's pool thread.
 */
public class CalculusBuilder<T> {

    private Bitmap image;
    private Calculus calculus;

    public CalculusBuilder(Bitmap image) {
        this.image = image;
    }

    /**
     * Sets the calculus that will be done.
     *
     * @param predefinedCalculation is a flag that must be one of the integers in {@link com.kimo.lib.alexei.ImageProcessingThing}
     * @return the instance of the {@link com.kimo.lib.alexei.CalculusBuilder}
     */
    public CalculusBuilder<T> perform(int predefinedCalculation) {

        switch (predefinedCalculation) {
            case ImageProcessingThing.DOMINANT_COLOR:
                calculus = new DominantColor(image);
                break;
            case ImageProcessingThing.COLOR_PALETTE:
                calculus = new ColorPalette(image);
                break;
            default:
                throw new IllegalArgumentException("Predefined flag is not matching");
        }

        return this;
    }

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

        task.execute();
    }



}
