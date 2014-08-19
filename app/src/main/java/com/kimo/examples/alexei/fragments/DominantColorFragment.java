package com.kimo.examples.alexei.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devspark.progressfragment.ProgressFragment;
import com.kimo.examples.alexei.R;
import com.kimo.lib.alexei.Alexei;
import com.kimo.lib.alexei.ImageProcessingThing;
import com.kimo.lib.alexei.Result;

/**
 * Created by Kimo on 8/19/14.
 */
public class DominantColorFragment extends ProgressFragment {

    private ImageView mImage;
    private View mDominantColorView;
    private TextView mElapsedTimeView;
    private Handler mHandler;
    private Runnable mShowContentRunnable = new Runnable() {

        @Override
        public void run() {

            Result dominantColorResult = Alexei.with(getActivity())
                    .analize(mImage)
                    .calculate(ImageProcessingThing.DOMINANT_COLOR)
                    .andGiveMeTheResults();

            mDominantColorView.setBackgroundColor((Integer) dominantColorResult.getResult());
            mElapsedTimeView.setText(new StringBuilder().append(dominantColorResult.getElapsedTime()).append(" milliseconds"));
            setContentShown(true);
        }

    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dominant_color, container, false);

        configure(view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        mHandler = new Handler();
        mHandler.post(mShowContentRunnable);
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mShowContentRunnable);
    }

    private void configure(View view) {
        mImage = (ImageView) view.findViewById(R.id.imageView);
        mDominantColorView = view.findViewById(R.id.dominant_color);
        mElapsedTimeView = (TextView) view.findViewById(R.id.elapsed_time);
    }
}
