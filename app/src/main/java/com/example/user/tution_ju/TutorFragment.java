package com.example.user.tution_ju;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TutorFragment extends Fragment {

    private FloatingActionButton addFAB;
    private RecyclerView recyclerView;
    private List<Tutor> tutors;
    private TutorAdapter adapter;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    public TutorFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutor, container, false);
        init(view);
        showTutor(view);
        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddTutorBottomSheet addTutorBottomSheet = new AddTutorBottomSheet();
                addTutorBottomSheet.show(getFragmentManager(), "Add tutor");
            }
        });

        return view;
    }

    private void showTutor(View view) {
        String userId = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference tutorRef = databaseReference.child("tutors");

        tutorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    tutors.clear();

                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Tutor tutor = data.getValue(Tutor.class);
                        tutors.add(tutor);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void init(View view) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        addFAB = view.findViewById(R.id.addTutorFAB);
        recyclerView = view.findViewById(R.id.recyclerView);
        tutors = new ArrayList<>();
        adapter = new TutorAdapter(tutors, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}
