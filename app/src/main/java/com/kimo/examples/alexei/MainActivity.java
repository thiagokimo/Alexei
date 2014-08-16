package com.kimo.examples.alexei;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kimo.lib.alexei.Alexei;
import com.kimo.lib.alexei.ImageProcessingThing;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity {

    private ImageView mImageView;
    private View mMainColor;
    private LinearLayout mPalleteContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configure();
        doStuff();
    }

    private void configure() {
        mImageView = (ImageView) findViewById(R.id.imageView);
        mMainColor = findViewById(R.id.average_color);
        mPalleteContainer = (LinearLayout) findViewById(R.id.pallete_container);
    }

    private void doStuff() {
        Integer calculatedMainColor = (Integer) Alexei.analize(mImageView).calculate(ImageProcessingThing.AVERAGE_RGB).andTellMeTheResult();

        List<Integer> calculatedColorPallete = (ArrayList<Integer>) Alexei.analize(mImageView).calculate(ImageProcessingThing.COLOR_PALLETE).andTellMeTheResult();

        fillPalleteColors(calculatedColorPallete);

        mMainColor.setBackgroundColor(calculatedMainColor);
    }

    private void fillPalleteColors(List<Integer> colors) {

        LayoutInflater inflater = getLayoutInflater();

        for(int color : colors) {

            View palleteColor = inflater.inflate(R.layout.item_pallete, mPalleteContainer, false);
            palleteColor.setBackgroundColor(color);

            mPalleteContainer.addView(palleteColor);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
