package com.kimo.examples.alexei.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.devspark.progressfragment.ProgressFragment;
import com.kimo.examples.alexei.R;
import com.kimo.examples.alexei.events.CalculateGreyScaleButtonClicked;
import com.kimo.lib.alexei.Alexei;
import com.kimo.lib.alexei.AlexeiUtils;
import com.kimo.lib.alexei.Answer;
import com.kimo.lib.alexei.calculus.GreyScaleCalculus;

import de.greenrobot.event.EventBus;

/**
 * Created by Kimo on 9/9/14.
 */
public class GreyScaleFragment extends ProgressFragment {

    public static final String TAG = GreyScaleFragment.class.getSimpleName();

    private ImageView mImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grey_scale, container, false);
        configure(view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setContentShown(true);

        getFragmentManager().beginTransaction().replace(R.id.info_area, new GreyScaleParamsFragment()).commit();
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

    public void onEventMainThread(CalculateGreyScaleButtonClicked event) {
        performCalculus();
    }

    private void performCalculus() {
        Alexei.with(getActivity())
                .analize(mImageView)
                .perform(new GreyScaleCalculus(AlexeiUtils.getBitmapFromImageView(mImageView)))
                .showMe(new Answer<Bitmap>() {
                    @Override
                    public void beforeExecution() {
                        setContentShown(false);
                    }

                    @Override
                    public void afterExecution(Bitmap answer, long elapsedTime) {
                        try {
                            mImageView.setImageBitmap(answer);
                            getFragmentManager().beginTransaction().replace(R.id.info_area, ResultsFragment.newInstance(elapsedTime)).commit();
                            setContentShown(true);
                        } catch (NullPointerException e){}
                    }

                    @Override
                    public void ifFails(Exception error) {}
                });
    }

    private void configure(View view) {
        mImageView = (ImageView) view.findViewById(R.id.img);
    }
}
