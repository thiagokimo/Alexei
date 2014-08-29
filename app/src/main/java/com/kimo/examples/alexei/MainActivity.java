package com.kimo.examples.alexei;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;

import com.kimo.examples.alexei.fragments.BlurFragment;
import com.kimo.examples.alexei.fragments.ColorPaletteFragment;
import com.kimo.examples.alexei.fragments.DominantColorFragment;
import com.kimo.examples.alexei.fragments.NavigationDrawerFragment;


public class MainActivity extends FragmentActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configure();
    }

    private void configure() {
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

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
                            .replace(R.id.container, new ColorPaletteFragment(), ColorPaletteFragment.TAG)
                        .commit();
                break;

            case NavigationDrawerFragment.FRAGMENT_BLUR:
                getSupportFragmentManager()
                        .beginTransaction()
                            .replace(R.id.container, new BlurFragment(), BlurFragment.TAG)
                        .commit();
                break;
        }

    }
}
