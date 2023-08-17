package com.example.ling.common;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetClient {


    //192.168.0.36 김기곤
    //192.168.0.28 정수원
    //192.168.0.122 김건호
    //192.168.0.31 김혜민
    public Retrofit getRet(){
        Retrofit ret = new Retrofit.Builder()
                .baseUrl("http://192.168.0.36:8080/ling/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        return ret;
    }
}
