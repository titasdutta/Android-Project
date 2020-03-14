package com.example.android.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class WelcomeActivity extends AppCompatActivity {

    private FrameLayout mFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button welLogin = (Button) findViewById(R.id.welcome_login);
        Button welReg = (Button) findViewById(R.id.welcome_register);


        welLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                    startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            }
        }) ;


        welReg .setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            }
        }) ;

    }


}
