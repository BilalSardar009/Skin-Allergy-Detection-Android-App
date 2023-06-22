package com.example.skindisease;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skindisease.DB.IDB;

import java.util.ArrayList;
import java.util.List;

public class DetailAdapter  extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder> {

    IDB DB;
    Context context;
    ArrayList<SingleDetail> AllDetail = new ArrayList<>();
    public DetailAdapter(Context ct,ArrayList<SingleDetail> st,IDB db){

        context=ct;
        DB=db;
        AllDetail=st;


    }
    @NonNull
    @Override
    public DetailAdapter.DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.singledetail,parent,false);

        return new DetailAdapter.DetailViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.DetailViewHolder holder, int position) {
        int pos=position;
        holder.Name.setText(AllDetail.get(pos).getName());
        holder.Description.setText(AllDetail.get(pos).getDescription());
        holder.Description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,Detail.class);
                intent.putExtra("Name",AllDetail.get(pos).getName());
                intent.putExtra("Description",AllDetail.get(pos).getDescription());
                intent.putExtra("Symptoms",AllDetail.get(pos).getSymptoms());
                intent.putExtra("Causes",AllDetail.get(pos).getCauses());
                intent.putExtra("Treatement",AllDetail.get(pos).getTreatement());

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return AllDetail.size();
    }
    public class DetailViewHolder extends RecyclerView.ViewHolder {
        TextView Name;
        TextView Description;
        ConstraintLayout mainLayout;
        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            Name=itemView.findViewById(R.id.Name);
            Description=itemView.findViewById(R.id.Description);

        }
    }
}
