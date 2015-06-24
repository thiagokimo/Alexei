package com.kimo.examples.alexei.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devspark.progressfragment.ProgressFragment;
import com.kimo.examples.alexei.R;
import com.kimo.examples.alexei.events.CalculateColorPaletteClicked;
import com.kimo.lib.alexei.Alexei;
import com.kimo.lib.alexei.Utils;
import com.kimo.lib.alexei.Answer;
import com.kimo.lib.alexei.calculus.ColorPaletteCalculus;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Kimo on 8/19/14.
 */
public class ColorPaletteFragment extends ProgressFragment {

    public static final String TAG = ColorPaletteFragment.class.getSimpleName();

    private ImageView mImage;
    private TextView mElapsedTimeView;
    private LinearLayout mPaletteContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_palette, container, false);
        configure(view);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setContentShown(true);

        getFragmentManager().beginTransaction().replace(R.id.info_area, new ColorPaletteParamsFragment()).commit();
    }

    @Override
    public void onResume() {
        super.onResume();

        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(CalculateColorPaletteClicked event) {
        performCalculus(event.getNumberOfColors());
    }

    private void configure(View view) {
        mImage = (ImageView) view.findViewById(R.id.img);
    }

    private void performCalculus(int numberOfColors) {

        Alexei.with(getActivity())
                .analyze(mImage)
                .perform(new ColorPaletteCalculus(Utils.getBitmapFromImageView(mImage),numberOfColors))
                .showMe(new Answer<List<Integer>>() {
                    @Override
                    public void beforeExecution() {
                        setContentShown(false);
                    }

                    @Override
                    public void afterExecution(List<Integer> answer, long elapsedTime) {

                        try {
                            getFragmentManager().beginTransaction().replace(R.id.info_area, ColorPaletteResultsFragment.newInstance((java.util.ArrayList<Integer>) answer, elapsedTime)).commit();
                            setContentShown(true);
                        } catch (NullPointerException e){}
                    }

                    @Override
                    public void ifFails(Exception error) {}
                });
    }
}
