package com.example.ling.photo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.example.ling.calendar.CalendarAdapter;
import com.example.ling.calendar.ScheAddVO;
import com.example.ling.common.CommonConn;
import com.example.ling.common.RetClient;
import com.example.ling.common.RetInterface;
import com.example.ling.databinding.ActivityPhotoBinding;
import com.example.ling.home.CameraDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoActivity extends AppCompatActivity {

    ActivityPhotoBinding binding;
    private CameraDialog cameraDialog;
    private final int REQ_Gallery = 1000;
    ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhotoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        select();
//        Glide.with(this).load("http://192.168.0.28/hanul/img//andimg.jpg").into(binding.imgvElbumCamera);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }


        binding.imgvElbumCamera.setOnClickListener(v->{//imgv_photos               showGallery();
            showCamera();
        });



        // D:\Ling\Ling\image\photo 경로에서 이미지들의 파일명을 가져와서 어댑터에 전달합니다.
//        PhotoAdapter adapter = new PhotoAdapter(this, getImagePaths());
//        binding.gridGallery.setAdapter(adapter);




//        String filePath = "D:\\WorkSpace\\Ling\\image\\photo\\all"; // 이미지 파일 경로
//        String tvText = binding.imgv.getText().toString(); // tv_text 값
//// 파일 경로에서 파일명 추출
//        String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
//
//        if (fileName.equals("image_" + tvText + ".png")) {
//
//        }




        binding.imgvFolderAdd.setOnClickListener(view -> {
            AlertDialog.Builder follder = new AlertDialog.Builder(this);
            follder.setTitle("생성할 폴더명");
            final EditText name = new EditText(this);
            follder.setView(name);
            follder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) { //확인 버튼을 클릭했을때
                    String folderName = name.getText().toString().trim();

                    if (!folderName.isEmpty()) {
                        // 특정 경로와 입력된 폴더 이름으로 폴더 경로를 생성
                        String folderPath = "D:\\WorkSpace\\Ling\\image\\" + folderName;

                        // 폴더 생성 로직을 호출하여 폴더 생성
                        createFolder(folderPath);
                    }
                }
            });
            follder.setNegativeButton("취소",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) { //취소 버튼을 클ㅣ
                }
            });
            follder.show();
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                //액티비티(카메라 액티비티)가 종료되면 콜백으로 데이터를 받는 부분. (기존에는 onActivityResult메소드가 실행/ 현재는 해당 메소드)
                Glide.with(PhotoActivity.this).load(camera_uri).into(binding.imgvElbumCamera);
                File file = new File(getRealPath(camera_uri));

                if(file!=null){
                    RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
                    MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", "test.jpg", fileBody);
                    RetInterface api = new RetClient().getRet().create(RetInterface.class);
                    api.clientSendFile("file.f", new HashMap<>(), filePart).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            t.getMessage();
                        }
                    });
                }
            }
        });
    }



    Uri camera_uri = null;


    public void showCamera(){
        //ContentResolver(). 앱 -> 컨텐트리졸버(작업자) -> 미디어 저장소
        camera_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_Gallery && resultCode == RESULT_OK){
            //갤러리 액티비티가 종료되었다. (사용자가 사진을 선택했는지)
            Log.d("갤러리", "onActivityResult: " + data.getData());
            Log.d("갤러리", "onActivityResult: " + data.getData().getPath());
            Glide.with(this).load(data.getData()).into(binding.imgvElbumCamera); //갤러리 이미지가 잘 붙는지??
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
                    t.getMessage();
                }
            });

            //MultiPart <- Spring 처리
            //form태그 : 태그 사이에 있는 모든 입력 양식을 감싸는 태그로, name, action..등의 속성을 가지고 전송용 태그.
            //encType : 폼데이터 ↑ 서버로 전송될 때 "파일을 담고 있다면" 데이터의 인코딩 과정이 필요하다.
            //multipart/form-data <- 파일과 데이터는 담겨지는 영역이 다르기때문에 여러부분(Multi)Body(Part)


        }
    }



    public String getRealPath(Uri contentUri){
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};//
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Cursor cursor = getContentResolver().query(contentUri, proj, null, null);

            if(cursor.moveToFirst()){
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                res = cursor.getString(column_index);
            }
            cursor.close(); // 다 사용했으니 닫음.
        }

        Log.d("TAG", "getRealPath: 커서" + res);
        return res;
    }



        public void insert(){
        CommonConn conn = new CommonConn(this, "folder_insert");
        conn.onExcute((isResult, data) -> {
            ArrayList<FolderVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<FolderVO>>(){}.getType());
            Log.d("리스트사이즈", "select: " + list.size());
            //if문으로 list의 사이즈처리 해야함.

            FolderAdapter adapter = new FolderAdapter(list);



            binding.gridGallery.setAdapter(adapter);
            binding.gridGallery.setLayoutManager(new LinearLayoutManager(this));

        });
    }

    public void select(){
        CommonConn conn = new CommonConn(this, "folder_list");
        conn.onExcute((isResult, data) -> {
            ArrayList<FolderVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<FolderVO>>(){}.getType());
            Log.d("리스트사이즈", "select: " + list.size());
            //if문으로 list의 사이즈처리 해야함.
            FolderAdapter adapter = new FolderAdapter(list);


            binding.gridGallery.setAdapter(adapter);
            binding.gridGallery.setLayoutManager(new LinearLayoutManager(this));

        });
    }


//        public void select(){
//        CommonConn conn = new CommonConn(this, "photo_list");
//        conn.onExcute((isResult, data) -> {
//            ArrayList<PhotoVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<PhotoVO>>(){}.getType());
//            Log.d("리스트사이즈", "select: " + list.size());
//            //if문으로 list의 사이즈처리 해야함.
//            PhotoAdapter adapter = new PhotoAdapter(this, list);
//
//
//            binding.gridGallery.setAdapter(adapter);
//            binding.gridGallery.setLayoutManager(new GridLayoutManager(this, 3));
//
//        });
//    }




    // 폴더 생성 로직
    private void createFolder(String folderPath) {
        File folder = new File(folderPath);

        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            if (created) {
                System.out.println("폴더가 생성되었습니다.");
                insert();
            } else {
                System.out.println("폴더 생성에 실패하였습니다.");
            }
        }
    }
}