package com.example.ling.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.example.ling.MainActivity;
import com.example.ling.R;
import com.example.ling.calendar.CalendarActivity;
import com.example.ling.common.CommonConn;
import com.example.ling.common.CommonVar;
import com.example.ling.databinding.ActivityLocTrackingBinding;
import com.example.ling.login.Ling_MemberVO;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraAnimation;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.FusedLocationSource;

public class LocTrackingActivity extends AppCompatActivity implements OnMapReadyCallback {

    ActivityLocTrackingBinding binding;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;
    private NaverMap naverMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLocTrackingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgvHome.setOnClickListener(v -> {
            finish();
        });

        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
        locationSource =
                new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (locationSource.onRequestPermissionsResult(
                requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated()) { // 권한 거부됨
                naverMap.setLocationTrackingMode(LocationTrackingMode.None);
            }
            return;
        }
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults);
    }

    private Marker currentMarker;
    private Handler handler = new Handler();
    private LatLng targetLocation;

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;
        naverMap.setLocationSource(locationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.Face);

        // 상대방의 위치를 표시할 마커 생성
        naverMap.addOnCameraIdleListener(() -> {
            Location lastLocation = locationSource.getLastLocation();
            if (lastLocation != null) {
                // 처음에 마커 한번 찍음
                if (currentMarker == null) {
                    CommonConn conn = new CommonConn(this, "select_location");
                    conn.addParamMap("id", CommonVar.loginInfo.getId());
                    conn.addParamMap("couple_num", CommonVar.loginInfo.getCouple_num());
                    conn.onExcute((isResult, data) -> {
                        Ling_MemberVO location = new Gson().fromJson(data, new TypeToken<Ling_MemberVO>() {
                        }.getType());
                        targetLocation = new LatLng(Double.parseDouble(location.getLat()), Double.parseDouble(location.getLng()));
                        currentMarker = new Marker();
                        currentMarker.setPosition(targetLocation);
                        currentMarker.setMap(naverMap);
                        CameraUpdate cameraUpdate = CameraUpdate.scrollAndZoomTo(targetLocation, 15)
                                .animate(CameraAnimation.Fly, 2000);
                        naverMap.moveCamera(cameraUpdate);
                    });
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        CommonConn conn = new CommonConn(LocTrackingActivity.this, "update_location");
                        conn.addParamMap("lat", lastLocation.getLatitude());
                        conn.addParamMap("lng", lastLocation.getLongitude());
                        conn.addParamMap("id", CommonVar.loginInfo.getId());
                        conn.onExcute((isResult, data) -> {
                            CommonConn locationConn = new CommonConn(LocTrackingActivity.this, "select_location");
                            locationConn.addParamMap("id", CommonVar.loginInfo.getId());
                            locationConn.addParamMap("couple_num", CommonVar.loginInfo.getCouple_num());
                            locationConn.onExcute((isResult1, data1) -> {
                                Ling_MemberVO location = new Gson().fromJson(data1, new TypeToken<Ling_MemberVO>() {
                                }.getType());
                                targetLocation = new LatLng(Double.parseDouble(location.getLat()), Double.parseDouble(location.getLng()));
                                if (currentMarker != null) {
                                    currentMarker.setMap(null);
                                }
                                currentMarker = new Marker();
                                currentMarker.setPosition(targetLocation);
                                currentMarker.setMap(naverMap);
                                CameraUpdate cameraUpdate = CameraUpdate.scrollAndZoomTo(targetLocation, 15)
                                        .animate(CameraAnimation.Fly, 2000);
                                naverMap.moveCamera(cameraUpdate);
                            });
                        });
                    }
                }, 20000);
            } else {
                Toast.makeText(this, "위치를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
