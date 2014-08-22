package com.kimo.lib.alexei.calculus;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.kimo.lib.alexei.Calculus;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Kimo on 8/19/14.
 */
public class DominantColor extends Calculus<Integer> {

    public DominantColor(Bitmap image) {
        super(image);
    }

    /**
     * Calculated according to this solution {@link http://stackoverflow.com/questions/8471236/finding-the-dominant-color-of-an-image-in-an-android-drawable }
     * @param image
     */
    @Override
    protected Integer theCalculation(Bitmap image) {
        Map histogram = new HashMap();

        for(int i = 0; i < image.getWidth(); i++)
            for(int j = 0; j < image.getHeight(); j++) {
                int rgb = image.getPixel(i,j);

                int rgbArray[] = getRGBArrayFromPixel(rgb);

                if(!isGray(rgbArray)) {

                    Integer counter = (Integer) histogram.get(rgb);

                    if (counter == null)
                        counter = 0;
                    counter++;

                    histogram.put(rgb, counter);
                }
            }

        return Color.parseColor("#"+getMostCommonColor(histogram));
    }

    private int[] getRGBArrayFromPixel(int pixel) {

        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;

        return new int[]{red,green,blue};

    }

    private boolean isGray(int[] rgbArr) {

        int rgDiff = rgbArr[0] - rgbArr[1];
        int rbDiff = rgbArr[0] - rgbArr[2];

        int tolerance = 100;

        if (rgDiff > tolerance || rgDiff < -tolerance)
            if (rbDiff > tolerance || rbDiff < -tolerance)
                return false;
        return true;
    }

    private String getMostCommonColor(Map histogram) {

        List list = new LinkedList(histogram.entrySet());

        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {

                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());

            }

        });

        Map.Entry me = (Map.Entry )list.get(list.size()-1);
        int[] rgb = getRGBArrayFromPixel((Integer) me.getKey());

        return Integer.toHexString(rgb[0])+""+Integer.toHexString(rgb[1])+""+Integer.toHexString(rgb[2]);
    }
}
