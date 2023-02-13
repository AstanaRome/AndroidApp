package com.example.myapplication.fragments;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import com.example.myapplication.NetworkService;
import com.example.myapplication.R;
import com.example.myapplication.VisitDialog;
import com.example.myapplication.adapters.PersonAdapter;
import com.example.myapplication.api.PersonApi;
import com.example.myapplication.entity.Person;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonFragment extends Fragment {

    public PersonFragment(){
        super(R.layout.fragment_person);
    }
    private ListView lvPersons;
    private PersonAdapter adapter;
    private List<Person> persons;
    private FloatingActionButton btnAdd;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnAdd = view.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(this::startTaskDialog);


        lvPersons = view.findViewById(R.id.lvPersons);
        NetworkService networkService = NetworkService.getInstance();
        PersonApi api = networkService.getPersonApi();
        lvPersons.setOnItemClickListener(this::deleteMethod);
        Call<List<Person>> call = api.getAllPersons();

        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                persons = response.body();
                adapter = new PersonAdapter(getActivity(), R.layout.person_list_item, persons);
                lvPersons.setAdapter(adapter);
                System.out.println("test!!!!!!!");
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                System.out.println(t.toString());
            }
        });

    }

    private void deleteMethod(AdapterView<?> adapterView, View view, int i, long l) {
        System.out.println("proverka!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }


    private void startTaskDialog(View view) {
        VisitDialog contactDialog = new VisitDialog();
        contactDialog.show(getChildFragmentManager(), "custom");
    }

}
