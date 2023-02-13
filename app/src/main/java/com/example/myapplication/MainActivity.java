package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {
    private Button btnHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(this::showFirstFragment);
    }
    private void showFirstFragment(View view) {
        Fragment Fragment_first=new MainFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView3, Fragment_first, "TAG")
                .commit();
    }
}