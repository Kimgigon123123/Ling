package com.example.ling.common;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetClient {

    public Retrofit getRet(){
        Retrofit ret = new Retrofit.Builder()
                .baseUrl("http://192.168.0.28:8080/hanul/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        return ret;
    }
}
