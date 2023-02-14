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
        holder.fullnameView.setText(doctor.getFullname());

    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView fullnameView;
        ViewHolder(View view){
            super(view);
            fullnameView = view.findViewById(R.id.tvFullname);       }
    }



}