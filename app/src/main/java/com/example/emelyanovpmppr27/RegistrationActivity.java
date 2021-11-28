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

public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText emailTb;
    EditText passTb;
    EditText repPassTb;
    EditText nameTb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        emailTb = findViewById(R.id.emailEdT);
        passTb = findViewById(R.id.passEdT);
        repPassTb = findViewById(R.id.repPassEdT);
        nameTb = findViewById(R.id.nameEdT);
    }

    public void SignUpBtnClick(View V){
        String email = emailTb.getText().toString();
        String password = passTb.getText().toString();
        String repPassword = repPassTb.getText().toString();
        String name = nameTb.getText().toString();
        NotificationDialog dialog;

        String message = "";

        if(!(email.isEmpty() || password.isEmpty() || name.isEmpty())){
            if(!Validator.Companion.IsEmail(email))
                message += "Неверный формат Email!\n";

            if(!Validator.Companion.IsPassword(password))
                message += "Неверный формат пароля! Минимальная длина - 8 символов. В пароле должна быть хотя бы одна цифра, одна маленькая и одна заглавная буква.\n";
            else
            if(!password.equals(repPassword)){
                message += "Пароли не совпадают!";
            }

        }
        else{
            message = "Не все поля заполнены!";
        }
        if(message != "") {
            dialog = new NotificationDialog("Ошибка", message);
            dialog.show(getSupportFragmentManager(), "errDial");
            return;
        }



        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            startMainActivity();
                        } else {
                            String mess = task.getException().getMessage();
                            NotificationDialog dialog = new NotificationDialog("Ошибка", mess);
                            dialog.show(getSupportFragmentManager(), "errDial");
                        }
                    }
                });


    }
    private void startMainActivity(){
        Intent startMainActivity = new Intent(this, MainActivity.class);

        startActivity(startMainActivity);
        finish();
    }


}