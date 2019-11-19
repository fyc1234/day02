package com.example;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class VpMainAdapter extends FragmentPagerAdapter {
    List<Fragment> fs;
    List<String> titles;

    public VpMainAdapter(@NonNull FragmentManager fm, List<Fragment> fs, List<String> titles) {
        super(fm);
        this.fs = fs;
        this.titles = titles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fs.get(position);
    }

    @Override
    public int getCount() {
        return fs.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
