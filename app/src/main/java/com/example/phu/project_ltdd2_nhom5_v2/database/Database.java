package com.example.phu.project_ltdd2_nhom5_v2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.phu.project_ltdd2_nhom5_v2.model.Chi;
import com.example.phu.project_ltdd2_nhom5_v2.model.NhomChiTieu;
import com.example.phu.project_ltdd2_nhom5_v2.model.Thu;
import com.example.phu.project_ltdd2_nhom5_v2.model.ViTien;

import java.util.ArrayList;

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

//chi
        ContentValues chi_value = new ContentValues();
        chi_value.put("nhom_chi_tieu_id", "1");
        chi_value.put("img_nhom_chi_tieu", "1");
        chi_value.put("note", "note0");
        chi_value.put("ngay_chi_tieu", "1900-2-09");
        chi_value.put("so_tien_chi", "100000");
        db.insert("chi", null, chi_value);

        chi_value.put("nhom_chi_tieu_id", "2");
        chi_value.put("img_nhom_chi_tieu", "2");
        chi_value.put("note", "note1");
        chi_value.put("ngay_chi_tieu", "1900-3-09");
        chi_value.put("so_tien_chi", "100000");
        db.insert("chi", null, chi_value);

        chi_value.put("nhom_chi_tieu_id", "3");
        chi_value.put("img_nhom_chi_tieu", "3");
        chi_value.put("note", "note2");
        chi_value.put("ngay_chi_tieu", "1900-4-09");
        chi_value.put("so_tien_chi", "100000");
        db.insert("chi", null, chi_value);

        chi_value.put("nhom_chi_tieu_id", "4");
        chi_value.put("img_nhom_chi_tieu", "4");
        chi_value.put("note", "note3");
        chi_value.put("ngay_chi_tieu", "1900-5-09");
        chi_value.put("so_tien_chi", "100000");
        db.insert("chi", null, chi_value);

        chi_value.put("nhom_chi_tieu_id", "1");
        chi_value.put("img_nhom_chi_tieu", "1");
        chi_value.put("note", "note3");
        chi_value.put("ngay_chi_tieu", "1900-5-09");
        chi_value.put("so_tien_chi", "100000");
        db.insert("chi", null, chi_value);


        chi_value.put("nhom_chi_tieu_id", "1");
        chi_value.put("img_nhom_chi_tieu", "1");
        chi_value.put("note", "note3");
        chi_value.put("ngay_chi_tieu", "1900-1-09");
        chi_value.put("so_tien_chi", "100000");
        db.insert("chi", null, chi_value);


        chi_value.put("nhom_chi_tieu_id", "1");
        chi_value.put("img_nhom_chi_tieu", "1");
        chi_value.put("note", "note3");
        chi_value.put("ngay_chi_tieu", "1900-2-09");
        chi_value.put("so_tien_chi", "0");
        db.insert("chi", null, chi_value);
//thu
        ContentValues thu_values = new ContentValues();
        thu_values.put("note", "note3");
        thu_values.put("ngay_thu", "1900-2-09");
        thu_values.put("so_tien_thu", "1100000");
        db.insert("thu", null, thu_values);

        thu_values.put("note", "note3");
        thu_values.put("ngay_thu", "1900-3-09");
        thu_values.put("so_tien_thu", "1000000");
        db.insert("thu", null, thu_values);

        thu_values.put("note", "note3");
        thu_values.put("ngay_thu", "1900-2-09");
        thu_values.put("so_tien_thu", "1200000");
        db.insert("thu", null, thu_values);

        thu_values.put("note", "note3");
        thu_values.put("ngay_thu", "1900-2-09");
        thu_values.put("so_tien_thu", "1300000");
        db.insert("thu", null, thu_values);

        thu_values.put("note", "note3");
        thu_values.put("ngay_thu", "1900-3-09");
        thu_values.put("so_tien_thu", "1400000");
        db.insert("thu", null, thu_values);

        thu_values.put("note", "note3");
        thu_values.put("ngay_thu", "1900-3-09");
        thu_values.put("so_tien_thu", "1500000");
        db.insert("thu", null, thu_values);

        thu_values.put("note", "note3");
        thu_values.put("ngay_thu", "1900-3-09");
        thu_values.put("so_tien_thu", "1500000");
        db.insert("thu", null, thu_values);
