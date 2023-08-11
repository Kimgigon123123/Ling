package com.example.ling.home;

import static java.security.AccessController.getContext;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ling.MainActivity;
import com.example.ling.R;
import com.example.ling.common.RetClient;
import com.example.ling.common.RetInterface;
import com.example.ling.databinding.ActivityPhotoBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoActivity extends AppCompatActivity {

    ActivityPhotoBinding binding;
    SharedPreferences pref; //자바 변수나 static등의 멤버로 데이터를 저장하면 앱을 다시 실행시 처음부터 불러옴.(DB)
    SharedPreferences.Editor editor;

    ActivityResultLauncher<Intent> launcher; // <-- onCreate에서 초기화하면 오류발생.




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhotoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 1);
//        Glide.with(this).load("http://192.168.0.28/hanul/img//andimg.jpg").into(binding.imgvElbumCamera);

        binding.imgvElbumCamera.setOnClickListener(v -> {
//            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA )== PackageManager.PERMISSION_GRANTED){
                camera();
//            }
        });



        // D:\Ling\Ling\image\photo 경로에서 이미지들의 파일명을 가져와서 어댑터에 전달합니다.
        PhotoAdapter adapter = new PhotoAdapter(this, getImagePaths());
        binding.gridGallery.setAdapter(adapter);







        binding.imgvFolderAdd.setOnClickListener(view -> {
            AlertDialog.Builder follder = new AlertDialog.Builder(this);
            follder.setTitle("생성할 폴더명");
            final EditText name = new EditText(this);
            follder.setView(name);
            follder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) { //확인 버튼을 클릭했을때

                }
            });
            follder.setNegativeButton("취소",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) { //취소 버튼을 클ㅣ
                }
            });
            follder.show();
        });

    }

    Uri uri;

    public void camera(){
        try {
            ContentValues values = new ContentValues();
            uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            camera.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            camera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivity(camera);

        }catch (Exception e){

        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == REQ_Gallery && resultCode == RESULT_OK){
//            //갤러리 액티비티가 종료되었다. (사용자가 사진을 선택했는지)
//            Log.d("갤러리", "onActivityResult: " + data.getData());
//            Log.d("갤러리", "onActivityResult: " + data.getData().getPath());
//            Glide.with(this).load(data.getData()).into(binding.imgv); //갤러리 이미지가 잘 붙는지??
//            String img_path = getRealPath(data.getData());
//
//            //MultiPart 형태로 전송 (File)
//            RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), new File(img_path));
//            MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", "test.jpg", fileBody);
//            RetInterface api = new RetClient().getRet().create(RetInterface.class);
//            api.clientSendFile("file.f", new HashMap<>(), filePart).enqueue(new Callback<String>() {
//                @Override
//                public void onResponse(Call<String> call, Response<String> response) {
//
//                }
//
//                @Override
//                public void onFailure(Call<String> call, Throwable t) {
//                    t.getMessage();
//                }
//            });
//
//            //MultiPart <- Spring 처리
//            //form태그 : 태그 사이에 있는 모든 입력 양식을 감싸는 태그로, name, action..등의 속성을 가지고 전송용 태그.
//            //encType : 폼데이터 ↑ 서버로 전송될 때 "파일을 담고 있다면" 데이터의 인코딩 과정이 필요하다.
//            //multipart/form-data <- 파일과 데이터는 담겨지는 영역이 다르기때문에 여러부분(Multi)Body(Part)
//
//
//        }
//    }
//
//    //오라클 커서
//    public String getRealPath(Uri contentUri){
//        String res = null;
//        String[] proj = {MediaStore.Images.Media.DATA};//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            Cursor cursor = getContentResolver().query(contentUri, proj, null, null);
//
//            if(cursor.moveToFirst()){
//                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                res = cursor.getString(column_index);
//            }
//            cursor.close(); // 다 사용했으니 닫음.
//        }
//
//        Log.d("TAG", "getRealPath: 커서" + res);
//        return res;
//    }

    private final int REQ_PERMISSION = 1000;
    private final int REQ_PERMISSION_DENY = 1001;
    private void checkPermission(){
        ////        editor.putInt("permission" , 0);//데이터 0이 들어감.
        ////        editor.apply();//데이터를 확실히 넣음.
        int permission = pref.getInt("permission" , -1);
        permission++;
        editor.putInt("permission" , permission);
        editor.apply();

        String[] permissions = { Manifest.permission.CAMERA  ,
                Manifest.permission.ACCESS_MEDIA_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE

        } ;//카메라 권한을 String으로 가져옴.
        // ContextCompat(액티비티가 아닌곳) , ActivityCompat(액티비티)
        for(int i = 0 ; i <permissions.length ; i ++){
            //내가 모든 권한이 필요하다면 전체 권한을 하나씩 체크해서 허용 안됨이 있는경우 다시 요청을 하게 만든다.
            if(ActivityCompat.checkSelfPermission(this , permissions[i]) == PackageManager.PERMISSION_DENIED) {
                if(ActivityCompat.shouldShowRequestPermissionRationale(this , permissions[i])){
                    //최초 앱이 설치되고 실행 시 false가 나옴.=>사용자가 거부 후 true 재거부=>false
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("권한 요청").setMessage("권한이 반드시 필요합니다.!!미허용시 앱 사용 불가!");
                    builder.setPositiveButton("확인(권한허용)" , (dialog, which) -> {
                        //2.권한 설명 후 다시보여줌.
                        ActivityCompat.requestPermissions(this, permissions, REQ_PERMISSION_DENY);
                    });
                    builder.setNegativeButton("종료(권한허용불가)" , (dialog, which) -> {
                        finish();
                    });
                    builder.create().show();//<==넣어줘야함.
                }else{
                    //1.
                    ActivityCompat.requestPermissions(this, permissions, REQ_PERMISSION);
                }
                break;
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(REQ_PERMISSION == requestCode){
            for (int i = 0; i < grantResults.length; i++) {
                if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                    //거절된권한이있음.
                    checkPermission();
                    break;
                }
            }

            Log.d("권한", "onRequestPermissionsResult: 권한 요청 완료 ");
        }else if(REQ_PERMISSION_DENY == requestCode){
            for (int i = 0; i < grantResults.length; i++) {
                if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                    Log.d("권한", "onRequestPermissionsResult: 권한 요청 완료 ");
                    editor.putInt("permission", -2);
                    // 3.
                    viewSetting();
//                    checkPermission();
                }
            }


        }
    }

    public void viewSetting(){
        Intent intent = new Intent(Settings.ACTION_APPLICATION_SETTINGS);
        intent.setData(Uri.parse("package:" + getApplicationContext().getPackageName()));
        startActivity(intent);
    }


    private List<String> getImagePaths() {
        List<String> paths = new ArrayList<>();
        File folder = new File("D:\\Ling\\Ling\\image\\photo");
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        paths.add(file.getAbsolutePath());
                    }
                }
            }
        }
        return paths;
    }
}