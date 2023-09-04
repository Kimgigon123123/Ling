package com.example.ling.photo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ling.R;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ItemRecvFolderBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.ViewHolder>{


    public FolderAdapter
            (ArrayList<FolderVO> list) {
        this.list = list;
    }

    ArrayList<FolderVO> list;


    Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ItemRecvFolderBinding binding = ItemRecvFolderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FolderAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
                h.binding.tvFolderTitle.setText(list.get(i).getFolder_name());

//                CommonConn conn = new CommonConn(context, "folder_list");
//                conn.addParamMap("couple", CommonVar.loginInfo.getCouple_num());
//
//                conn.onExcute((isResult, data) -> {
//                    Glide.with(context).load(list.get(i).getLast_photo()).into(h.binding.imgvFolder);
//                });


                h.binding.imgvFolderDelete.setOnClickListener(v -> {
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setTitle("앨범삭제");
                    alert.setMessage("정말로 삭제하시겠습니까?");

                    alert.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            CommonConn conn = new CommonConn(context, "file_delete");
                            // 추가적인 파라미터 설정
                            conn.addParamMap("couple_num", CommonVar.loginInfo.getCouple_num());
                            conn.addParamMap("folder_name", list.get(i).getFolder_name());

                            // 쿼리 실행
                            conn.onExcute(new CommonConn.JswCallBack() {
                                @Override
                                public void onResult(boolean isResult, String data) {
                                    // 추가 작업 수행
                                }
                            });

                            conn = new CommonConn(context, "folder_delete");
                            conn.addParamMap("folder_name", list.get(i).getFolder_name());
                            conn.addParamMap("couple_num", CommonVar.loginInfo.getCouple_num());
                            list.remove(i);
                            notifyDataSetChanged();
                            conn.onExcute(new CommonConn.JswCallBack() {


                                @Override
                                public void onResult(boolean isResult, String data) {

                                }
                            });
                        }
                    });

                    alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });


                    alert.show();
                });
                h.binding.lnFolder.setOnClickListener(v -> {
                    Intent intent = new Intent(context, PhotoListActivity.class);
                    intent.putExtra("folder_no" , list.get(i).getFolder_num());
                    intent.putExtra("name" , list.get(i).getFolder_name());

                    context.startActivity(intent);
                });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemRecvFolderBinding binding;

        public ViewHolder(@NonNull ItemRecvFolderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }




}
