package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


   private FirebaseAuth firebaseAuth;
    EditText emailEditText;
    EditText passwordEditText;
    Button loginBtn;

    GoogleSignInClient googleSignInClient;
    SignInButton btSignIn;
    TextView resetBtn;
    TextView createAccountBtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btSignIn = findViewById(R.id.bt_sign_in);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        loginBtn = (Button) findViewById(R.id.loginbutton);
        loginBtn.setOnClickListener(this);
        resetBtn = (TextView) findViewById(R.id.resetPwTextView);
        resetBtn.setOnClickListener(this);
        createAccountBtn = (TextView) findViewById(R.id.createAccTextView);
        createAccountBtn.setOnClickListener(this);
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("882320523069-9u7ivrk7gn3dffg5hea6e2m221ana1nv.apps.googleusercontent.com")
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(LoginActivity.this, googleSignInOptions);
        System.out.println("Failed1");
        btSignIn.setOnClickListener((View.OnClickListener) view -> {
            System.out.println("Failed2");
            // Initialize sign in intent
            Intent intent = googleSignInClient.getSignInIntent();
            // Start activity for result
            startActivityForResult(intent, 100);
        });

        firebaseAuth = FirebaseAuth.getInstance();
        // Initialize firebase user
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        // Check condition
        if (firebaseUser != null) {
            // When user already sign in redirect to profile activity
            startActivity(new Intent(LoginActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }


    @Override
    public void onClick(View v) {
        // default method for handling onClick Events..
        switch (v.getId()) {
            case R.id.loginbutton:

                loginUser();
                break;

            case R.id.createAccTextView:
                Intent i2 = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i2);
                break;

            case R.id.resetPwTextView:
                Intent i3 = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                startActivity(i3);
                break;

            default:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check condition

        if (requestCode == 100) {
            // When request code is equal to 100 initialize task
            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            // check condition
            System.out.println("Failed4");
            if (signInAccountTask.isSuccessful()) {
                // When google sign in successful initialize string
                String s = "Google sign in successful";


                // Display Toast
                displayToast(s);
                // Initialize sign in account
                try {
                    // Initialize sign in account
                    GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                    // Check condition
                    if (googleSignInAccount != null) {
                        // When sign in account is not equal to null initialize auth credential
                        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
                        // Check credential
                        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // Check condition
                                if (task.isSuccessful()) {
                                    // When task is successful redirect to profile activity display Toast
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                    System.out.println("Succes");
                                    displayToast("Firebase authentication successful");
                                } else {
                                    System.out.println("Failed3");
                                    // When task is unsuccessful display Toast
                                    displayToast("Authentication Failed :" + task.getException().getMessage());
                                }
                            }
                        });
                    }
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println(signInAccountTask.getException());
            }
        }
    }

    private void displayToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
    public void loginUser(){

        String email = emailEditText.getText().toString().trim();
        String password  = passwordEditText.getText().toString().trim();


        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password", Toast.LENGTH_LONG).show();
            return;
        }




        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                            finish();
                           // updateUI(user);
                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }


                    }
                });

    }

//
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
//        updateUI(currentUser);
//    }
//will work on later


}
