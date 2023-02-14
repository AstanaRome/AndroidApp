package com.example.myapplication.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.entity.Visit;

import java.util.List;

public class VisitAdapter extends RecyclerView.Adapter<VisitAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Visit> visits;

    public VisitAdapter(Context context, List<Visit> visits) {
        this.visits = visits;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public VisitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.visit_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VisitAdapter.ViewHolder holder, int position) {
        Visit visit = visits.get(position);
        holder.tvPersonView.setText(visit.getPerson().getFullName());
        holder.tvDoctorView.setText(visit.getDoctor().getFullname());
        holder.tvCause.setText(visit.getCause());

    }

    @Override
    public int getItemCount() {
        return visits.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvDoctorView, tvPersonView, tvCause;
        ViewHolder(View view){
            super(view);
            tvDoctorView = view.findViewById(R.id.tvDoctor);
            tvPersonView = view.findViewById(R.id.tvPerson);
            tvCause = view.findViewById(R.id.tvCause);
        }
    }



}