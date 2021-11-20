package com.example.emelyanovpmppr27;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void regLabelClick(View v){
        Intent startRegActivity = new Intent(this, RegistrationActivity.class);

        startActivity(startRegActivity);
    }

    public void forgotPassClick(View v){
        Intent startMainActivity = new Intent(this, MainActivity.class);

        startActivity(startMainActivity);
        finish();
    }

}