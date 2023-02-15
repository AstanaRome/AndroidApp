package com.example.myapplication.fragments.full_info;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.api.NetworkService;
import com.example.myapplication.R;
import com.example.myapplication.entity.Doctor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorFullInfoFragment extends Fragment {
    //private ImageView ivAvatar;
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etQualification;
    private EditText etPhone;
    private EditText etEmail;
    private EditText etCountry;
    private Button btnApply;
    private Doctor doctor;
    private String key;
    public DoctorFullInfoFragment(){
        super(R.layout.fragment_doctor_full_info);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doctor = new Doctor();
        Bundle bundle = getArguments();
        if (bundle != null) {
            key = bundle.getString("key");
            if (key.equals("update")) {
                doctor = bundle.getParcelable("doctorInfo"); // Key
            }
        }

        etFirstName = view.findViewById(R.id.etFirstname);
        etLastName = view.findViewById(R.id.etLastName);
        etQualification = view.findViewById(R.id.etQualification);
        etCountry = view.findViewById(R.id.etCountry);
        etEmail = view.findViewById(R.id.etEmail);
        btnApply = view.findViewById(R.id.btnApply);

        //doctor = (Doctor) getIntent().getSerializableExtra("doctor");

        etFirstName.setText(doctor.getFirstName());
        etLastName.setText(doctor.getLastName());
        etQualification.setText(doctor.getQualification());
        etCountry.setText(doctor.getCountry());
        etEmail.setText(String.valueOf(doctor.getEmail()));

        btnApply.setOnClickListener(this::change);
    }
    private void change(View view){
        doctor.setFirstName(etFirstName.getText().toString());
        doctor.setLastName(etLastName.getText().toString());
        doctor.setEmail(etEmail.getText().toString());
        doctor.setCountry(etCountry.getText().toString());
        doctor.setQualification(etQualification.getText().toString());
        if (key.equals("update")) {
            updateDoctor(doctor);
        } else{
            saveDoctor(doctor);
        }

        //        doctor.setPhone(etPhone.getText().toString());
//        doctor.setEmail(Integer.parseInt(etEmail.getText().toString()));
//        CarStorage.Update(doctor);

    }
    private void saveDoctor(Doctor doctor){
        NetworkService
                .getInstance()
                .getDoctorApi()
                .saveDoctor(doctor)
                .enqueue(new Callback<Doctor>() {
                    @Override
                    public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                        Doctor buf = response.body();
                        Toast.makeText(getContext(), "Succesful",
                                Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<Doctor> call, Throwable t) {
                    }
                });
    }
    private void updateDoctor(Doctor doctor){
            NetworkService
                    .getInstance()
                    .getDoctorApi()
                    .updateDoctor(doctor)
                    .enqueue(new Callback<Doctor>() {
                        @Override
                        public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                            Doctor buf = response.body();
                            Toast.makeText(getContext(), "Succesful",
                                    Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onFailure(Call<Doctor> call, Throwable t) {
                        }
                    });
    }



}