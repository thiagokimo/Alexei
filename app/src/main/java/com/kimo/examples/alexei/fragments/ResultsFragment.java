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
public class ResultsFragment extends Fragment {

    public static final String TAG = ResultsFragment.class.getSimpleName();
    public static final String ELAPSED_TIME = TAG + ".ELAPSED_TIME";

    private long mElapsedTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null)
            mElapsedTime = getArguments().getLong(ELAPSED_TIME);
    }

    public static ResultsFragment newInstance(long elapsedTime) {
        ResultsFragment fragment = new ResultsFragment();

        Bundle args = new Bundle();
        args.putLong(ELAPSED_TIME, elapsedTime);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);

        configure(view);

        return view;
    }

    private void configure(View view) {
        TextView elapsedTimeLabel = (TextView)view.findViewById(R.id.elapsed_time);
        elapsedTimeLabel.setText(new StringBuilder().append(mElapsedTime).append(" milliseconds"));
    }
}
