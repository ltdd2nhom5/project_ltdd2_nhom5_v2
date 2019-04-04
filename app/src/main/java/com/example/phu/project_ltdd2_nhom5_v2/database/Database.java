package com.example.phu.project_ltdd2_nhom5_v2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {
    private static String DBNAME = "quanlychitieu";
    private static int VERSION = 1;

    private String TABLE  =  "nhom_chi_tieu";
    private String ID = "id";
    private String NAME = "name";
    private String PHAN_TRAM = "phan_tram";
    private String TIEN_CON_LAI = "tien_con_lai";


    public Database( Context context) {

        super(context, DBNAME, null, VERSION);
    }
    public void insert(){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, "test");
        values.put(PHAN_TRAM, "test");
        values.put(TIEN_CON_LAI, "test");

        db.insert(TABLE, null, values);
        db.close();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table "+TABLE+" ("
                +ID+" integer primary key autoincrement, "
                +NAME+" text, "
                +PHAN_TRAM+" float, "
                +TIEN_CON_LAI+" float)";
        Log.d("test_","created");
        db.execSQL(sql);

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
