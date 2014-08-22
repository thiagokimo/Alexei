package com.kimo.examples.alexei;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;

import com.kimo.examples.alexei.fragments.ColorPalleteFragment;
import com.kimo.examples.alexei.fragments.DominantColorFragment;
import com.kimo.examples.alexei.fragments.NavigationDrawerFragment;


public class MainActivity extends FragmentActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    public static final int IMAGE_FROM_GALLERY = 0;

    private NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configure();
    }

    private void configure() {
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();


        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
////        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
////        if (id == R.id.action_change_image) {
////
////            Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
////            startActivityForResult(i, IMAGE_FROM_GALLERY);
////
////            return true;
////        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == IMAGE_FROM_GALLERY  && resultCode == Activity.RESULT_OK) {
//            Picasso.with(this).load(data.getData()).into(mImageView);
//        }
//        else
//            Toast.makeText(this, "Error selecting the image from gallery.", Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        switch (position) {
            case NavigationDrawerFragment.FRAGMENT_DOMINANT_COLOR:
                getSupportFragmentManager()
                        .beginTransaction()
                            .replace(R.id.container, new DominantColorFragment(), DominantColorFragment.TAG)
                        .commit();
                break;
            case NavigationDrawerFragment.FRAGMENT_COLOR_PALLETE:
                getSupportFragmentManager()
                        .beginTransaction()
                            .replace(R.id.container, new ColorPalleteFragment(), ColorPalleteFragment.TAG)
                        .commit();
                break;
        }

    }
}
