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
import com.kimo.lib.alexei.ImageProcessingThing;
import com.kimo.lib.alexei.Result;

/**
 * Created by Kimo on 8/19/14.
 */
public class DominantColorFragment extends ProgressFragment {

    private ImageView mImage;
    private View mDominantColorView;
    private TextView mElapsedTimeView;
    private CalculusExecutor mBackgroundThread;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dominant_color, container, false);

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
    public void onResume() {
        super.onResume();

        performCalculus();
    }

    @Override
    public void onPause() {
        super.onPause();

        mBackgroundThread.cancel(true);
        mBackgroundThread = null;
    }

    private void configure(View view) {

        setHasOptionsMenu(true);

        mImage = (ImageView) view.findViewById(R.id.imageView);
        mDominantColorView = view.findViewById(R.id.dominant_color);
        mElapsedTimeView = (TextView) view.findViewById(R.id.elapsed_time);
    }

    private void performCalculus() {
        mBackgroundThread = new CalculusExecutor();
        mBackgroundThread.execute();
    }

    private class CalculusExecutor extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if(!isCancelled())
                setContentShown(false);

        }

        @Override
        protected Void doInBackground(Void... params) {

            if(!isCancelled()) {
                Result dominantColorResult = Alexei.with(getActivity())
                        .analize(mImage)
                        .calculate(ImageProcessingThing.DOMINANT_COLOR)
                        .andGiveMeTheResults();

                mDominantColorView.setBackgroundColor((Integer) dominantColorResult.getResult());
                mElapsedTimeView.setText(new StringBuilder().append(dominantColorResult.getElapsedTime()).append(" milliseconds"));
            }



            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(!isCancelled())
                setContentShown(true);
        }
    }
}
