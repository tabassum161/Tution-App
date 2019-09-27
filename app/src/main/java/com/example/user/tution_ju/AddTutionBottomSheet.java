package com.example.user.tution_ju;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTutionBottomSheet extends BottomSheetDialogFragment {

    private EditText sClassEt,subjectsEt,genderEt,locationEt,salaryEt,phoneNoEt;
    private Button addBtn;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    public AddTutionBottomSheet() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_tutions_bottom_sheet,container,false);
        init(view);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sClass = sClassEt.getText().toString();
                String subjects = subjectsEt.getText().toString();
                String gender = genderEt.getText().toString();
                String location = locationEt.getText().toString();
                String salary = salaryEt.getText().toString();
                String phoneNo = phoneNoEt.getText().toString();

                addValueToDB(sClass,subjects,gender,location,salary,phoneNo);
            }
        });


        return view;
    }

    private void addValueToDB(String sClass, String subjects, String gender, String location,
                              String salary, String phoneNo) {
        String userId = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference tutionRef = databaseReference.child("tutions");
        Tution tution = new Tution(sClass,subjects,gender,location,salary,phoneNo);

        tutionRef.push().setValue(tution).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    dismiss();
                    Toast.makeText(getContext(), "Successfully added", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void init(View view) {
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        sClassEt = view.findViewById(R.id.classET);
        subjectsEt = view.findViewById(R.id.subjectsET);
        genderEt = view.findViewById(R.id.genderET);
        locationEt = view.findViewById(R.id.locationET);
        salaryEt = view.findViewById(R.id.salaryET);
        phoneNoEt = view.findViewById(R.id.phoneNoET);
        addBtn = view.findViewById(R.id.addBTN);

    }
}
