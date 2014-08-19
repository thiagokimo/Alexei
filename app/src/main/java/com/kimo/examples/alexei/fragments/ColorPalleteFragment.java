package com.kimo.examples.alexei.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devspark.progressfragment.ProgressFragment;
import com.kimo.examples.alexei.R;
import com.kimo.lib.alexei.Alexei;
import com.kimo.lib.alexei.ImageProcessingThing;
import com.kimo.lib.alexei.Result;

import java.util.List;

/**
 * Created by Kimo on 8/19/14.
 */
public class ColorPalleteFragment extends ProgressFragment {

    private ImageView mImage;
    private TextView mElapsedTimeView;
    private LinearLayout mPalleteContainer;
    private Handler mHandler;
    private Runnable mShowContentRunnable = new Runnable() {

        @Override
        public void run() {

            Result colorPalleteResult = Alexei.with(getActivity())
                    .analize(mImage)
                    .calculate(ImageProcessingThing.COLOR_PALLETE)
                    .andGiveMeTheResults();

            List<Integer> mPallete = (List<Integer>) colorPalleteResult.getResult();
            fillPalleteColors(mPallete);

            mElapsedTimeView.setText(new StringBuilder().append(colorPalleteResult.getElapsedTime()).append(" milliseconds"));
            setContentShown(true);
        }

    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pallete, container, false);
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
        mPalleteContainer = (LinearLayout) view.findViewById(R.id.pallete_container);
        mElapsedTimeView = (TextView) view.findViewById(R.id.elapsed_time);
    }

    private void fillPalleteColors(List<Integer> colors) {

        LayoutInflater inflater = getActivity().getLayoutInflater();

        for(int color : colors) {

            View palleteColor = inflater.inflate(R.layout.item_pallete, mPalleteContainer, false);
            palleteColor.setBackgroundColor(color);

            mPalleteContainer.addView(palleteColor);
        }
    }
}
