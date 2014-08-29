package com.kimo.examples.alexei.events;

/**
 * Created by Kimo on 8/29/14.
 */
public class CalculateColorPaletteClicked {

    private int numberOfColors;

    public CalculateColorPaletteClicked(int numberOfColors) {
        this.numberOfColors = numberOfColors;
    }

    public int getNumberOfColors() {
        return this.numberOfColors;
    }
}
