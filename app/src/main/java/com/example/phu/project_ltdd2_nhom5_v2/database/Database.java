package com.example.phu.project_ltdd2_nhom5_v2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.phu.project_ltdd2_nhom5_v2.model.Chi;

public class Database extends SQLiteOpenHelper {
    private static String DBNAME = "quanlychitieu";
    private static int VERSION = 1;

//    private String TABLE  =  "nhom_chi_tieu";
//    private String ID = "id";
//    private String NAME = "name";
//    private String PHAN_TRAM = "phan_tram";
//    private String TIEN_CON_LAI = "tien_con_lai";


    public Database( Context context) {

        super(context, DBNAME, null, VERSION);
    }
    public void insert_du_lieu_mau(){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", "Nhóm di chuyển");
        values.put("phan_tram", "0.0");
        values.put("tien_con_lai", "0");
        db.insert("nhom_chi_tieu", null, values);

        values.put("name", "Nhóm ăn uống");
        values.put("phan_tram", "0.0");
        values.put("tien_con_lai", "0");
        db.insert("nhom_chi_tieu", null, values);

        values.put("name", "Nhóm mua sắm");
        values.put("phan_tram", "0.0");
        values.put("tien_con_lai", "0");
        db.insert("nhom_chi_tieu", null, values);

        values.put("name", "Nhóm giải trí");
        values.put("phan_tram", "0.0");
        values.put("tien_con_lai", "0");
        db.insert("nhom_chi_tieu", null, values);

//        chi
        ContentValues chi_value = new ContentValues();
        chi_value.put("nhom_chi_tieu_id", "1");
        chi_value.put("note", "note0");
        chi_value.put("ngay_chi_tieu", "2019-01-09");
        chi_value.put("so_tien_chi", "0");
        db.insert("chi", null, chi_value);

        chi_value.put("nhom_chi_tieu_id", "1");
        chi_value.put("note", "note1");
        chi_value.put("ngay_chi_tieu", "2019-01-09");
        chi_value.put("so_tien_chi", "0");
        db.insert("chi", null, chi_value);

        chi_value.put("nhom_chi_tieu_id", "1");
        chi_value.put("note", "note2");
        chi_value.put("ngay_chi_tieu", "2019-01-09");
        chi_value.put("so_tien_chi", "0");
        db.insert("chi", null, chi_value);

        chi_value.put("nhom_chi_tieu_id", "1");
        chi_value.put("note", "note3");
        chi_value.put("ngay_chi_tieu", "2019-01-09");
        chi_value.put("so_tien_chi", "0");
        db.insert("chi", null, chi_value);

        chi_value.put("nhom_chi_tieu_id", "1");
        chi_value.put("note", "note3");
        chi_value.put("ngay_chi_tieu", "2019-01-09");
        chi_value.put("so_tien_chi", "0");
        db.insert("chi", null, chi_value);


        chi_value.put("nhom_chi_tieu_id", "1");
        chi_value.put("note", "note3");
        chi_value.put("ngay_chi_tieu", "2019-01-09");
        chi_value.put("so_tien_chi", "0");
        db.insert("chi", null, chi_value);


        chi_value.put("nhom_chi_tieu_id", "1");
        chi_value.put("note", "note3");
        chi_value.put("ngay_chi_tieu", "2019-01-09");
        chi_value.put("so_tien_chi", "0");
        db.insert("chi", null, chi_value);
//thu
        ContentValues thu_values = new ContentValues();
        thu_values.put("note", "note3");
        thu_values.put("ngay_thu", "2019-01-09");
        thu_values.put("so_tien_thu", "0");
        db.insert("thu", null, thu_values);

        thu_values.put("note", "note3");
        thu_values.put("ngay_thu", "2019-01-09");
        thu_values.put("so_tien_thu", "0");
        db.insert("thu", null, thu_values);

        thu_values.put("note", "note3");
        thu_values.put("ngay_thu", "2019-01-09");
        thu_values.put("so_tien_thu", "0");
        db.insert("thu", null, thu_values);

        thu_values.put("note", "note3");
        thu_values.put("ngay_thu", "2019-01-09");
        thu_values.put("so_tien_thu", "0");
        db.insert("thu", null, thu_values);

        thu_values.put("note", "note3");
        thu_values.put("ngay_thu", "2019-01-09");
        thu_values.put("so_tien_thu", "0");
        db.insert("thu", null, thu_values);

        thu_values.put("note", "note3");
        thu_values.put("ngay_thu", "2019-01-09");
        thu_values.put("so_tien_thu", "0");
        db.insert("thu", null, thu_values);

        thu_values.put("note", "note3");
        thu_values.put("ngay_thu", "2019-01-09");
        thu_values.put("so_tien_thu", "0");
        db.insert("thu", null, thu_values);
//vi
        ContentValues vi_values = new ContentValues();
        vi_values.put("so_du", "0");
        vi_values.put("thang_nam", "2019-01-09");
        db.insert("vi", null, vi_values);

        vi_values.put("so_du", "0");
        vi_values.put("thang_nam", "2019-02-09");
        db.insert("vi", null, vi_values);

        vi_values.put("so_du", "0");
        vi_values.put("thang_nam", "2019-03-09");
        db.insert("vi", null, vi_values);

        vi_values.put("so_du", "0");
        vi_values.put("thang_nam", "2019-04-09");
        db.insert("vi", null, vi_values);

        vi_values.put("so_du", "0");
        vi_values.put("thang_nam", "2019-05-09");
        db.insert("vi", null, vi_values);

        vi_values.put("so_du", "0");
        vi_values.put("thang_nam", "2019-06-09");
        db.insert("vi", null, vi_values);
        db.close();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_nhom_chi_tieu_sql = "create table nhom_chi_tieu (id integer primary key autoincrement, name text, phan_tram float, tien_con_lai float)";
        db.execSQL(create_nhom_chi_tieu_sql);

        String create_chi = "create table chi (id integer primary key autoincrement, nhom_chi_tieu_id integer, note text, ngay_chi_tieu date,so_tien_chi float)";
        db.execSQL(create_chi);
        String create_thu = "create table thu (id integer primary key autoincrement, note text, ngay_thu date,so_tien_thu float)";
        db.execSQL(create_thu);
        String create_vi = "create table vi (id integer primary key autoincrement,so_du float, thang_nam date)";
        db.execSQL(create_vi);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert_chi(Chi chi){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues chi_value = new ContentValues();
        chi_value.put("nhom_chi_tieu_id", chi.getNhom_chi_tieu());
        chi_value.put("note", chi.getGhi_chu());
//        chi_value.put("ngay_chi_tieu", chi.getNgay_chi_tieu().toString());
        chi_value.put("so_tien_chi", chi.getSo_tien_chi());
        db.insert("chi", null, chi_value);
    }
}
