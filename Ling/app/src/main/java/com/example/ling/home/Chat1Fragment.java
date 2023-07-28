package com.example.ling.home;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

import com.example.ling.R;
import com.example.ling.databinding.FragmentChat1Binding;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;


public class Chat1Fragment extends Fragment {
    FragmentChat1Binding binding;

    ListView listView;
    ArrayList<ChatDTO> list=new ArrayList<>();
    Chat1Adapter adapter;

    //Firebase Database 관리 객체참조변수
    FirebaseDatabase firebaseDatabase;

    //'chat'노드의 참조객체 참조변수
    DatabaseReference chatRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChat1Binding.inflate(inflater, container, false);

        adapter=new Chat1Adapter(list,getLayoutInflater());
        binding.listView.setAdapter(adapter);

        //Firebase DB관리 객체와 'caht'노드 참조객체 얻어오기
        firebaseDatabase=FirebaseDatabase.getInstance();
        chatRef=firebaseDatabase.getReference("chat");

        //firebaseDB에서 채팅 메세지들 실시간 읽어오기..
        //'chat'노드에 저장되어 있는 데이터들을 읽어오기
        //chatRef에 데이터가 변경되는 것으 듣는 리스너 추가

        binding.btnChat1.setOnClickListener(v -> {
            //firebase DB에 저장할 값들( 닉네임, 메세지, 프로필 이미지URL, 시간)
            String nickName= G.nickName;
            String message= binding.edtChat.getText().toString();
            String profileUrl= G.profileUrl;

            //메세지 작성 시간 문자열로..
            Calendar calendar= Calendar.getInstance(); //현재 시간을 가지고 있는 객체
            String time=calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE); //14:16

            //firebase DB에 저장할 값(MessageItem객체) 설정
            ChatDTO messageItem= new ChatDTO(nickName,message,profileUrl);
            //'char'노드에 MessageItem객체를 통해
            chatRef.push().setValue(messageItem);

            //EditText에 있는 글씨 지우기
            binding.edtChat.setText("");
        });

        chatRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    ChatDTO dto = snapshot.getValue(ChatDTO.class);

                    list.add(dto);

                    adapter.notifyDataSetChanged();
                    listView.setSelection(list.size()-1);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return binding.getRoot();


    }

}