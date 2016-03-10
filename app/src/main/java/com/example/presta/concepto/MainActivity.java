package com.example.presta.concepto;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.presta.concepto.ui.adapter.PageAdapter;
import com.example.presta.concepto.ui.fragments.TagArtistFragment;
import com.example.presta.concepto.ui.fragments.TopArtistsFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        setupViewPager();

        if (toolbar != null)
            setSupportActionBar(toolbar);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setupViewPager() {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), buildFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_play);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_action_name);

    }

    private ArrayList<Fragment> buildFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new TagArtistFragment());
        fragments.add(new TopArtistsFragment());

        return fragments;
    }
}
