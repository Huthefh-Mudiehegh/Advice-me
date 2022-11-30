package com.huthfy.AdviceMe.ui.activity.displayAdvice;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class AdvicePagerAdapter extends FragmentStateAdapter {
    ArrayList<Fragment> fragments;

    public AdvicePagerAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<Fragment> fragments) {
        super(fragmentActivity);
        this.fragments = fragments;

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.d(DisplayAdviceActivity.markAsFav, "createFragment: " + position);
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
        Log.d(DisplayAdviceActivity.markAsFav, "setFragment: ");

    }

    public void updateFragment(Fragment fragment, int index) {
        fragments.set(index, fragment);
        Log.d(DisplayAdviceActivity.markAsFav, "AdvicePagerAdapter: sixth updateFragment:");
        notifyItemChanged(index);

    }



}
