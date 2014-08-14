package com.kimo.lib.alexei;

import android.content.Context;
import android.test.AndroidTestCase;
import android.widget.ImageView;

import org.mockito.Mock;

/**
 * Created by Kimo on 8/14/14.
 */
public class AlexeiTest extends AndroidTestCase {

    @Mock Context context;
    @Mock ImageView someImageView;
    @Mock AlexeiAnswer someAnswer;

    public void calculateTheMedianCutAlgorithmOfAnImageAndReturnsAColor() {
        assertEquals(1, Alexei.analize(someImageView).calculate(ImageProcessingThing.AVERAGE_RGB).andTellMeTheResult());
    }
}
