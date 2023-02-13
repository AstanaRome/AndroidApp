package com.example.myapplication.adapters;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import com.example.myapplication.NetworkService;
import com.example.myapplication.R;
import com.example.myapplication.api.DoctorApi;
import com.example.myapplication.api.PersonApi;
import com.example.myapplication.entity.Doctor;
import com.example.myapplication.entity.Person;
import com.example.myapplication.entity.Visit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitAdapter extends ArrayAdapter<Visit> {
    private int resource;
    private List<Visit> visits;
    private LayoutInflater inflater;
    private List<Person> persons;
    private List<Doctor> doctors;
    String fullName;
    String fullName_d;
    Spinner spPerson;
    Spinner spDoctor;
    public VisitAdapter(@NonNull Context context, int resource, List<Visit>visits) {
        super(context, resource, visits);
        this.resource = resource;
        this.visits = visits;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        View item = inflater.inflate(resource, parent, false);




        EditText etCause = item.findViewById(R.id.etCause);
        spDoctor = item.findViewById(R.id.spDoctor);
        spPerson = item.findViewById(R.id.spPerson);
        Visit visit = visits.get(position);
        etCause.setText(visit.getCause());
        System.out.println(visit.getPerson());
        fillSpPerson(visit.getPerson().getId());
        fillSpDoctor(visit.getDoctor().getId());


        etCause.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                visit.setCause(s.toString());
                updateVisit(visit);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}

        });






        spPerson.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String str = (String) spPerson.getSelectedItem();
                for (Person person : persons) {
                    String str2 = person.getFirstName() + " " + person.getLastName();
                    if (str2.equals(str)) {
                        visit.setPerson(person);
                        updateVisit(visit);
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
                    String str2 = doctor.getFullname();
                    if (str2.equals(str)) {
                        visit.setDoctor(doctor);
                        updateVisit(visit);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

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


    public void fillSpPerson(int id){
        NetworkService networkService = NetworkService.getInstance();
        PersonApi api = networkService.getPersonApi();
        Call<List<Person>> call = api.getAllPersons();
        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                persons = response.body();
                ArrayList<String>names = new ArrayList<>();
                for (Person person: persons) {
                    if (person.getId() == id){
                        fullName = (person.getFirstName() + " " + person.getLastName());
                    }
                    names.add(person.getFirstName() + " " +  person.getLastName());
                }
                ArrayAdapter<String> adapter_person = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, names);
                adapter_person.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                int index = adapter_person.getPosition(fullName);
                spPerson.setAdapter(adapter_person);

                spPerson.setSelection(index);
            }
            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                System.out.println(t.toString());
            }
        });


    }

    public void fillSpDoctor(int id){
        NetworkService networkService = NetworkService.getInstance();
        DoctorApi api = networkService.getDoctorApi();
        Call<List<Doctor>> call = api.getAllDoctors();
        call.enqueue(new Callback<List<Doctor>>() {
            @Override
            public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
                doctors = response.body();
                ArrayList<String>names = new ArrayList<>();
                for (Doctor doctor: doctors) {
                    if (doctor.getId() == id){
                       fullName_d = doctor.getFullname();
                    }
                    names.add(doctor.getFullname());
                }
                ArrayAdapter<String> adapter_doctor = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, names);
                adapter_doctor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                int index = adapter_doctor.getPosition(fullName_d);
                spDoctor.setAdapter(adapter_doctor);

                spDoctor.setSelection(index);
            }
            @Override
            public void onFailure(Call<List<Doctor>> call, Throwable t) {
                System.out.println(t.toString());
            }
        });


    }

    public void updateVisit(Visit visit){
       // System.out.println(visit);
        NetworkService
                .getInstance()
                .getVisitApi()
                .updateVisit(visit)
                .enqueue(new Callback<Visit>() {
                    @Override
                    public void onResponse(Call<Visit> call, Response<Visit> response) {
                        Visit buf = response.body();
                    }
                    @Override
                    public void onFailure(Call<Visit> call, Throwable t) {
                    }
                });
    }


}
