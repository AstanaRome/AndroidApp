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

import com.example.myapplication.R;
import com.example.myapplication.adapters.VisitAdapter;
import com.example.myapplication.api.NetworkService;
import com.example.myapplication.api.VisitApi;
import com.example.myapplication.entity.Visit;
import com.example.myapplication.fragments.full_info.VisitFullInfoFragment;
import com.example.myapplication.test.ClickInterface;
import com.example.myapplication.test.RecyclerItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitFragment extends Fragment implements ClickInterface {

    public VisitFragment(){
        super(R.layout.fragment_list);
    }
    private RecyclerView rvVisits;
    private VisitAdapter adapter;
    private List<Visit> visits;
    private FloatingActionButton btnAdd;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this::addVisit);

        rvVisits = view.findViewById(R.id.rvList);

        fillAdapter();

        rvVisits.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), rvVisits ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Fragment Fragment_Visit_Info   =  new VisitFullInfoFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragmentContainerView3, Fragment_Visit_Info, "TAG")
                                .commit();
                        Bundle bundle = new Bundle();
                        Visit visit = visits.get(position);
                        bundle.putString("key", "update");
                        bundle.putParcelable("visitInfo", visit);  // Key, value
                        Fragment_Visit_Info.setArguments(bundle);
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
                Visit deletedCourse = visits.get(viewHolder.getAdapterPosition());
                deleteVisit(deletedCourse);
                // below line is to get the position
                // of the item at that position.
                int position = viewHolder.getAdapterPosition();

                // this method is called when item is swiped.
                // below line is to remove item from our array list.
                visits.remove(viewHolder.getAdapterPosition());

                // below line is to notify our item is removed from adapter.
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());

                // below line is to display our snackbar with action.

            }
            // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(rvVisits);
    }


    public void deleteVisit(Visit visit){
        Call<ResponseBody> deleteRequest = NetworkService
                .getInstance()
                .getVisitApi()
                .deleteVisit(visit.getId());
        deleteRequest.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(getContext(), "deleted", Toast.LENGTH_SHORT).show();
                System.out.println(response.code());
                System.out.println(response.body());
                System.out.println(visit.getId());
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // handle failure
                Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
            }
        });;
    }


    private void addVisit(View view) {
        Fragment Fragment_Visit_Info   =  new VisitFullInfoFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView3, Fragment_Visit_Info, "TAG")
                .commit();
        Bundle bundle = new Bundle();
        bundle.putString("key", "save");
        Fragment_Visit_Info.setArguments(bundle);
    }

    private void fillAdapter(){
        NetworkService networkService = NetworkService.getInstance();
        VisitApi api = networkService.getVisitApi();

        Call<List<Visit>> call = api.getAllVisits();

        call.enqueue(new Callback<List<Visit>>() {
            @Override
            public void onResponse(Call<List<Visit>> call, Response<List<Visit>> response) {
                visits = response.body();
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                rvVisits.setHasFixedSize(true);
                rvVisits.setLayoutManager(manager);
                adapter = new VisitAdapter(getContext(), visits);
                rvVisits.setAdapter(adapter);
                System.out.println("test!!!!!!!");
            }

            @Override
            public void onFailure(Call<List<Visit>> call, Throwable t) {
                System.out.println(t.toString());
            }
        });
    }


    @Override
    public void recyclerviewOnClick(int position) {

    }
}
