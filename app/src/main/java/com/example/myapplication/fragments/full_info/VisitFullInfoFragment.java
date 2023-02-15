package com.example.myapplication.fragments.full_info;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.api.DoctorApi;
import com.example.myapplication.api.NetworkService;
import com.example.myapplication.api.PersonApi;
import com.example.myapplication.entity.Doctor;
import com.example.myapplication.entity.Visit;
import com.example.myapplication.entity.Person;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitFullInfoFragment extends Fragment {
    //private ImageView ivAvatar;
    private List<Person> persons;
    private List<Doctor> doctors;
    private List<Visit> visits;
    private Button btnApply;
    private Visit visit;
    private String key;
    Spinner spPerson;
    Spinner spDoctor;
    private EditText etCause;
    //private EditText etDate;
    String fullName_person;
    String fullName_doctor;



    public VisitFullInfoFragment(){
        super(R.layout.fragment_visit_full_info);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        visit = new Visit();
        Bundle bundle = getArguments();
        if (bundle != null) {
            key = bundle.getString("key");
            if (key.equals("update")) {
                visit = bundle.getParcelable("visitInfo"); // Key
            }
        }

        etCause = view.findViewById(R.id.etCause);
        //EditText etDate = view.findViewById(R.id.etDate);
        spDoctor = view.findViewById(R.id.spDoctor);
        spPerson = view.findViewById(R.id.spPerson);
        btnApply = view.findViewById(R.id.btnAddVisit);
        btnApply.setOnClickListener(this::change);
//        etCause.setText(visit.getCause());
//        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");//formating according to my need
//        String date = formatter.format(visit.getVisitDate());
        //etDate.setText(visit.getVisitDate());
        spPerson.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String str = (String) spPerson.getSelectedItem();
                for (Person person : persons) {
                    String str2 = person.getFullName();
                    if (str2.equals(str)) {
                        visit.setPerson(person);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        spDoctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String str = (String) spDoctor.getSelectedItem();
                for (Doctor doctor : doctors) {
                    String str2 = doctor.getFullName();
                    if (str2.equals(str)) {
                        visit.setDoctor(doctor);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        fillSpPerson(visit);
        fillSpDoctor(visit);
    }
    private void change(View view){
        visit.setCause(etCause.getText().toString());
        //visit.setVisitDate(etDate.getText().toString());
        if (key.equals("update")) {
            updateVisit(visit);
        } else{
            saveVisit(visit);
        }

    }

    private void saveVisit(Visit visit){
        NetworkService
                .getInstance()
                .getVisitApi()
                .saveVisit(visit)
                .enqueue(new Callback<Visit>() {
                    @Override
                    public void onResponse(Call<Visit> call, Response<Visit> response) {
                        Visit buf = response.body();
                        Toast.makeText(getContext(), "Succesful",
                                Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<Visit> call, Throwable t) {
                    }
                });
    }
    private void updateVisit(Visit visit){
            NetworkService
                    .getInstance()
                    .getVisitApi()
                    .updateVisit(visit)
                    .enqueue(new Callback<Visit>() {
                        @Override
                        public void onResponse(Call<Visit> call, Response<Visit> response) {
                            Visit buf = response.body();
                            Toast.makeText(getContext(), "Succesful",
                                    Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onFailure(Call<Visit> call, Throwable t) {
                        }
                    });
    }

    public void fillSpDoctor(Visit visit){
        NetworkService networkService = NetworkService.getInstance();
        DoctorApi api = networkService.getDoctorApi();
        Call<List<Doctor>> call = api.getAllDoctors();
        call.enqueue(new Callback<List<Doctor>>() {
            @Override
            public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
                doctors = response.body();
                ArrayList<String> names = new ArrayList<>();
                for (Doctor doctor: doctors) {
                    if (key.equals("update")) {
                        if (doctor.getId() == visit.getDoctor().getId()) {
                            fullName_person = visit.getDoctor().getFullName();
                        }
                    }
                    names.add(doctor.getFullName());
                }
                ArrayAdapter<String> adapter_doctor = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, names);
                adapter_doctor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spDoctor.setAdapter(adapter_doctor);
                if (key.equals("update")){
                    int index = adapter_doctor.getPosition(fullName_doctor);
                    spDoctor.setSelection(index);
                }

            }
            @Override
            public void onFailure(Call<List<Doctor>> call, Throwable t) {
                System.out.println(t.toString());
            }
        });


    }



    public void fillSpPerson(Visit visit){
        NetworkService networkService = NetworkService.getInstance();
        PersonApi api = networkService.getPersonApi();
        Call<List<Person>> call = api.getAllPersons();
        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                persons = response.body();
                ArrayList<String> names = new ArrayList<>();
                for (Person person: persons) {
                    if (key.equals("update")) {
                        if (person.getId() == visit.getPerson().getId()) {
                            fullName_person = visit.getPerson().getFullName();
                        }
                    }
                    names.add(person.getFirstName() + " " +  person.getLastName());
                }
                ArrayAdapter<String> adapter_person = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, names);
                adapter_person.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spPerson.setAdapter(adapter_person);
                if (key.equals("update")){
                    int index = adapter_person.getPosition(fullName_person);
                    spPerson.setSelection(index);
                }

            }
            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                System.out.println(t.toString());
            }
        });


    }

//    public void fillSpDoctor(int id){
//        NetworkService networkService = NetworkService.getInstance();
//        DoctorApi api = networkService.getDoctorApi();
//        Call<List<Doctor>> call = api.getAllDoctors();
//        call.enqueue(new Callback<List<Doctor>>() {
//            @Override
//            public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
//                doctors = response.body();
//                ArrayList<String>names = new ArrayList<>();
//                for (Doctor doctor: doctors) {
//                    if (doctor.getId() == id){
//                        fullName_d = doctor.getFullname();
//                    }
//                    names.add(doctor.getFullname());
//                }
//                ArrayAdapter<String> adapter_doctor = new ArrayAdapter<String>(getContext(),
//                        android.R.layout.simple_spinner_item, names);
//                adapter_doctor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                int index = adapter_doctor.getPosition(fullName_d);
//                spDoctor.setAdapter(adapter_doctor);
//
//                spDoctor.setSelection(index);
//            }
//            @Override
//            public void onFailure(Call<List<Doctor>> call, Throwable t) {
//                System.out.println(t.toString());
//            }
//        });




};