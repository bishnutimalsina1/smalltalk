package com.example.smalltalk16;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.smalltalk16.Models.Users;
import com.example.smalltalk16.databinding.ActivityEmailMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class email_main extends AppCompatActivity {

    ActivityEmailMainBinding binding;

    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmailMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        //Account creation loading progress
        progressDialog = new ProgressDialog(email_main.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("Your account is being created.");

        //click event listener for sign up button, uses binding
        binding.startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loading when button clicked
                progressDialog.show();

                auth.createUserWithEmailAndPassword(binding.etEmail.getText()
                        .toString(), binding.etPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {
                        //dismiss progress dialog
                        progressDialog.dismiss();

                        if(task.isSuccessful()){
                            Users user = new Users(binding.etuserName.getText().toString(), binding.etEmail.getText().toString(), binding.etPassword.getText().toString());
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(user);
                            Toast.makeText(email_main.this, "User Created Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(email_main.this, SignInActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(email_main.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });



        binding.alreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(email_main.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        binding.signupphonelink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(email_main.this, PhoneNumberActivity.class);
                startActivity(intent);
            }
        });
    }
}