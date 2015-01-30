package com.helieu.androidtutorials;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.helieu.androidtutorials.recyclerview.RecyclerFragment;

public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private RecyclerFragment frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeActivity();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void initializeActivity() {
        //toolbar initialization

        initializeContentFragment();
        initializeActionBar();
        setupFABClickableAction();
    }

    private void initializeActionBar() {
        //replace the stock support action bar with the v7 support toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        initializeDrawer();
    }

    private void initializeDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                toggleFAB(true);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                toggleFAB(false);
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
    }

    private void initializeContentFragment() {
        frag = new RecyclerFragment();

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.contentFrame, frag)
                .commit();
    }

    private void toggleFAB(boolean setVisible) {
        Animation animation = AnimationUtils.loadAnimation(this, (setVisible) ? R.anim.grow_fade_in : R.anim.shrink_fade_out);
        View button = findViewById(R.id.addActionButton);

        if (setVisible) {
            button.setVisibility(View.VISIBLE);
            button.startAnimation(animation);
        } else {
            setAnimationListener(button, animation);
        }
    }

    private void setAnimationListener(final View button, final Animation animation) {
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                button.setVisibility(View.INVISIBLE);
            }
        });

        button.startAnimation(animation);
    }

    private void setupFABClickableAction() {
        findViewById(R.id.addActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag.invokeFABAction();
            }
        });
    }
}
