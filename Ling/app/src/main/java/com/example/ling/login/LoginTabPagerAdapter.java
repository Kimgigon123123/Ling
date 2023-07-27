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

    Fragment[] fragments = new Fragment[] {new LoginFragment(), new LoginFindFragment(), new Loginfind_IdFragment(), new LoginTemp_IdFragment(), new LoginFind_PwFragment(), new LoginTemp_PwFragment(), new JoinCompleteFragment() };

    public LoginTabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
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
