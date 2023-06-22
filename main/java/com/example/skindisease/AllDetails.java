package com.example.skindisease;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.skindisease.DB.DBHelper;
import com.example.skindisease.DB.IDB;

import java.util.ArrayList;

public class AllDetails extends AppCompatActivity {
    ArrayList<SingleDetail> Details = new ArrayList<>();
    IDB DB;
    RecyclerView recyclerView;
    DetailAdapter detailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_details);
        DB = new DBHelper(this);
        LoadData();
        recyclerView = findViewById(R.id.recyclerView);
        detailsAdapter = new DetailAdapter(this, Details, DB);
        recyclerView.setAdapter(detailsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void LoadData() {
        Cursor cursor = DB.getdata();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "DB is Empty", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
                String Name = cursor.getString(1);
                String Description = cursor.getString(2);
                String Symptoms = cursor.getString(3);
                String Causes = cursor.getString(4);
                String Treatement = cursor.getString(5);
                SingleDetail s = new SingleDetail(Name, Description, Symptoms, Causes, Treatement);
                Details.add(s);


            }
            Toast.makeText(this, "All Data Is Entered", Toast.LENGTH_SHORT).show();

        }
    }
}