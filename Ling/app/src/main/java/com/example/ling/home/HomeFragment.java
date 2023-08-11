package com.example.ling.home;

import static android.app.Activity.RESULT_OK;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ling.R;
import com.example.ling.calendar.CalendarActivity;
import com.example.ling.common.RetClient;
import com.example.ling.common.RetInterface;
import com.example.ling.databinding.FragmentHomeBinding;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    private final int REQ_Gallery = 1000;
    private final static int DEFAULT_IMG = R.drawable.man;
    ActivityResultLauncher<Intent> launcher; // <-- onCreate에서 초기화하면 오류발생.
    Uri camera_uri = null;

    private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/M/d");



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        Glide.with(this).load("http://192.168.0.28/Ling/Ling/images/photo/andimg.jpg").into(binding.imgvManProfile);
        binding.imgvPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), PhotoActivity.class);
            startActivity(intent);
        });

        binding.imgvCalendar.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), CalendarActivity.class);
            startActivity(intent);
        });

        binding.imgvManProfile.setOnClickListener(v -> {
            showDialog();
        });




        Date date = new Date();

        String date1 = mFormat.format(date); //날짜1
        String date2 = "2023/07/23"; //날짜2

        Date format1 = null;
        try {
            format1 = new SimpleDateFormat("yyyy/MM/dd").parse(date1);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date format2 = null;
        try {
            format2 = new SimpleDateFormat("yyyy/MM/dd").parse(date2);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        long diffSec = (format1.getTime() - format2.getTime()) / 1000; //초 차이
        String diffDays = String.valueOf(diffSec / (24*60*60)); //일자수 차이

        binding.loveDDay.setText(diffDays);




        return binding.getRoot();


    }

    public void showDialog(){
        String[] dialog_item = {"갤러리", "카메라", "기본이미지"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("사진 업로드 방식");
        builder.setSingleChoiceItems(dialog_item, -1, (dialog, i) -> {
            if(dialog_item[i].equals("갤러리")){
                //갤러리 로직
                showGallery();
            }else if(dialog_item[i].equals("카메라")){
                //카메라 로직
                showCamera();
            }else if(dialog_item[i].equals("기본이미지")){
                binding.imgvManProfile.setImageResource(DEFAULT_IMG);
            }
            dialog.dismiss();
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public void onStart() {
        super.onStart();
        launcher = getActivity().registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Glide.with(HomeFragment.this).load(camera_uri).into(binding.imgvManProfile);
                File file = new File(getRealPath(camera_uri));

                if (file != null) {

                    RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
                    MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", "test.jpg", fileBody);
                    RetInterface api = new RetClient().getRet().create(RetInterface.class);

                    api.clientSendFile("file.f", new HashMap<>(), filePart).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }

    public void showCamera(){
        //ContentResolver(). 앱 -> 컨텐트리졸버(작업자) -> 미디어 저장소
        camera_uri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, camera_uri);
        launcher.launch(cameraIntent);
    }

    public void showGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
//        startActivity(intent); //단순 실행 결과 알 수 없음
        startActivityForResult(intent, REQ_Gallery);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_Gallery && resultCode == RESULT_OK) {
            //갤러리 액티비티가 종료되었다. (사용자가 사진을 선택했는지)
            Log.d("갤러리", "onActivityResult: " + data.getData());
            Log.d("갤러리", "onActivityResult: " + data.getData().getPath());
            Glide.with(this).load(data.getData()).into(binding.imgvManProfile); //갤러리 이미지가 잘 붙는지??
            String img_path = getRealPath(data.getData());

            //MultiPart 형태로 전송 (File)
            RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), new File(img_path));
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", "test.jpg", fileBody);
            RetInterface api = new RetClient().getRet().create(RetInterface.class);

            api.clientSendFile("file.f", new HashMap<>(), filePart).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        }
    }

    public String getRealPath(Uri contentUri){
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};//
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null);

            if(cursor.moveToFirst()){
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                res = cursor.getString(column_index);
            }
            cursor.close(); // 다 사용했으니 닫음.
        }

        Log.d("TAG", "getRealPath: 커서" + res);
        return res;
    }

}