package com.example.user.tution_ju;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class TutorAdapter extends RecyclerView.Adapter<TutorAdapter.ViewHolder> {

    private List<Tutor> tutorList;
    private Context context;

    public TutorAdapter(List<Tutor> tutorList, Context context) {
        this.tutorList = tutorList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tutor_item_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Tutor tutor = tutorList.get(i);
        viewHolder.name.setText(tutor.getName());
        viewHolder.institute.setText(tutor.getInstitute());
        viewHolder.dept.setText(tutor.getDept());
        viewHolder.subjects.setText(tutor.getSubjects());
        viewHolder.phoneNo.setText(tutor.getPhoneNo());
        viewHolder.callingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = tutorList.get(viewHolder.getAdapterPosition()).getPhoneNo();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+Uri.encode(number)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tutorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name,institute,dept,subjects,phoneNo;
        private Button callingBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            callingBtn = itemView.findViewById(R.id.callingBTN);
            name = itemView.findViewById(R.id.nameTV);
            institute = itemView.findViewById(R.id.instituteTV);
            dept = itemView.findViewById(R.id.deptTV);
            subjects = itemView.findViewById(R.id.subjectsTV);
            phoneNo = itemView.findViewById(R.id.phoneNoTV);
        }
    }
}
