package com.example.myapplication;

import com.example.myapplication.api.DoctorApi;
import com.example.myapplication.api.PersonApi;
import com.example.myapplication.api.VisitApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService networkService;
    private static final String BASE_URL = "http://192.168.1.7:8080/";
    private Retrofit retrofit;

    private NetworkService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (networkService == null) {
            networkService = new NetworkService();
        }
        return networkService;
    }

   public PersonApi getPersonApi(){
        return retrofit.create(PersonApi.class);
    }

    public DoctorApi getDoctorApi(){
        return retrofit.create(DoctorApi.class);
    }
    public VisitApi getVisitApi(){
        return retrofit.create(VisitApi.class);
    }

}
