package com.example.skindisease.DB;

import android.database.Cursor;

public interface IDB {
    public void deleteRows();
    public Cursor getdata();
    public Cursor getDetailDataRow(int keys);
    public  Boolean insertDetailData(int keys,String Name,String Description,String Symptoms,String Causes,String Treatement);
}
