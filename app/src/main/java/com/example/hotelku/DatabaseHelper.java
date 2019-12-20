package com.example.hotelku;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Nama Tebel
    public static final String TABLE_NAME="AdminTable";
    public static final String TABLE_NAME2="BookingTable";
    // Nama kolom dalam tabel
    public static final String _ID = "_id";
    public static final String Nama = "nama";
    public static final String Email = "email";
    public static final String Nohp= "nohp";
    public static final String Jmlhruangan = "jumlahruangan";
    public static final String Tanggal= "tangal";

    public static final String Table_Column_ID="id";

    public static final String Table_Column_1_Name="name";

    public static final String Table_Column_2_Email="email";

    public static final String Table_Column_3_NoHp="nohp";

    public static final String Table_Column_4_Password="password";


    // Nama Database
    static final String DB_NAME = "PROJECT.DB";
    // Versi Database
    static final int DB_VERSION = 1;
    // Membuat query tabel
    private static final String CREATE_TABLE2 = "create table " + TABLE_NAME2+ "(" + _ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+ Nama + " TEXT NOT NULL, " + Email + " TEXT NOT NULL, " + Nohp + " TEXT NOT NULL,"+ Jmlhruangan+" TEXT NOT NULL,"+Tanggal+" TEXT NOT NULL );";
    String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+" INTEGER PRIMARY KEY, "+Table_Column_1_Name+" VARCHAR, "+Table_Column_2_Email+" VARCHAR, "+Table_Column_3_NoHp+" VARCHAR, "+Table_Column_4_Password+" VARCHAR)";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE2);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

}
