package com.example.myapplication.fragments;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.api.NetworkService;
import com.example.myapplication.R;
import com.example.myapplication.test.ClickInterface;
import com.example.myapplication.test.RecyclerItemClickListener;
import com.example.myapplication.adapters.DoctorAdapter;
import com.example.myapplication.api.DoctorApi;
import com.example.myapplication.entity.Doctor;
import com.example.myapplication.fragments.full_info.DoctorFullInfoFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorFragment extends Fragment implements ClickInterface {

    public DoctorFragment(){
        super(R.layout.fragment_list);
    }
    private RecyclerView rvDoctors;
    private DoctorAdapter adapter;
    private List<Doctor> doctors;
    private FloatingActionButton btnAdd;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this::addDoctor);

        rvDoctors = view.findViewById(R.id.rvList);

        fillAdapter();

        rvDoctors.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), rvDoctors ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Fragment Fragment_Doctor_Info   =  new DoctorFullInfoFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragmentContainerView3, Fragment_Doctor_Info, "TAG")
                                .commit();
                        Bundle bundle = new Bundle();
                        Doctor doctor = doctors.get(position);
                        bundle.putString("key", "update");
                        bundle.putParcelable("doctorInfo", doctor);  // Key, value
                        Fragment_Doctor_Info.setArguments(bundle);
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
                Doctor deletedCourse = doctors.get(viewHolder.getAdapterPosition());
                deleteDoctor(deletedCourse);
                // below line is to get the position
                // of the item at that position.
                int position = viewHolder.getAdapterPosition();

                // this method is called when item is swiped.
                // below line is to remove item from our array list.
                doctors.remove(viewHolder.getAdapterPosition());

                // below line is to notify our item is removed from adapter.
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());

                // below line is to display our snackbar with action.

            }
            // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(rvDoctors);
    }










    public void deleteDoctor(Doctor doctor){
        Call<ResponseBody> deleteRequest = NetworkService
                .getInstance()
                .getDoctorApi()
                .deleteDoctor(doctor.getId());
        deleteRequest.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(getContext(), "deleted", Toast.LENGTH_SHORT).show();
                System.out.println(response.code());
                System.out.println(response.body());
                System.out.println(doctor.getId());
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // handle failure
                Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
            }
        });;
    }


    private void addDoctor(View view) {
        Fragment Fragment_Doctor_Info   =  new DoctorFullInfoFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView3, Fragment_Doctor_Info, "TAG")
                .commit();
        Bundle bundle = new Bundle();
        bundle.putString("key", "save");
        Fragment_Doctor_Info.setArguments(bundle);
    }

    private void fillAdapter(){
        NetworkService networkService = NetworkService.getInstance();
        DoctorApi api = networkService.getDoctorApi();

        Call<List<Doctor>> call = api.getAllDoctors();

        call.enqueue(new Callback<List<Doctor>>() {
            @Override
            public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
                doctors = response.body();
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                rvDoctors.setHasFixedSize(true);
                rvDoctors.setLayoutManager(manager);
                adapter = new DoctorAdapter(getContext(), doctors);
                rvDoctors.setAdapter(adapter);
                System.out.println("test!!!!!!!");
            }

            @Override
            public void onFailure(Call<List<Doctor>> call, Throwable t) {
                System.out.println(t.toString());
            }
        });
    }


    @Override
    public void recyclerviewOnClick(int position) {

    }
}
