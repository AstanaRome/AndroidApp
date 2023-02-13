package com.example.myapplication.api;

import java.util.List;

import com.example.myapplication.entity.Visit;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface VisitApi {

    @GET("/visits")
    Call<List<Visit>>getAllVisits();

    @POST("/visits")
    Call<Visit> saveVisit(@Body Visit visit);

    @PUT("/visits")
    Call<Visit> updateVisit(@Body Visit visit);

    @DELETE("/visit/{id}")
    Call<ResponseBody> deleteVisit(@Path("id") int visitId);


}
