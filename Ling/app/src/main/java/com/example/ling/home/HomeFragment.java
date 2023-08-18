package com.example.ling.home;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.ling.R;
import com.example.ling.calendar.CalendarActivity;
import com.example.ling.common.RetClient;
import com.example.ling.common.RetInterface;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.FragmentHomeBinding;
import com.example.ling.login.PreferenceManager;
//import com.ramotion.fluidslider.FluidSlider;
import com.example.ling.photo.PhotoActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;


public class HomeFragment extends Fragment {
    //ActivityResultLauncher<Intent> launcher; // <-- onCreate에서 초기화하면 오류발생.
    FragmentHomeBinding binding;
    Uri camera_uri;

    private final int REQ_Gallery = 1000 , REQ_CAMERA = 1001;

    private final int DEFALUT_MANIMG = R.drawable.man;


    String couple_num;

    private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/M/d");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(!CommonVar.loginInfo.getId().equals("admin") ) {


            CommonConn conn = new CommonConn(getContext(), "select_couple_info");
            conn.addParamMap("id", CommonVar.loginInfo.getId());

            conn.onExcute((isResult, data) -> {
                ArrayList<MainVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<MainVO>>() {
                }.getType());

                binding.tvMid.setText(list.get(0).mname);
                binding.tvFid.setText(list.get(0).fname);
                binding.tvDay.setText("사귄지 " + list.get(0).day + "일" + "커플번호는 " + list.get(0).couple_num);
                couple_num = list.get(0).couple_num;

            });
        }


        binding = FragmentHomeBinding.inflate(inflater, container, false);

        Glide.with(this).load("http://192.168.0.28/hanul/img//andimg.jpg").into(binding.imgvManProfile);
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

        binding.imgvLocTracking.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), LocTrackingActivity.class);
            startActivity(intent);
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

        //김기곤 test chat
//        binding.tvTestChat.setOnClickListener(v -> {
//            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager(); // getActivity() 대신 requireActivity()를 사용합니다.
//            FragmentTransaction transaction = fragmentManager.beginTransaction();
//
//            TestChatFragment testChatFragment = new TestChatFragment(); // TestChatFragment로 교체할 프래그먼트 인스턴스 생성
//            transaction.replace(R.id.container, testChatFragment); // R.id.container는 프래그먼트가 표시될 레이아웃의 ID입니다.
//
//            transaction.addToBackStack(null); // 백 스택에 추가하여 뒤로 가기 가능
//            transaction.commit();
//        });




        return binding.getRoot();


    }


    Handler handler = new Handler();


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
                //카메라 로직
                binding.imgvManProfile.setImageResource(DEFALUT_MANIMG);
            }

            dialog.dismiss();
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public void onStart() {
        super.onStart();

        Activity activity = getActivity();

//        launcher = getActivity().registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//            @Override
//            public void onActivityResult(ActivityResult result) {
//                //액티비티(카메라 액티비티)가 종료되면 콜백으로 데이터를 받는 부분. (기존에는 onActivityResult메소드가 실행/ 현재는 해당 메소드)
//
//            }
//        });
    }




    public void showCamera(){
        //ContentResolver(). 앱 -> 컨텐트리졸버(작업자) -> 미디어 저장소
        camera_uri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, camera_uri);
        startActivityForResult(cameraIntent  , REQ_CAMERA);
     //   launcher.launch(cameraIntent);
    }


    public void showGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
//        startActivity(intent); //단순 실행 결과 알 수 없음
        startActivityForResult(intent, REQ_Gallery);
    }

    //startActivityForResult로 띄운 액티비티가 종료되면 반드시 ↓ 메소드가 실행 됨.
    //카메라 액티비티의 종료인지, 갤러리 액티비티의 종료인지, 그 외에 액티비티 종료인지를 구분할 수 있는 변수가 필요하다.
    //requestCode <- 내가 넣어놨떤 코드가 그대로 다시 돌아옴.
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_Gallery && resultCode == RESULT_OK){
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
                    t.getMessage();
                }
            });

            //MultiPart <- Spring 처리
            //form태그 : 태그 사이에 있는 모든 입력 양식을 감싸는 태그로, name, action..등의 속성을 가지고 전송용 태그.
            //encType : 폼데이터 ↑ 서버로 전송될 때 "파일을 담고 있다면" 데이터의 인코딩 과정이 필요하다.
            //multipart/form-data <- 파일과 데이터는 담겨지는 영역이 다르기때문에 여러부분(Multi)Body(Part)


        }else if(requestCode == REQ_CAMERA && resultCode == RESULT_OK){
             Glide.with(HomeFragment.this).load(camera_uri).into(binding.imgvManProfile);
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
    }

    //오라클 커서
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