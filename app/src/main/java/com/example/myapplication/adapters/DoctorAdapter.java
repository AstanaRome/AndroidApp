package com.example.myapplication.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import com.example.myapplication.NetworkService;
import com.example.myapplication.R;
import com.example.myapplication.entity.Doctor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorAdapter extends ArrayAdapter<Doctor> {
    private int resource;
    private List<Doctor> doctors;
    private LayoutInflater inflater;
    public DoctorAdapter(@NonNull Context context, int resource, List<Doctor>doctors) {
        super(context, resource, doctors);
        this.resource = resource;
        this.doctors = doctors;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        View item = inflater.inflate(resource, parent, false);




        EditText etFullname = item.findViewById(R.id.etFirstname);
        EditText etQualifiation = item.findViewById(R.id.etLastname);
        Doctor doctor = doctors.get(position);
        etFullname.setText(doctor.getFullname());
        etQualifiation.setText(doctor.getQualification());

        etFullname.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                doctor.setFullname(s.toString());
                updateDoctor(doctor);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}

        });
        etQualifiation.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                doctor.setQualification(s.toString());
                updateDoctor(doctor);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}

        });


        return item;
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public void updateDoctor(Doctor doctor){
        System.out.println(doctor);
        NetworkService
                .getInstance()
                .getDoctorApi()
                .updateDoctor(doctor)
                .enqueue(new Callback<Doctor>() {
                    @Override
                    public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                        Doctor buf = response.body();
                    }
                    @Override
                    public void onFailure(Call<Doctor> call, Throwable t) {
                    }
                });
    }


}