//vi
        ContentValues vi_values = new ContentValues();
        vi_values.put("so_du", "1000000");
        vi_values.put("thang_nam", "1900-1-09");
        db.insert("vi", null, vi_values);

        vi_values.put("so_du", "2000000");
        vi_values.put("thang_nam", "1900-1-09");
        db.insert("vi", null, vi_values);

        vi_values.put("so_du", "2500000");
        vi_values.put("thang_nam", "1900-2-09");
        db.insert("vi", null, vi_values);

        vi_values.put("so_du", "400000");
        vi_values.put("thang_nam", "1900-2-09");
        db.insert("vi", null, vi_values);

        vi_values.put("so_du", "200000");
        vi_values.put("thang_nam", "1900-2-09");
        db.insert("vi", null, vi_values);

        vi_values.put("so_du", "800000");
        vi_values.put("thang_nam", "1900-3-09");
        db.insert("vi", null, vi_values);
        db.close();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_nhom_chi_tieu_sql = "create table nhom_chi_tieu (id integer primary key autoincrement, name text, phan_tram float, tien_con_lai float)";
        db.execSQL(create_nhom_chi_tieu_sql);

        String create_chi = "create table chi (id integer primary key autoincrement, nhom_chi_tieu_id integer, img_nhom_chi_tieu integer, note text, ngay_chi_tieu date, so_tien_chi float)";
        db.execSQL(create_chi);
        String create_thu = "create table thu (id integer primary key autoincrement, note text, ngay_thu date, so_tien_thu float)";
        db.execSQL(create_thu);
        String create_vi = "create table vi (id integer primary key autoincrement,so_du float, thang_nam date)";
        db.execSQL(create_vi);
    }

    /*
    * Created by: Nguyen Linh Chan
    * Date: 5/4/2019
    * Trang thong ke chi tieu
    * */

    //Get danh sach chi tieu
    public void getChiTieu(ArrayList<Chi> members){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from chi",null);
        if(cursor.moveToFirst()){
            do {
                Chi chi = new Chi();
                chi.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                chi.setNhom_chi_tieu(cursor.getString(cursor.getColumnIndex("nhom_chi_tieu_id")));
                chi.setImg_nhom_chi_tieu(Integer.parseInt(cursor.getString(cursor.getColumnIndex("img_nhom_chi_tieu"))));
                chi.setGhi_chu(cursor.getString(cursor.getColumnIndex("note")));
                chi.setSo_tien_chi(Float.parseFloat(cursor.getString(cursor.getColumnIndex("so_tien_chi"))));
                chi.setNgay_chi_tieu(cursor.getString(cursor.getColumnIndex("ngay_chi_tieu")));
                members.add(chi);
            }while (cursor.moveToNext());
        }
        db.close();
    }

//    values.put("name", "Nhóm giải trí");
//        values.put("phan_tram", "0.0");
//        values.put("tien_con_lai", "0");
    public void getNhomChiTieu(ArrayList<NhomChiTieu> members){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from nhom_chi_tieu",null);
        if(cursor.moveToFirst()){
            do {
                NhomChiTieu nct = new NhomChiTieu();
                nct.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                nct.setName(cursor.getString(cursor.getColumnIndex("name")));
                members.add(nct);
            }while (cursor.moveToNext());
        }
        db.close();
    }

    public void getThu(ArrayList<Thu> members) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from thu",null);
        if(cursor.moveToFirst()){
            do {
                Thu thu_ = new Thu();
                thu_.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                thu_.setNgay_thu(cursor.getString(cursor.getColumnIndex("ngay_thu")));
                thu_.setSo_tien_thu(Float.parseFloat(cursor.getString(cursor.getColumnIndex("so_tien_thu"))));
                members.add(thu_);
            }while (cursor.moveToNext());
        }
        db.close();
    }

    public void getVi(ArrayList<ViTien> members)
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from vi", null);
        if(cursor.moveToFirst())
        {
            do {
                ViTien vi = new ViTien();
                vi.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                vi.setSo_du(Float.parseFloat(cursor.getString(cursor.getColumnIndex("so_du"))));
                vi.setThang(cursor.getString(cursor.getColumnIndex("thang_nam")));
                members.add(vi);
            }while (cursor.moveToNext());
        }
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
