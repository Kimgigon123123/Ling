package com.example.ling.board;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.databinding.ActivityNoticeContextBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Board_DetailtActivity extends AppCompatActivity {
    ActivityNoticeContextBinding binding;
    MaterialDialog mAnimatedDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoticeContextBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvBefore.setOnClickListener(v->{
            finish();
        });
        UserContentselect();

        binding.btnDelete.setOnClickListener(v->{

                mAnimatedDialog = new MaterialDialog.Builder(this)
                        .setTitle("Delete?")
                        .setMessage("Are you sure want to delete this file?")
                        .setCancelable(false)
                        .setPositiveButton("Delete", R.drawable.ic_delete, new MaterialDialog.OnClickListener() {
                            @Override
                            public void onClick(com.example.ling.board.interfaces.DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "Deleted!", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                                //delete();
                            }
                        })
                        .setNegativeButton("Cancel", R.drawable.ic_close, new MaterialDialog.OnClickListener() {
                            @Override
                            public void onClick(com.example.ling.board.interfaces.DialogInterface dialogInterface, int which) {
                                Toast.makeText(getApplicationContext(), "Cancelled!", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        })
                        .setAnimation("delete_anim.json")
                        .build();

            mAnimatedDialog.show();
                //mAnimatedDialog.setOnClickListener(this);
            //delete();
        });


//        String board_no =  getIntent().getStringExtra("board_no");
//        String board_cd =  getIntent().getStringExtra("board_cd");
//        FragmentManager manager = getSupportFragmentManager();
//        manager.beginTransaction().replace(R.id.container, new ContentFragment(board_no, board_cd)).commit();
//


    }



    public void UserContentselect() {
        CommonConn conn = new CommonConn(this, "board.usercontent");
        String board_no =  getIntent().getStringExtra("board_no");
        String board_cd =  getIntent().getStringExtra("board_cd");
        conn.addParamMap("id", board_no);
        conn.addParamMap("board_cd", board_cd);
        conn.onExcute((isResult, data) -> {
            BoardVO boardvo = new Gson().fromJson(data, new TypeToken<BoardVO>() {
            }.getType());

            binding.btnUpdate.setOnClickListener(v->{
                Intent intent = new Intent(this, Board_Write_ContentActivity.class);
                intent.putExtra("vo",boardvo);
                intent.putExtra("type", "update");
                startActivity(intent);
            });

            Board_User_ContentAdapter adapter = new Board_User_ContentAdapter(boardvo);
            binding.recvContent.setAdapter(adapter);
            binding.recvContent.setLayoutManager(new LinearLayoutManager(this));
        });
    }
    public void delete() {
        CommonConn conn = new CommonConn(this, "board.delete");
        String board_no =  getIntent().getStringExtra("board_no");
        conn.addParamMap("id", board_no);
        conn.onExcute((isResult, data) -> {

        });
    }
    @Override
    public void onStart() {
        super.onStart();
        UserContentselect();
    }
}