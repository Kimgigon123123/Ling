package com.example.ling.store.myinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.ling.databinding.ActivityStoreMyinfoBinding;
import com.example.ling.store.ChargeCashActivity;
import com.example.ling.store.ChargeVO;
import com.example.ling.store.CompleteDialog;

public class StoreMyinfoActivity extends AppCompatActivity {




    ActivityStoreMyinfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        binding=ActivityStoreMyinfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//

        binding.recvZzim.setAdapter(new ZZimAdapter(this));
        binding.recvZzim.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        binding.recvBuylist.setAdapter(new BuylistAdapter(this));
        binding.recvBuylist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        binding.recvReturn.setAdapter(new ReturnAdapter(this));
        binding.recvReturn.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        if(ChargeVO.isCharge){
            Dialog dialog = new CompleteDialog(this,"charge");
                dialog.show();
                ChargeVO.isCharge=false;
        }


        binding.imgvBefore.setOnClickListener(v -> {
            finish();
        });

        binding.btnCharge.setOnClickListener(v->{


            Intent intent = new Intent(StoreMyinfoActivity.this, ChargeCashActivity.class);
            startActivity(intent);
        });

        binding.imgvIntoZZim.setOnClickListener(v->{
            Intent intent = new Intent(StoreMyinfoActivity.this, ZZimActivity.class);
            startActivity(intent);
        });

        binding.imgvIntoBylist.setOnClickListener(v->{
            Intent intent = new Intent(StoreMyinfoActivity.this, ZZimActivity.class);
            intent.putExtra("buylist","buylist");
            startActivity(intent);
        });

        binding.imgvReturn.setOnClickListener(v->{
            Intent intent = new Intent(StoreMyinfoActivity.this, ZZimActivity.class);
            intent.putExtra("return","return");
            startActivity(intent);
        });

    }

}

