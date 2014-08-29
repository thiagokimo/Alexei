package com.kimo.examples.alexei.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kimo.examples.alexei.R;
import com.kimo.lib.alexei.calculus.ColorPalette;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kimo on 8/29/14.
 */
public class ColorPaletteResultsFragment extends Fragment {

    public static final String TAG = ColorPalette.class.getSimpleName();
    public static final String PALETTE = TAG + ".PALETTE";
    public static final String ELAPSED_TIME = TAG + ".ELAPSED_TIME";

    private List<Integer> mPalette;
    private long mElapsedTime;

    public ColorPaletteResultsFragment(){}

    public static ColorPaletteResultsFragment newInstance(ArrayList<Integer> palette, long elapsedTime) {
        ColorPaletteResultsFragment fragment = new ColorPaletteResultsFragment();

        Bundle args = new Bundle();
        args.putIntegerArrayList(PALETTE, palette);
        args.putLong(ELAPSED_TIME, elapsedTime);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            mPalette = getArguments().getIntegerArrayList(PALETTE);
            mElapsedTime = getArguments().getLong(ELAPSED_TIME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_palette_results, container, false);
        configure(view);
        return view;
    }

    private void configure(View view) {
        LinearLayout palleteContainer = (LinearLayout) view.findViewById(R.id.palette_container);
        TextView elapsedTime = (TextView) view.findViewById(R.id.elapsed_time);

        fillPalleteColors(mPalette, palleteContainer);
        elapsedTime.setText(new StringBuilder().append(mElapsedTime).append(" milliseconds"));
    }

    private void fillPalleteColors(List<Integer> colors, LinearLayout paletteContainer) {

        LayoutInflater inflater = getActivity().getLayoutInflater();

        for(int color : colors) {

            View palleteColor = inflater.inflate(R.layout.item_pallete, paletteContainer, false);
            palleteColor.setBackgroundColor(color);

            paletteContainer.addView(palleteColor);
        }
    }
}
