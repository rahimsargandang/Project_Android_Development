package com.example.twiceapps;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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
import com.google.firebase.database.FirebaseDatabase;

public class Register_user extends AppCompatActivity implements View.OnClickListener {

    private TextView banner, registerUser;
    private EditText editTextFullName, editTextEmail, editTextPassword;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.tv_register_title);
        banner.setOnClickListener(this);

        registerUser = (Button) findViewById(R.id.btn_register);
        registerUser.setOnClickListener(this);

        editTextFullName = (EditText) findViewById(R.id.et_register_fullname);
        editTextEmail = (EditText) findViewById(R.id.et_register_email);
        editTextPassword = (EditText) findViewById(R.id.et_register_password);

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_register_title:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btn_register:
                registerUser();
                break;
        }

    }

    private void registerUser() {
        String fullname = editTextFullName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(fullname.isEmpty()){
            editTextFullName.setError("Full name is required!");
            editTextFullName.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextEmail.setError("Email Address is required!");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide valid Email Address!");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() <8){
            editTextPassword.setError("Minimum Password length should be 8 characters!");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            User user = new User(fullname,email);

                            FirebaseDatabase.getInstance("https://twice-apps-60be0-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(Register_user.this, "Registration Successful!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        //redirect Login
                                    }else{
                                        Toast.makeText(Register_user.this, "Registration Failed! Try Again!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }

                                }
                            });
                        }else{
                            Log.d("---->",""+task.getException());
                            //Toast.makeText(Register_user.this, "Registration Failed!!!!! Try Again!", Toast.LENGTH_LONG).show();
                            //progressBar.setVisibility(View.GONE);
                        }

                    }
                });

    }
}