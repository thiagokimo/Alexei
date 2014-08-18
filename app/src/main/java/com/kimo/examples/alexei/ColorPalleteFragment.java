package com.kimo.examples.alexei;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devspark.progressfragment.ProgressFragment;
import com.kimo.lib.alexei.Alexei;
import com.kimo.lib.alexei.ImageProcessingThing;
import com.kimo.lib.alexei.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kimo on 8/18/14.
 */
public class ColorPalleteFragment extends ProgressFragment {

    public static final String TAG = ColorPalleteFragment.class.getSimpleName();
    public static final String IMAGE_RESOURCE = TAG + ".IMAGE_RESOURCE";

    private int mImageResource;

    private TextView mElapsedTimeLabel;
    private List<Integer> mPallete = new ArrayList<Integer>();
    private LinearLayout mPalleteContainer;

    private Handler mHandler;
    private Runnable mShowContentRunnable = new Runnable() {

        @Override
        public void run() {

            ImageView image = new ImageView(getActivity());
            image.setImageResource(mImageResource);

            Result calculusResult = Alexei.with(getActivity())
                    .analize(image)
                    .calculate(ImageProcessingThing.COLOR_PALLETE)
                    .andGiveMeTheResults();

            mPallete = (List<Integer>) calculusResult.getResult();
            fillPalleteColors(mPallete);

            mElapsedTimeLabel.setText(new StringBuilder().append(calculusResult.getElapsedTime()).append(" milliseconds"));

            setContentShown(true);
        }

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null)
            mImageResource = getArguments().getInt(IMAGE_RESOURCE);
    }

    public static ColorPalleteFragment newInstance(int imageResource) {

        ColorPalleteFragment fragment = new ColorPalleteFragment();

        Bundle args = new Bundle();
        args.putInt(IMAGE_RESOURCE, imageResource);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.fragment_pallete, container, false);
        mPalleteContainer = (LinearLayout) mainView.findViewById(R.id.pallete_container);
        mElapsedTimeLabel = (TextView) mainView.findViewById(R.id.elapsed_time);

        return mainView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setContentShown(false);

        mHandler = new Handler();
        mHandler.postDelayed(mShowContentRunnable, 1000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHandler.removeCallbacks(mShowContentRunnable);
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
