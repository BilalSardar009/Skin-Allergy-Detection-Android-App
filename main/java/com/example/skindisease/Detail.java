package com.example.skindisease;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.skindisease.DB.DBHelper;
import com.example.skindisease.DB.IDB;

public class Detail extends AppCompatActivity {
TextView Name,Description,Symptoms,Causes,Treatement;
IDB DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Name=findViewById(R.id.Name);
        Description=findViewById(R.id.Description);
        Symptoms=findViewById(R.id.Symptoms);
        Causes=findViewById(R.id.Causes);
        Treatement=findViewById(R.id.Treatement);

        DB=new DBHelper(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Name.setText(extras.getString("Name"));
            Name.setMovementMethod(new ScrollingMovementMethod());
            Description.setText(extras.getString("Description"));
            Symptoms.setText(extras.getString("Symptoms"));
            Symptoms.setMovementMethod(new ScrollingMovementMethod());
            Causes.setText(extras.getString("Causes"));
            Causes.setMovementMethod(new ScrollingMovementMethod());
            Treatement.setText(extras.getString("Treatement"));
            Treatement.setMovementMethod(new ScrollingMovementMethod());
            Cursor cursor=DB.getdata();
            int key=cursor.getCount()+1;
            DB.insertDetailData(key,extras.getString("Name"),extras.getString("Description"),extras.getString("Symptoms"),extras.getString("Causes"),extras.getString("Treatement"));


        }
    }
}