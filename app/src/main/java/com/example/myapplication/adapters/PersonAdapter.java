package com.example.myapplication.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import com.example.myapplication.NetworkService;
import com.example.myapplication.R;
import com.example.myapplication.entity.Person;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonAdapter extends ArrayAdapter<Person> {
    private int resource;
    private List<Person> persons;
    private LayoutInflater inflater;
    public PersonAdapter(@NonNull Context context, int resource, List<Person>persons) {
        super(context, resource, persons);
        this.resource = resource;
        this.persons = persons;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        View item = inflater.inflate(resource, parent, false);




        TextView etFirstName = item.findViewById(R.id.etFirstname);
        TextView etLastName = item.findViewById(R.id.etLastname);
        Person person = persons.get(position);

        etFirstName.setText(person.getFirstName());
        etLastName.setText(person.getLastName());

//        etFirstName.addTextChangedListener(new TextWatcher() {
//            public void afterTextChanged(Editable s) {
//                person.setFirstName(s.toString());
//                updatePerson(person);
//            }
//
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//
//            public void onTextChanged(CharSequence s, int start, int before, int count) {}
//
//        });
//        etLastName.addTextChangedListener(new TextWatcher() {
//            public void afterTextChanged(Editable s) {
//                person.setLastName(s.toString());
//                updatePerson(person);
//            }
//
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//
//            public void onTextChanged(CharSequence s, int start, int before, int count) {}
//
//        });


        return item;
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public void updatePerson(Person Person){

        NetworkService
                .getInstance()
                .getPersonApi()
                .updatePerson(Person)
                .enqueue(new Callback<Person>() {
                    @Override
                    public void onResponse(Call<Person> call, Response<Person> response) {
                        Person buf = response.body();
                    }
                    @Override
                    public void onFailure(Call<Person> call, Throwable t) {
                    }
                });
    }


}
