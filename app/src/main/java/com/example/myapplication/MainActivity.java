package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.fragments.MainFragment;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button btnHome;
    Button signOutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(this::showFirstFragment);
        signOutBtn = (Button) findViewById(R.id.signOutButton2);
        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                Toast.makeText(getApplicationContext(), "Signing Out", Toast.LENGTH_SHORT).show();
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();

            }
        });
    }
    private void showFirstFragment(View view) {
        Fragment Fragment_first=new MainFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView3, Fragment_first, "TAG")
                .commit();
    }
}