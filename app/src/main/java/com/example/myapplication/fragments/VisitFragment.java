package com.example.myapplication.fragments;


import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.NetworkService;
import com.example.myapplication.R;
import com.example.myapplication.adapters.VisitAdapter;
import com.example.myapplication.api.VisitApi;
import com.example.myapplication.entity.Visit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitFragment extends Fragment {

    public VisitFragment(){
        super(R.layout.fragment_visit);
    }
    private ListView lvVisits;
    private VisitAdapter adapter;
    private List<Visit> visits;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvVisits = view.findViewById(R.id.lvPersons);
        NetworkService networkService = NetworkService.getInstance();
        VisitApi api = networkService.getVisitApi();

        Call<List<Visit>> call = api.getAllVisits();

        call.enqueue(new Callback<List<Visit>>() {
            @Override
            public void onResponse(Call<List<Visit>> call, Response<List<Visit>> response) {
                visits = response.body();
                adapter = new VisitAdapter(getActivity(), R.layout.visit_list_item, visits);
                lvVisits.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Visit>> call, Throwable t) {
                System.out.println(t.toString());
            }
        });

    }

}
