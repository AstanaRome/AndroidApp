package com.example.myapplication.api;

import java.util.List;

import com.example.myapplication.entity.Person;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PersonApi {

    @GET("/persons")
    Call<List<Person>>getAllPersons();

    @POST("/persons")
    Call<Person> savePerson(@Body Person task);

    @PUT("/persons")
    Call<Person> updatePerson(@Body Person task);

    @DELETE("/persons/{id}")
    Call<ResponseBody> deletePerson(@Path("id") int personId);


}
