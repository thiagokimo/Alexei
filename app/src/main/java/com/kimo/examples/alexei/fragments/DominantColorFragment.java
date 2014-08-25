package com.kimo.examples.alexei.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devspark.progressfragment.ProgressFragment;
import com.kimo.examples.alexei.R;
import com.kimo.lib.alexei.Alexei;
import com.kimo.lib.alexei.Answer;
import com.kimo.lib.alexei.ImageProcessingThing;

/**
 * Created by Kimo on 8/19/14.
 */
public class DominantColorFragment extends ProgressFragment {

    public static final String TAG = DominantColorFragment.class.getSimpleName();

    private ImageView mImage;
    private View mDominantColorView;
    private TextView mElapsedTimeView;
    private AsyncTask mThread;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dominant_color, container, false);

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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        performCalculus();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.main, menu);
    }

    private void configure(View view) {

        setHasOptionsMenu(true);

        mImage = (ImageView) view.findViewById(R.id.img);
        mDominantColorView = view.findViewById(R.id.dominant_color);
        mElapsedTimeView = (TextView) view.findViewById(R.id.elapsed_time);
    }

    private void performCalculus() {

        mThread = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {

                Alexei.analize(mImage)
                        .perform(ImageProcessingThing.DOMINANT_COLOR)
                        .andGiveMe(new Answer<Integer>() {
                            @Override
                            public void beforeExecution() {}

                            @Override
                            public void afterExecution(Integer answer, long elapsedTime) {
                                mDominantColorView.setBackgroundColor(answer);
                                mElapsedTimeView.setText(new StringBuilder().append(elapsedTime).append(" milliseconds"));

                            }

                            @Override
                            public void ifFails(Exception error) {

                            }
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
