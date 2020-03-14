package com.example.android.chatapp;

//import android.support.v7.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AuthActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private DatabaseReference mRef;

    private EditText mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        final EditText mUsername = (EditText) findViewById(R.id.user_name);
        mEmail = (EditText) findViewById(R.id.user_email);
        final EditText mPassword = (EditText) findViewById(R.id.password);

        Button mSignUpBtn = (Button) findViewById(R.id.sign_up_btn);

        // Initialize Firebase Auth Object
        mAuth = FirebaseAuth.getInstance();


        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString();
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(AuthActivity.this, "All fields are necessary!", Toast.LENGTH_SHORT).show();
                }
                else {
                    signUp(username, password, email);
                }
            }
        });

    }


    private void signUp(final String userName, String mPassword, String mEmail ) {
        mAuth.createUserWithEmailAndPassword(mEmail, mPassword)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            FirebaseUser newUser = mAuth.getCurrentUser();
                            String userId = mAuth.getUid();

                            mRef = FirebaseDatabase.getInstance().getReference("Users").child(userId);


                            HashMap<String, String> userData = new HashMap<>();
                            userData.put("id", userId);
                            userData.put("username", userName);


                            mRef.setValue(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(AuthActivity.this, MainActivity.class);

                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            } );
                        }
                        else {
                            Toast.makeText(AuthActivity.this, "Sign Up Failed!", Toast.LENGTH_SHORT).show();
                        }
            }
        });
    }
}
