package com.example.ling.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.ling.R;
import com.example.ling.capsule.CapsuleAddActivity;
import com.example.ling.databinding.ActivityNoteBinding;
import com.example.ling.databinding.FragmentStoreCoBinding;
import com.example.ling.store.StoreCoAdater;

public class NoteActivity extends AppCompatActivity {

    String[] items = {"전체", "개인메모", "커플메모"};
    ActivityNoteBinding binding;

    String order = "all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        binding.spinner.setAdapter(adapter);


        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String str = items[position];
                if (str.equals("전체")) {
                    order = "all";
                } else if (str.equals("커플메모")) {
                    order = "couple";
                } else if (str.equals("개인메모")) {
                    order = "personal";
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        binding.btnAdd.setOnClickListener(v->{
            Intent intent = new Intent(this, NoteAddActivity.class);
            startActivity(intent);
        });

    }

}