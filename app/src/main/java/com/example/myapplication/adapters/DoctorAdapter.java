package com.example.myapplication.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.entity.Doctor;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Doctor> doctors;

    public DoctorAdapter(Context context, List<Doctor> doctors) {
        this.doctors = doctors;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public DoctorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.doctor_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DoctorAdapter.ViewHolder holder, int position) {
        Doctor doctor = doctors.get(position);
        holder.fullNameView.setText(doctor.getFullName());
        holder.countryView.setText(doctor.getCountry());
        holder.emailView.setText(doctor.getEmail());
        holder.qualificationView.setText(doctor.getQualification());

    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView fullNameView, qualificationView, emailView, countryView;
        ViewHolder(View view){
            super(view);
            fullNameView = view.findViewById(R.id.tvFullname);
            qualificationView = view.findViewById(R.id.tvQualification);
            emailView = view.findViewById(R.id.tvEmail);
            countryView = view.findViewById(R.id.tvCountry);
        }
    }



}