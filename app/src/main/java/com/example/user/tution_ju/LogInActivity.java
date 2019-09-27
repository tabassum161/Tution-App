package com.example.user.tution_ju;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class LogInActivity extends AppCompatActivity {

    private FirebaseAuth mauth;
    EditText editTextEmail,editTextPassword;
    ProgressBar progressBar;
    TextView signUp;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        signUp = findViewById(R.id.textViewSignup);
        loginBtn = findViewById(R.id.buttonLogin);
        mauth=FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInActivity.this,SignUpActivity.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }

    private void userLogin(){

            String email=editTextEmail.getText().toString().trim();
            String password=editTextPassword.getText().toString().trim();
            if(email.isEmpty())
            {
                editTextEmail.setError("Email is Required");
                editTextEmail.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                editTextEmail.setText("Please enter a valid email");
                editTextEmail.requestFocus();
                return;
            }
            if(password.isEmpty())
            {
                editTextPassword.setError("Password is required");
                editTextPassword.requestFocus();
                return;
            }
            if(password.length()<6)
            {
                editTextPassword.setText("Minimum length of password should be 6");
                editTextPassword.requestFocus();
                return;
            }
            progressBar.setVisibility(View.VISIBLE);

     mauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
         @Override
         public void onComplete(@NonNull Task<AuthResult> task) {
             progressBar.setVisibility(View.GONE);
             if(task.isSuccessful())
             {
                 Intent intent=new Intent(LogInActivity.this,HomeActivity.class);
                 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                 startActivity(intent);
                 Toast.makeText(getApplicationContext(),"successful",Toast.LENGTH_SHORT).show();


             }
             else
             {
                 Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
             }
         }
     });

    }
}
