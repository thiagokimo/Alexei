package com.kimo.examples.alexei.fragments;

import android.os.AsyncTask;
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
import com.kimo.lib.alexei.Alexei;
import com.kimo.lib.alexei.Answer;
import com.kimo.lib.alexei.ImageProcessingThing;

import java.util.List;

/**
 * Created by Kimo on 8/19/14.
 */
public class ColorPalleteFragment extends ProgressFragment {

    public static final String TAG = ColorPalleteFragment.class.getSimpleName();

    private ImageView mImage;
    private TextView mElapsedTimeView;
    private LinearLayout mPalleteContainer;
    private AsyncTask mThread;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pallete, container, false);
        configure(view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mThread.cancel(true);
        mThread = null;
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

        performCalculus();
    }

    private void configure(View view) {

        setHasOptionsMenu(true);

        mImage = (ImageView) view.findViewById(R.id.img);
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

    private void performCalculus() {
        mThread = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {

                Alexei.analize(mImage)
                        .perform(ImageProcessingThing.COLOR_PALLETE)
                        .andGiveMe(new Answer<List<Integer>>() {
                            @Override
                            public void beforeExecution() {}

                            @Override
                            public void afterExecution(List<Integer> answer, long elapsedTime) {
                                fillPalleteColors(answer);
                                mElapsedTimeView.setText(new StringBuilder().append(elapsedTime).append(" milliseconds"));
                            }

                            @Override
                            public void ifFails(Exception error) {}
                        });

                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);

                setContentShown(true);
            }
        };

        mThread.execute();
    }
}
