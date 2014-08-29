package com.kimo.examples.alexei.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.kimo.examples.alexei.R;
import com.kimo.examples.alexei.events.CalculateColorPaletteClicked;

import de.greenrobot.event.EventBus;

/**
 * Created by Kimo on 8/29/14.
 */
public class ColorPaletteParamsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_palette_params, container, false);

        configure(view);

        return view;
    }

    private void configure(View view) {
        final EditText editText = (EditText) view.findViewById(R.id.editText);

        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new CalculateColorPaletteClicked(Integer.parseInt(editText.getText().toString())));
            }
        });
    }

}
