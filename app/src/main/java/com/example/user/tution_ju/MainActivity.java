package com.example.user.tution_ju;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FrameLayout masterFrameLayout;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tutiondb=new DatabaseHelper(this);
        //ViewData();

        init();
        if (firebaseAuth.getCurrentUser() == null){
            startActivity(new Intent(MainActivity.this,LogInActivity.class));
        }
        else sendToHome();
    }

    private void sendToHome() {
        startActivity(new Intent(MainActivity.this,HomeActivity.class));
    }

    private void init() {
        masterFrameLayout = findViewById(R.id.master_frameLayout);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    /*public void onClickNeedTuition(View view)
    {
         *//*Intent intent=new Intent(this,Main3ActivityTuition.class);
         startActivity(intent);*//*
    }*/
   /* public void ViewData(){
        onClickNeedTution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = tutiondb.showData();

                if (data.getCount() == 0) {
                    display("Error", "No Data Found.");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (data.moveToNext()) {
                    buffer.append("ID: " + data.getString(0) + "\n");
                    buffer.append("Name: " + data.getString(1) + "\n");
                    buffer.append("LOCATION: " + data.getString(2) + "\n");
                  *//*  buffer.append("GENDER: " + data.getString(3) + "\n");
                    buffer.append("SUBJECT: " + data.getString(4) + "\n");*//*

                    display("All Available Tuitions:", buffer.toString());
                }
            }
        });

    }

    public void display(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }*/
}
