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

    public static final int IMAGE_FROM_GALLERY = 0;

    private ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configure();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.report_container, ColorPalleteFragment.newInstance((Integer) mImageView.getTag()))
                .commit();
    }

    private void configure() {
        mImageView = (ImageView) findViewById(R.id.imageView);
        mImageView.setTag(R.drawable.katheryn_winnick);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
        if (requestCode == IMAGE_FROM_GALLERY  && resultCode == Activity.RESULT_OK) {
            Picasso.with(this).load(data.getData()).into(mImageView);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.report_container, ColorPalleteFragment.newInstance((Integer) mImageView.getTag()))
                    .commit();
        }
        else
            Toast.makeText(this, "Error selecting the image from gallery.", Toast.LENGTH_SHORT).show();
    }
}
