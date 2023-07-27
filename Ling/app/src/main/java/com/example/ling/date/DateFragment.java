package com.example.ling.date;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ling.databinding.FragmentDateBinding;

public class DateFragment extends Fragment {

    FragmentDateBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDateBinding.inflate(inflater, container, false);

        binding.recvTour.setAdapter(new TourItemAdapter());
        binding.recvRestaurant.setAdapter(new RestaurantItemAdapter());
        binding.recvFestival.setAdapter(new FestivalItemAdapter());
        binding.recvTour.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.recvRestaurant.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.recvFestival.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        binding.imgvDibs.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DibsActivity.class);
            startActivity(intent);

        });

        binding.tvTmore.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TourActivity.class);
            startActivity(intent);
        });

        binding.tvRmore.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), RestaurantActivity.class);
            startActivity(intent);
        });

        binding.tvFmore.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), FestivalActivity.class);
            startActivity(intent);
        });


        return binding.getRoot();
    }
}