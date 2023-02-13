package com.example.myapplication.fragments;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.R;

public class MainFragment extends Fragment {
    private ImageButton btnPersons;
    private ImageButton btnDoctors;
    private ImageButton btnVisits;
    public MainFragment(){
        super(R.layout.fragment_main);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnPersons = view.findViewById(R.id.btnPerson);
        btnDoctors = view.findViewById(R.id.btnDoctor);
        btnVisits = view.findViewById(R.id.btnVisit);
        btnPersons.setOnClickListener(this::showSecondFragment);
        btnDoctors.setOnClickListener(this::showThirdFragment);
        btnVisits.setOnClickListener(this::showVisitFragment);
    }

    private void showSecondFragment(View view) {
        Fragment Fragment_Second=new PersonFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView3, Fragment_Second, "TAG")
                .commit();
    }
    private void showThirdFragment(View view) {
        Fragment Fragment_Third=new DoctorFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView3, Fragment_Third, "TAG")
                .commit();
    }
    private void showVisitFragment(View view) {
        Fragment Fragment_Visit=new VisitFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView3, Fragment_Visit, "TAG")
                .commit();
    }

}
