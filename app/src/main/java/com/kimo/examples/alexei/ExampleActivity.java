package com.kimo.examples.alexei;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.kimo.lib.alexei.Alexei;
import com.kimo.lib.alexei.Answer;
import com.kimo.lib.alexei.Utils;
import com.kimo.lib.alexei.calculus.GreyScaleCalculus;

/**
 * Created by Kimo on 9/10/15.
 */
public class ExampleActivity extends FragmentActivity {

    private View mainView, progressView;
    private ImageView image;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        configure();
    }

    private void configure() {
        mainView = findViewById(R.id.main_container);
        progressView = findViewById(R.id.progress);
        image = (ImageView) findViewById(R.id.image);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alexei.with(ExampleActivity.this)
                        .analyze(image)
                        .perform(new GreyScaleCalculus(Utils.getBitmapFromImageView(image)))
                        .showMe(new Answer<Bitmap>() {
                            @Override
                            public void beforeExecution() {
                                mainView.setVisibility(View.GONE);
                                progressView.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void afterExecution(Bitmap answer, long elapsedTime) {
                                image.setImageBitmap(answer);
                                mainView.setVisibility(View.VISIBLE);
                                progressView.setVisibility(View.GONE);
                            }

                            @Override
                            public void ifFails(Exception error) {
                                mainView.setVisibility(View.VISIBLE);
                                progressView.setVisibility(View.GONE);

                                Toast.makeText(ExampleActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
