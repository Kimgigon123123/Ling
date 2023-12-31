package com.example.ling.photo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ling.R;
import com.example.ling.calendar.CalendarAddActivity;
import com.example.ling.calendar.Spinner;
import com.example.ling.calendar.SpinnerAdapter;
import com.example.ling.chat.ChatActivity;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.common.RetClient;
import com.example.ling.common.RetInterface;
import com.example.ling.databinding.ActivityPhotoBinding;
import com.example.ling.home.CameraDialog;
import com.example.ling.photo.FolderVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;

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
    private ArrayList<FolderVO> folder_List;

    ArrayList<PhotoVO> photo_List;
    Window window ;
    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhotoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        select();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(Color.parseColor("#FDCEDF"));
        }


        binding.imgvElbumCamera.setOnClickListener(v->{//imgv_photos               showGallery();
            showCamera();
        });

        binding.imgvElbumBack.setOnClickListener(v -> {
            finish();
        });


    }

    public void insert(){
        CommonConn conn = new CommonConn(this, "folder_insert");
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String formattedDate = dateFormat.format(currentDate);

            AlertDialog.Builder follder = new AlertDialog.Builder(this);
            name = new EditText(this);
            name.setText(formattedDate);
            follder.setTitle("생성할 폴더명");
            follder.setView(name);
            follder.setPositiveButton("확인", new DialogInterface.OnClickListener() {


                public void onClick(DialogInterface dialog, int whichButton) {
                    String folderName = name.getText().toString().trim();

                    if (isFolderNameDuplicate(folderName)) {
                        // 중복된 이름인 경우 Toast 메시지 표시
                        Toast.makeText(getApplicationContext(), "이미 같은 이름의 폴더가 존재합니다. 다른 이름을 사용하세요.", Toast.LENGTH_SHORT).show();
                        return; // 중복된 이름일 경우 더 이상 진행하지 않고 종료
                    }


                    FolderVO vo = new FolderVO();
                    vo.setFolder_name(name.getText().toString().trim());
                    vo.setId(CommonVar.loginInfo.getId());
                    vo.setCouple_num(CommonVar.loginInfo.getCouple_num());

//                    vo.setFolder_name(vo.getCouple_num()+"");
                    //확인 버튼을 클릭했을때
                    conn.addParamMap("voJson", new Gson().toJson(vo) );
                    conn.addParamMap("id", CommonVar.loginInfo.getId());
                    conn.addParamMap("couple_num", CommonVar.loginInfo.getCouple_num());
                    conn.onExcute((isResult, data) -> {
                        folder_List = new Gson(). fromJson(data, new TypeToken<ArrayList<FolderVO>>(){}.getType());

                        FolderAdapter adapter = new FolderAdapter(folder_List);
                        binding.gridGallery.setAdapter(adapter);
                        binding.gridGallery.setLayoutManager(new GridLayoutManager(PhotoActivity.this, 2));

                      //  Log.d("리스트사이즈", "select: " + foder_List.size());
                        //if문으로 list의 사이즈처리 해야함.
                    });
                }
            });
            follder.setNegativeButton("취소",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) { //취소 버튼을 클ㅣ
                }
            });
            follder.show();

    }

    public void select(){
        CommonConn conn = new CommonConn(this, "folder_list");
        FolderVO vo = new FolderVO();
        conn.addParamMap("id", CommonVar.loginInfo.getId());
        conn.addParamMap("couple_num", CommonVar.loginInfo.getCouple_num());
        vo.setId(CommonVar.loginInfo.getId());
        vo.setCouple_num(CommonVar.loginInfo.getCouple_num());
        conn.onExcute((isResult, data) -> {
            folder_List = new Gson().fromJson(data, new TypeToken<ArrayList<FolderVO>>(){}.getType());
//            Log.d("리스트사이즈", "select: " + list.size());
            //if문으로 list의 사이즈처리 해야함.
            FolderAdapter adapter = new FolderAdapter(folder_List);



            binding.gridGallery.setAdapter(adapter);
            binding.gridGallery.setLayoutManager(new GridLayoutManager(this, 2));

        });
    }
    //어느 폴더에 넣을껀지 질문이나 선택. AlertDialog
    //새로 폴더 추가 하기를 누르면 지금 년월일을 폴더이름으로한다.
    //여기서 만들어서보내기.
    @Override
    protected void onStart() {
        super.onStart();
        binding.imgvFolderAdd.setOnClickListener(view -> {
            insert();
        });
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {

            @Override
            public void onActivityResult(ActivityResult result) {
                //액티비티(카메라 액티비티)가 종료되면 콜백으로 데이터를 받는 부분. (기존에는 onActivityResult메소드가 실행/ 현재는 해당 메소드)
//                Glide.with(PhotoActivity.this).load(camera_uri).into(binding.imgvElbumCamera);

                File file = new File(getRealPath(camera_uri));
                   if(file == null || folder_List.size()==0){
                    Toast.makeText(PhotoActivity.this, "폴더를 먼저 생성해주세요.", Toast.LENGTH_SHORT).show();
                    binding.imgvFolderAdd.performClick();
                   }else{
                       showDialog(file);
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
            api.clientSendFile("file", new HashMap<>(), filePart).enqueue(new Callback<String>() {
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


    public void showDialog(   File file ){
        if(file == null) return;


        android.widget.Spinner spinner = new android.widget.Spinner(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("사진 업로드 방식");
        String[] item = new String[folder_List.size()];
        for (int i = 0; i < item.length; i++) {
            item[i] = folder_List.get(i).getFolder_name();
        }
        builder.setSingleChoiceItems(item , -1 , (dialog, i) -> {
            if(file!=null){

                RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
                MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", "test.jpg", fileBody);
                RetInterface api = new RetClient().getRet().create(RetInterface.class);
                HashMap<String, RequestBody> param = new HashMap<>();

                FolderVO vo  = folder_List.get(i);// vo.set....()
                RequestBody folder_req = RequestBody.create(new Gson().toJson(vo), MediaType.parse("text/plain"));
                param.put("tempVo" ,    folder_req  );

//                    PhotoVO photovo = new PhotoVO();
//                    RequestBody photo_req = RequestBody.create(new Gson().toJson(photovo), MediaType.parse("text/plain"));
//                    param.put("photo", photo_req);

                api.clientSendFile("file", param, filePart).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        select();//파일 업로드 하고 리스트를 Spring에서 다시 조회해옴.
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        t.getMessage();
                    }
                });
            }else if(folder_List.size()==0){
                Toast.makeText(PhotoActivity.this, "폴더를 먼저 생성해주세요.", Toast.LENGTH_SHORT).show();
                binding.imgvFolderAdd.performClick();
            }

            dialog.dismiss();

        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean isFolderNameDuplicate(String folderName) {
        if (folder_List != null) {
            for (FolderVO folderVO : folder_List) {
                if (folderVO.getFolder_name().equalsIgnoreCase(folderName)) {
                    return true; // 중복된 이름이 있으면 true 반환
                }
            }
        }
        return false; // 중복된 이름이 없으면 false 반환
    }

}