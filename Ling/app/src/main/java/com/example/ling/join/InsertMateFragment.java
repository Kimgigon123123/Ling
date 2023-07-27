package com.example.ling.join;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.R;
import com.example.ling.databinding.FragmentInsertMateBinding;


public class InsertMateFragment extends Fragment {

    FragmentInsertMateBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInsertMateBinding.inflate(inflater, container, false);
        binding.btnNext.setOnClickListener(v->{
            ((JoinActivity) getActivity()).changeTab(4);
        });
        return binding.getRoot();
    }
}