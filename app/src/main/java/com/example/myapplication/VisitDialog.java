package com.example.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitDialog extends DialogFragment {
    private EditText etCause;

    private ContactStorageChanger changer;//ссылка на активити????

    @Override
    public void onAttach(@NonNull Context context) {
        changer = (ContactStorageChanger) context;
        super.onAttach(context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View container = getLayoutInflater().inflate(R.layout.dialog_visit, null);
        etCause = container.findViewById(R.id.etCause);
        builder.setTitle("New task");
        builder.setView(container);
        builder.setPositiveButton("Save", (dialogInterface, which) -> {
//            String taskName = etTask.getText().toString();
//            System.out.println(taskName);
//            NetworkService
//                    .getInstance()
//                    .getTaskApi()
//                    .saveTask(new Task(taskName, false))
//                    .enqueue(new Callback<Task>() {
//                        @Override
//                        public void onResponse(Call<Task> call, Response<Task> response) {
//                            Task task = response.body();
//                            System.out.println("first !!!!!!!!!!!!!!!!!!!!!!!!!!!");
//                             changer.change();
//                        }
//
//                        @Override
//                        public void onFailure(Call<Task> call, Throwable t) {
//
//                        }
//                    });
            System.out.println("second !!!!!!!!!!!!!!!!!!!!!!!!!!!");

        });

        return builder.create();
    }

    public interface ContactStorageChanger{
        void change();
    }
}
