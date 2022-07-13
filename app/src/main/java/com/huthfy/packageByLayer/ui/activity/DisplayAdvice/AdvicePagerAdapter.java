package com.huthfy.packageByLayer.ui.activity.DisplayAdvice;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class AdvicePagerAdapter extends FragmentStateAdapter {
    ArrayList<Fragment> fragments;

    public AdvicePagerAdapter(@NonNull FragmentActivity fragmentActivity,ArrayList<Fragment>fragments ) {
        super(fragmentActivity);
        this.fragments = fragments;

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }
}
