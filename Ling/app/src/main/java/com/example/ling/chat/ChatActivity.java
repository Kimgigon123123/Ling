package com.example.ling.chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityChatBinding;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ChatVO> chatList;
    private String nick;

    private ChatAdapter mAdapter;

    ActivityChatBinding binding;
    private EditText EditText_chat;
    private Button Button_send;
    private DatabaseReference myRef;

    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh시 mm분", Locale.KOREA);

    //firebase 실시간 데이터베이스 연결
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());


        Button_send = binding.btnChat;
        EditText_chat = binding.edtChat;

        nick = CommonVar.loginInfo.getName();





        Date date = new Date(System.currentTimeMillis());
//        mRecyclerView.scrollToPosition(chatList.size()-1);
        Button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = EditText_chat.getText().toString(); //msg
                ChatVO chat = new ChatVO();
                if(msg != null) {

                    chat.setId(CommonVar.loginInfo.getId());
                    chat.setNickname(nick);
                    chat.setMessage(msg);
                    chat.setTime(mFormat.format(date));


                    mAdapter.notifyDataSetChanged();
                    binding.edtChat.setText("");
                    myRef = database.getReference().child("chat").child(CommonVar.loginInfo.getCouple_num());
                    myRef.push().setValue(chat);

                    // 특정 조건에 따라 채팅 데이터 삭제
//                    myRef.child("chat").child(CommonVar.loginInfo.getCouple_num()).removeValue();

                }


            }
        });


        mRecyclerView = binding.myRecyclerView;
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        chatList = new ArrayList<>();
        mAdapter = new ChatAdapter(chatList, this, nick);

        mRecyclerView.setAdapter(mAdapter);

        // Write a message to the database
        myRef = database.getReference();
        myRef = myRef.child("chat").child(CommonVar.loginInfo.getCouple_num());

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                ChatVO chat = snapshot.getValue(ChatVO.class);

                chat.setKey(snapshot.getKey());

                mAdapter.addChat(chat);
                mAdapter.notifyDataSetChanged();

                int newPosition = mAdapter.getItemCount() - 1;

                // 일정 딜레이 후 스크롤 실행
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView.scrollToPosition(newPosition);
                    }
                }, 100);

            }



            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                ChatVO chat = snapshot.getValue(ChatVO.class);
                myRef.child("chat").child(CommonVar.loginInfo.getCouple_num()).child(chat.getKey()).removeValue();
                chatList.clear();
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        setContentView(binding.getRoot());
    }




}