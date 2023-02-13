package com.example.myapplication.api;

import java.util.List;

import com.example.myapplication.entity.Doctor;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DoctorApi {

    @GET("/doctors")
    Call<List<Doctor>>getAllDoctors();

    @POST("/doctors")
    Call<Doctor> saveDoctor(@Body Doctor doctor);

    @PUT("/doctors")
    Call<Doctor> updateDoctor(@Body Doctor doctor);

    @DELETE("/doctor/{id}")
    Call<ResponseBody> deleteDoctor(@Path("id") int doctorId);


}
