package com.example.ling.store.myinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityAddressBinding;
import com.example.ling.databinding.ActivityDeliveryBinding;
import com.example.ling.databinding.ActivityDetailAddBinding;

public class DetailAddActivity extends AppCompatActivity {

    ActivityDetailAddBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDetailAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCancel.setOnClickListener(v->{
            finish();
        });

        binding.btnOk.setOnClickListener(v->{

            CommonConn conn = new CommonConn(this,"store_update_detailadd");
            conn.addParamMap("id", CommonVar.loginInfo.getId());
            conn.addParamMap("detail_add",binding.etDetailAdd.getText().toString());
            conn.onExcute((isResult, data) -> {

            });
            finish();
        });
    }
}