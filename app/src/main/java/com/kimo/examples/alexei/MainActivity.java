package com.kimo.examples.alexei;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class MainActivity extends FragmentActivity {

    public static final int FRAGMENT_COLOR_PALLETE = 0;

    public static final int IMAGE_FROM_GALLERY = 0;

    private ImageView mImageView;
//    private View mMainColor;
//    private LinearLayout mPalleteContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configure();

        showFragment(FRAGMENT_COLOR_PALLETE);
    }

    private void configure() {
        mImageView = (ImageView) findViewById(R.id.imageView);
        mImageView.setTag(R.drawable.katheryn_winnick);
//        mMainColor = findViewById(R.id.average_color);
//        mPalleteContainer = (LinearLayout) findViewById(R.id.pallete_container);
    }

//    private void doStuff() {
//        Integer calculatedMainColor = (Integer) Alexei.analize(mImageView).calculate(ImageProcessingThing.AVERAGE_RGB).andGiveMeTheResults();
//
//        List<Integer> calculatedColorPallete = (ArrayList<Integer>) Alexei.analize(mImageView).calculate(ImageProcessingThing.COLOR_PALLETE).andGiveMeTheResults();
//
//        fillPalleteColors(calculatedColorPallete);
//
//        mMainColor.setBackgroundColor(calculatedMainColor);
//    }

//    private void fillPalleteColors(List<Integer> colors) {
//
//        LayoutInflater inflater = getLayoutInflater();
//
//        for(int color : colors) {
//
//            View palleteColor = inflater.inflate(R.layout.item_pallete, mPalleteContainer, false);
//            palleteColor.setBackgroundColor(color);
//
//            mPalleteContainer.addView(palleteColor);
//        }
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_change_image) {

            Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, IMAGE_FROM_GALLERY);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_FROM_GALLERY  && resultCode == Activity.RESULT_OK)
            Picasso.with(this).load(data.getData()).into(mImageView);
        else
            Toast.makeText(this, "Error selecting the image from gallery.", Toast.LENGTH_SHORT).show();
    }

    private void showFragment(int fragment) {
        switch (fragment) {
            case FRAGMENT_COLOR_PALLETE:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.report_container, ColorPalleteFragment.newInstance((Integer) mImageView.getTag()))
                        .commit();
                break;
        }
    }
}
