package com.example.myapplication.fragments;


import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import com.example.myapplication.NetworkService;
import com.example.myapplication.R;
import com.example.myapplication.adapters.DoctorAdapter;
import com.example.myapplication.api.DoctorApi;
import com.example.myapplication.entity.Doctor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorFragment extends Fragment {

    public DoctorFragment(){
        super(R.layout.fragment_doctor);
    }
    private ListView lvDoctors;
    private DoctorAdapter adapter;
    private List<Doctor> doctors;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvDoctors = view.findViewById(R.id.lvPersons);
        NetworkService networkService = NetworkService.getInstance();
        DoctorApi api = networkService.getDoctorApi();

        Call<List<Doctor>> call = api.getAllDoctors();

        call.enqueue(new Callback<List<Doctor>>() {
            @Override
            public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
                doctors = response.body();
                adapter = new DoctorAdapter(getActivity(), R.layout.doctor_list_item, doctors);
                lvDoctors.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Doctor>> call, Throwable t) {
                System.out.println(t.toString());
            }
        });

    }

}
