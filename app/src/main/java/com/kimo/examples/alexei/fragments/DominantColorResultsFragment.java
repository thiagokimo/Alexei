package com.kimo.examples.alexei.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kimo.examples.alexei.R;

/**
 * Created by Kimo on 8/29/14.
 */
public class DominantColorResultsFragment extends Fragment {

    public static final String TAG = DominantColorResultsFragment.class.getSimpleName();
    public static final String DOMINANT_COLOR = TAG + ".DOMINANT_COLOR";
    public static final String ELAPSED_TIME = TAG + ".ELAPSED_TIME";

    private int mDominantColor;
    private long mElapsedTime;

    public DominantColorResultsFragment(){}

    public static DominantColorResultsFragment newInstance(int color, long elapsedTime) {
        DominantColorResultsFragment fragment = new DominantColorResultsFragment();

        Bundle args = new Bundle();
        args.putInt(DOMINANT_COLOR, color);
        args.putLong(ELAPSED_TIME, elapsedTime);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            mDominantColor = getArguments().getInt(DOMINANT_COLOR);
            mElapsedTime = getArguments().getLong(ELAPSED_TIME);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dominant_color_results, container, false);
        configure(view);
        return view;
    }

    private void configure(View view) {
        View dominantColor = view.findViewById(R.id.dominant_color);
        dominantColor.setBackgroundColor(mDominantColor);

        TextView elapsedTime = (TextView) view.findViewById(R.id.elapsed_time);
        elapsedTime.setText(new StringBuilder().append(mElapsedTime).append(" milliseconds"));
    }
}
