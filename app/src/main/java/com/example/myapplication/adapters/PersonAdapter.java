package com.example.myapplication.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.entity.Person;

import java.util.List;

public class PersonAdapter  extends RecyclerView.Adapter<PersonAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Person> persons;

    public PersonAdapter(Context context, List<Person> persons) {
        this.persons = persons;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.person_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonAdapter.ViewHolder holder, int position) {
        Person person = persons.get(position);
        holder.firstnameView.setText(person.getFirstName() + " " + person.getLastName());

    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView firstnameView;
        ViewHolder(View view){
            super(view);
            firstnameView = view.findViewById(R.id.tvFullname);       }
    }



}