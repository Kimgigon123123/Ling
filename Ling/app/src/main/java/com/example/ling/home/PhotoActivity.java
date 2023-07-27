package com.example.ling.home;

import static java.security.AccessController.getContext;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ling.MainActivity;
import com.example.ling.R;
import com.example.ling.common.RetClient;
import com.example.ling.common.RetInterface;
import com.example.ling.databinding.ActivityPhotoBinding;

import java.io.File;
import java.util.HashMap;

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
        Glide.with(this).load("http://192.168.0.28/hanul/img//andimg.jpg").into(binding.imageView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        binding.btnCamera.setOnClickListener(v -> {
            cameraDialog = new CameraDialog(this);
            cameraDialog.show();
            cameraDialog.findViewById(R.id.ln_camera).setOnClickListener(v2->{//imgv_photos               showGallery();
                showCamera();
            });
            cameraDialog.findViewById(R.id.ln_photos).setOnClickListener(v2->{//imgv_photos               showGallery();
                showGallery();
            });
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                //액티비티(카메라 액티비티)가 종료되면 콜백으로 데이터를 받는 부분. (기존에는 onActivityResult메소드가 실행/ 현재는 해당 메소드)
                Glide.with(PhotoActivity.this).load(camera_uri).into(binding.imageView);
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
            Glide.with(this).load(data.getData()).into(binding.imageView); //갤러리 이미지가 잘 붙는지??
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






            //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.actionbar, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    public class PhotoAdapter extends BaseAdapter {
//
//        Context context;
//
//        public PhotoAdapter(Context context) {
//            this.context = context;
//        }
//
//        Integer[] exam = {
//                R.drawable.photo, R.drawable.photo, R.drawable.photo, R.drawable.photo, R.drawable.photo,
//                R.drawable.photo, R.drawable.photo, R.drawable.photo, R.drawable.photo, R.drawable.photo,
//                R.drawable.photo, R.drawable.photo, R.drawable.photo, R.drawable.photo, R.drawable.photo,
//                R.drawable.photo, R.drawable.photo, R.drawable.photo, R.drawable.photo, R.drawable.photo,
//                R.drawable.photo, R.drawable.photo, R.drawable.photo, R.drawable.photo, R.drawable.photo,
//                R.drawable.photo, R.drawable.photo, R.drawable.photo, R.drawable.photo, R.drawable.photo
//        };
//
//        @Override
//        public int getCount() {
//            return exam.length;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return position;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ImageView imageView = new ImageView(context);
//            imageView.setLayoutParams(new ViewGroup.LayoutParams(200, 300));
//            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        imageView.setPadding(5,5,5,5);
//
//            imageView.setImageResource(exam[position]);
//
//            imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    View dialogView = View.inflate(PhotoActivity.this, R.layout.photo_dialog, null);
//                    AlertDialog.Builder dlg = new AlertDialog.Builder(PhotoActivity.this);
//                    ImageView ivPic = dialogView.findViewById(R.id.imgv_photoDialog);
//                    ivPic.setImageResource(exam[position]);
//                    dlg.setTitle("큰 이미지");
//                    dlg.setIcon(R.drawable.ic_launcher_foreground);
//                    dlg.setView(dialogView);
//                    dlg.setNegativeButton("닫기", null);
//                    dlg.show();
//                }
//            });
//            return imageView;
//        }




}