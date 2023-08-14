package com.example.ling.store.myinfo;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ling.MainActivity;
import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;

public class AddressMainActivity extends AppCompatActivity {

    EditText mEtAddress;
    Button btn_cancel,btn_ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_main);

        mEtAddress = findViewById(R.id.et_address);
        btn_ok = findViewById(R.id.btn_ok);
        btn_cancel = findViewById(R.id.btn_cancel);


        //blok touch
        mEtAddress.setFocusable(false);
        mEtAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //주소 검색 웹뷰 화면으로 이동
                Intent intent = new Intent(AddressMainActivity.this,AddressSearchActivity.class);
                getSearchResult.launch(intent);
            }
        });

        btn_ok.setOnClickListener(v -> {
            if(mEtAddress.getText().toString().equals("")){
                Toast.makeText(this, "주소를 입력해주세요", Toast.LENGTH_SHORT).show();
            }
            else{

                CommonConn conn = new CommonConn(this,"store_update_address");
                conn.addParamMap("id", CommonVar.loginInfo.getId());
                conn.addParamMap("address",mEtAddress.getText().toString());
                conn.onExcute((isResult, data) -> {

                        });
                finish();
            }
        });

        btn_cancel.setOnClickListener(v -> {
            finish();
        });


    }

    private final ActivityResultLauncher<Intent> getSearchResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode()==RESULT_OK){
                    if(result.getData() != null) {
                        String data = result.getData().getStringExtra("data");
                        mEtAddress.setText(data);
                    }
                }
            }
    );
}