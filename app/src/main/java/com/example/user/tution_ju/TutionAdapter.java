package com.example.user.tution_ju;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class TutionAdapter extends RecyclerView.Adapter<TutionAdapter.ViewHolder> {

    private List<Tution> tutionList;
    private Context context;

    public TutionAdapter(List<Tution> tutionList, Context context) {
        this.tutionList = tutionList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.tution_item_layout,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Tution tution = tutionList.get(i);
        viewHolder.sClass.setText(tution.getsClass());
        viewHolder.subjects.setText(tution.getSubjects());
        viewHolder.gender.setText(tution.getGender());
        viewHolder.location.setText(tution.getLocation());
        viewHolder.salary.setText(tution.getSalary());
        viewHolder.phoneNo.setText(tution.getPhoneNo());
        viewHolder.callingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String number = tutionList.get(viewHolder.getAdapterPosition()).getPhoneNo();
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+Uri.encode(number)));
                    context.startActivity(intent);
                }catch (Exception e){
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tutionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView sClass,subjects,gender,location,salary,phoneNo;
        private Button callingBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            callingBtn = itemView.findViewById(R.id.callingBTN);
            sClass = itemView.findViewById(R.id.classTV);
            subjects = itemView.findViewById(R.id.subjectsTV);
            gender = itemView.findViewById(R.id.genderTV);
            location = itemView.findViewById(R.id.locationTV);
            salary = itemView.findViewById(R.id.salaryTV);
            phoneNo = itemView.findViewById(R.id.phoneNoTV);
        }
    }
}
