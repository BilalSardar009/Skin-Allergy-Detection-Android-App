package com.example.skindisease.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper implements IDB {
    public DBHelper(Context context) {
        super(context, "Search.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table AllDetail(keys INTEGER PRIMARY KEY,Name TEXT,Description TEXT,Symptoms TEXT,Causes TEXT,Treatement TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists AllDetail");
    }

    public  Boolean insertDetailData(int keys,String Name,String Description,String Symptoms,String Causes,String Treatement){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("keys",keys);
        contentValues.put("Name",Name);
        contentValues.put("Description",Description);
        contentValues.put("Symptoms",Symptoms);
        contentValues.put("Causes",Causes);
        contentValues.put("Treatement",Treatement);

        long result=DB.insert("AllDetail",null,contentValues);
        if(result==-1)
        {
            return false;
        }
        else{
            return  true;
        }
    }

    public Cursor getDetailDataRow(int keys){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor= DB.rawQuery("Select * from AllDetail where keys=?",new String[]{String.valueOf(keys)});
        return cursor;
    }

    public Cursor getdata(){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor= DB.rawQuery("Select * from AllDetail",null);
        return cursor;
    }

    public void deleteRows()
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        DB.execSQL("delete from AllDetail");
    }


}
