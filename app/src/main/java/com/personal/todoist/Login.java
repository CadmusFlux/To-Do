package com.personal.todoist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button mLoginBtn;
    TextView mCreateBtn;
    ProgressBar progressbar;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail=findViewById(R.id.Email);
        mPassword=findViewById(R.id.password);
        progressbar=findViewById(R.id.progressBar);
        fAuth=FirebaseAuth.getInstance();
        mLoginBtn=findViewById(R.id.login_btn);
        mCreateBtn=findViewById(R.id.createText);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=mEmail.getText().toString().trim();
                String  password= mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required");
                    return;

                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required");
                    return;

                }
                if(password.length()<6){
                    mPassword.setError("Password must be greater than 6 characters");
                    return;

                }
                progressbar.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(Login.this, "Error"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressbar.setVisibility(View.GONE);
                        }

                    }
                });

            }
        });
        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });
    }
}