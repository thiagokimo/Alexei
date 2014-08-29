package com.kimo.examples.alexei.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.devspark.progressfragment.ProgressFragment;
import com.kimo.examples.alexei.R;
import com.kimo.examples.alexei.events.CalculateDominantColorClicked;
import com.kimo.lib.alexei.Alexei;
import com.kimo.lib.alexei.Answer;
import com.kimo.lib.alexei.ImageProcessingThing;

import de.greenrobot.event.EventBus;

/**
 * Created by Kimo on 8/19/14.
 */
public class DominantColorFragment extends ProgressFragment {

    public static final String TAG = DominantColorFragment.class.getSimpleName();

    private ImageView mImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dominant_color, container, false);

        configure(view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setContentShown(true);

        getFragmentManager().beginTransaction().replace(R.id.info_area, new DominantColorParamsFragment()).commit();
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.main, menu);
    }

    public void onEventMainThread(CalculateDominantColorClicked event) {
        performCalculus();
    }

    private void configure(View view) {
        mImage = (ImageView) view.findViewById(R.id.img);
    }

    private void performCalculus() {

        Alexei.with(getActivity())
                .analize(mImage)
                .perform(ImageProcessingThing.DOMINANT_COLOR)
                .showMe(new Answer<Integer>() {
                    @Override
                    public void beforeExecution() {
                        setContentShown(false);
                    }

                    @Override
                    public void afterExecution(Integer answer, long elapsedTime) {
                        getFragmentManager().beginTransaction().replace(R.id.info_area, DominantColorResultsFragment.newInstance(answer, elapsedTime)).commit();
                        setContentShown(true);
                    }

                    @Override
                    public void ifFails(Exception error) {

                    }
                });
    }



}
