package com.example.myapplication.fragments.full_info;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.api.NetworkService;
import com.example.myapplication.R;
import com.example.myapplication.entity.Person;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonFullInfoFragment extends Fragment {
    //private ImageView ivAvatar;
    private EditText etFirstName;
    private EditText etLastname;
    private EditText etPhone;
    private EditText etEmail;
    private EditText etAge;
    private Button btnApply;
    private Person person;
    private String key;
    public PersonFullInfoFragment(){
        super(R.layout.fragment_person_full_info);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        person = new Person();
        Bundle bundle = getArguments();
        if (bundle != null) {
            key = bundle.getString("key");
            if (key.equals("update")) {
                person = bundle.getParcelable("personInfo"); // Key
            }
        }

        etFirstName = view.findViewById(R.id.etFirstname);
        etLastname = view.findViewById(R.id.etQualification);
        etPhone = view.findViewById(R.id.etPhone);
        etEmail = view.findViewById(R.id.etEmail);
        etAge = view.findViewById(R.id.etAge);
        btnApply = view.findViewById(R.id.btnApply);

        //person = (Person) getIntent().getSerializableExtra("person");

        etFirstName.setText(person.getFirstName());
        etLastname.setText(person.getLastName());
        etPhone.setText(person.getPhone());
        etEmail.setText(String.valueOf(person.getEmail()));
        etAge.setText(String.valueOf(person.getAge()));

        btnApply.setOnClickListener(this::change);
    }
    private void change(View view){
        System.out.println(etFirstName.getText().toString());
        person.setFirstName(etFirstName.getText().toString());
        person.setLastName(etLastname.getText().toString());
        person.setEmail(etEmail.getText().toString());
        person.setPhone(etPhone.getText().toString());
        person.setAge(Integer.parseInt(etAge.getText().toString()));
        if (key.equals("update")) {
            updatePerson(person);
        } else{
            savePerson(person);
        }

        //        person.setPhone(etPhone.getText().toString());
//        person.setEmail(Integer.parseInt(etEmail.getText().toString()));
//        CarStorage.Update(person);

    }
    private void savePerson(Person person){
        NetworkService
                .getInstance()
                .getPersonApi()
                .savePerson(person)
                .enqueue(new Callback<Person>() {
                    @Override
                    public void onResponse(Call<Person> call, Response<Person> response) {
                        Person buf = response.body();
                        Toast.makeText(getContext(), "Succesful",
                                Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<Person> call, Throwable t) {
                    }
                });
    }
    private void updatePerson(Person person){
            NetworkService
                    .getInstance()
                    .getPersonApi()
                    .updatePerson(person)
                    .enqueue(new Callback<Person>() {
                        @Override
                        public void onResponse(Call<Person> call, Response<Person> response) {
                            Person buf = response.body();
                            Toast.makeText(getContext(), "Succesful",
                                    Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onFailure(Call<Person> call, Throwable t) {
                        }
                    });
    }



}