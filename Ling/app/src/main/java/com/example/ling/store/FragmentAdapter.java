package com.example.ling.store;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentStateAdapter {
    //FragmentStateAdapter <- 프레그먼트와 ViewPager2 & recyclerview와 세트로 많이 사용되는 어댑터.
    //사용방법 자체가 엄청 간단함.
    ArrayList<Fragment>list;



    public FragmentAdapter(@NonNull FragmentActivity activity, ArrayList<Fragment> list) {
        super(activity);
        this.list=list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int i) {
        return list.get(i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
