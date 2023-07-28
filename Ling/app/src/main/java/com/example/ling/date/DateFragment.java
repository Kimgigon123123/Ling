package com.example.ling.date;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ling.databinding.FragmentDateBinding;
import com.example.ling.date.dibs.DibsActivity;
import com.example.ling.date.festival.FestivalActivity;
import com.example.ling.date.festival.FestivalItemAdapter;
import com.example.ling.date.restaurant.RestaurantActivity;
import com.example.ling.date.restaurant.RestaurantItemAdapter;
import com.example.ling.date.tour.TourActivity;
import com.example.ling.date.tour.TourItemAdapter;

public class DateFragment extends Fragment {

    FragmentDateBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDateBinding.inflate(inflater, container, false);

        binding.recvTour.setAdapter(new TourItemAdapter(getContext()));
        binding.recvRestaurant.setAdapter(new RestaurantItemAdapter(getContext()));
        binding.recvFestival.setAdapter(new FestivalItemAdapter(getContext()));
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