package com.example.emelyanovpmppr27;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    EditText emailTb;
    EditText passTb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        emailTb = findViewById(R.id.emailTb);
        passTb = findViewById(R.id.passTb);
    }

    public void regLabelClick(View v){
        startRegActivity();
    }


    public void LogInBtnClick(View V){
        String email = emailTb.getText().toString();
        String password = passTb.getText().toString();

        if(email.isEmpty() || password.isEmpty()){
            NotificationDialog dialog = new NotificationDialog("Ошибка", "Не все поля заполнены!");
            dialog.show(getSupportFragmentManager(), "errDial");
            return;
        }


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startMainActivity();
                        } else {
                            String message = task.getException().getMessage();

                            NotificationDialog dialog = new NotificationDialog("Ошибка", message);
                            dialog.show(getSupportFragmentManager(), "errDial");
                        }
                    }
                });
    }

    private void startRegActivity(){
        Intent startRegActivity = new Intent(this, RegistrationActivity.class);

        startActivity(startRegActivity);
    }

    private void startMainActivity(){
        Intent startMainActivity = new Intent(this, MainActivity.class);

        startActivity(startMainActivity);
        finish();
    }

}