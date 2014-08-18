package com.kimo.lib.alexei.tests;

import android.app.Activity;
import android.widget.ImageView;

import com.kimo.lib.alexei.Alexei;
import com.kimo.lib.alexei.ImageProcessingThing;

import junit.framework.TestCase;

/**
 * Created by Kimo on 8/14/14.
 */
public class AlexeiTest extends TestCase {

    public void testSomething() {
        Activity activity = new Activity();
        Alexei.with(activity.getApplicationContext())
                .analize(new ImageView(activity))
                .calculate(ImageProcessingThing.COLOR_PALLETE)
                .andGiveMeTheResults();
    }
}
