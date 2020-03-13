package com.example.android.chatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;

public class AuthActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        findViewById(R.id.sign_in_button).setOnClickListener(this);

        // Initialize Firebase Auth Object
        mAuth = FirebaseAuth.getInstance();

    }



    // Starting check if the user is already signed in

    @Override
    protected void onStart() {
        super.onStart();

        // Check if user is signed in and update the view
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }


    private void updateUI(FirebaseUser curr) {}
}
