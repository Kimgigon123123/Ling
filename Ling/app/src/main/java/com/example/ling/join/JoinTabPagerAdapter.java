package com.example.ling.join;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class JoinTabPagerAdapter extends FragmentStateAdapter {

    Fragment[] fragments = new Fragment[] {new JoinStep1Fragment(), new JoinProfileFragment(), new JoinMiddleFragment(), new InsertMateFragment(), new JoinCompleteFragment() };

    public JoinTabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments[position];
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }
}
