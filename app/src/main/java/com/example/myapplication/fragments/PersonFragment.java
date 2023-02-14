package com.example.myapplication.fragments;


import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.myapplication.NetworkService;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerItemClickListener;
import com.example.myapplication.VisitDialog;
import com.example.myapplication.adapters.PersonAdapter;
import com.example.myapplication.api.PersonApi;
import com.example.myapplication.entity.Person;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonFragment extends Fragment implements ClickInterface {

    public PersonFragment(){
        super(R.layout.fragment_person);
    }
    private RecyclerView rvPersons;
    private PersonAdapter adapter;
    private List<Person> persons;
    private FloatingActionButton btnAdd;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this::addPerson);

        rvPersons = view.findViewById(R.id.rvPersons);

        fillAdapter();

        rvPersons.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), rvPersons ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Fragment Fragment_Person_Info   =  new PersonFullInfoFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragmentContainerView3, Fragment_Person_Info, "TAG")
                                .commit();
                        Bundle bundle = new Bundle();
                        Person person = persons.get(position);
                        bundle.putString("key", "update");
                        bundle.putParcelable("personInfo", person);  // Key, value
                        Fragment_Person_Info.setArguments(bundle);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );



        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                // this method is called
                // when the item is moved.
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // this method is called when we swipe our item to right direction.
                // on below line we are getting the item at a particular position.
                Person deletedCourse = persons.get(viewHolder.getAdapterPosition());
                deletePerson(deletedCourse);
                // below line is to get the position
                // of the item at that position.
                int position = viewHolder.getAdapterPosition();

                // this method is called when item is swiped.
                // below line is to remove item from our array list.
                persons.remove(viewHolder.getAdapterPosition());

                // below line is to notify our item is removed from adapter.
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());

                // below line is to display our snackbar with action.

            }
            // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(rvPersons);
    }










    public void deletePerson(Person person){
        Call<ResponseBody> deleteRequest = NetworkService
                .getInstance()
                .getPersonApi()
                .deletePerson(person.getId());
        deleteRequest.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(getContext(), "deleted", Toast.LENGTH_SHORT).show();
                System.out.println(response.code());
                System.out.println(response.body());
                System.out.println(person.getId());
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // handle failure
                Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
            }
        });;
    }


    private void addPerson(View view) {
        Fragment Fragment_Person_Info   =  new PersonFullInfoFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView3, Fragment_Person_Info, "TAG")
                .commit();
        Bundle bundle = new Bundle();
        bundle.putString("key", "save");
        Fragment_Person_Info.setArguments(bundle);
    }

    private void fillAdapter(){
        NetworkService networkService = NetworkService.getInstance();
        PersonApi api = networkService.getPersonApi();

        Call<List<Person>> call = api.getAllPersons();

        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                persons = response.body();
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                rvPersons.setHasFixedSize(true);
                rvPersons.setLayoutManager(manager);
                adapter = new PersonAdapter(getContext(), persons);
                rvPersons.setAdapter(adapter);
                System.out.println("test!!!!!!!");
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                System.out.println(t.toString());
            }
        });
    }


    @Override
    public void recyclerviewOnClick(int position) {

    }
}
