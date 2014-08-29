package com.kimo.examples.alexei.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.kimo.examples.alexei.R;
import com.kimo.examples.alexei.events.CalculateBlurButtonClicked;

import de.greenrobot.event.EventBus;

/**
 * Created by Kimo on 8/29/14.
 */
public class BlurParamsFragment extends Fragment {

    private Button mCalculateButton;
    private SeekBar mSeekBar;
    private TextView mRadiusLabel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blur_params, container, false);

        configure(view);

        return view;
    }

    private void configure(View view) {

        mRadiusLabel = (TextView) view.findViewById(R.id.radius_value);

        mCalculateButton = (Button) view.findViewById(R.id.button);
        mCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new CalculateBlurButtonClicked(mSeekBar.getProgress()));
            }
        });

        mSeekBar = (SeekBar) view.findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mRadiusLabel.setText(new StringBuilder().append(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
