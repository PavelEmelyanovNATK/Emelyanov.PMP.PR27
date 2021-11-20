package com.example.emelyanovpmppr27;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent mainActivityIntent = new Intent(this, LogInActivity.class);
        startActivity(mainActivityIntent);
        finish();
    }
}