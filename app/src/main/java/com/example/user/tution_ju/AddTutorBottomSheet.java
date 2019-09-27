package com.example.user.tution_ju;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
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

public class AddTutorBottomSheet extends BottomSheetDialogFragment {

    private EditText nameEt,instituteEt,deptEt,subjectsEt,phoneNoEt;
    private Button addBtn;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    AddTutorBottomSheet bottomSheet;

    public AddTutorBottomSheet() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_tutor, container, false);
        init(view);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameEt.getText().toString();
                String institute = instituteEt.getText().toString();
                String dept = deptEt.getText().toString();
                String subjects = subjectsEt.getText().toString();
                String phoneNo = phoneNoEt.getText().toString();

                addValueToDB(name,institute,dept,subjects,phoneNo);
            }
        });

        return view;
    }

    private void addValueToDB(String name, String institute, String dept, String subjects, String phoneNo) {
        String userId = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference tutorRef = databaseReference.child("tutors");
        Tutor tutor = new Tutor(name,institute,dept,subjects,phoneNo);

        tutorRef.push().setValue(tutor).addOnCompleteListener(new OnCompleteListener<Void>() {
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
        nameEt = view.findViewById(R.id.nameET);
        instituteEt = view.findViewById(R.id.instituteET);
        deptEt = view.findViewById(R.id.deptET);
        subjectsEt = view.findViewById(R.id.subjectsET);
        phoneNoEt = view.findViewById(R.id.phoneNoET);
        addBtn = view.findViewById(R.id.addBTN);
    }

}
