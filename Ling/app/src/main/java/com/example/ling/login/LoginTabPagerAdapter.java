package com.example.ling.login;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ling.join.InsertMateFragment;
import com.example.ling.join.JoinCompleteFragment;
import com.example.ling.join.JoinMiddleFragment;
import com.example.ling.join.JoinProfileFragment;
import com.example.ling.join.JoinStep1Fragment;

public class LoginTabPagerAdapter extends FragmentStateAdapter {

    Fragment[] findFragments = new Fragment[] {
            new LoginFragment(),
            new LoginFindFragment(),
            new Loginfind_IdFragment(),
            new LoginTemp_PwFragment(),
            new LoginMiddleFragment(),
            new InsertLoginMateFragment(),
            new JoinCompleteFragment()};



    public LoginTabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
            return findFragments[position];

    }

    @Override
    public int getItemCount() {
        return findFragments.length;
    }
}
