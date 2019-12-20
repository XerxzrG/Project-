package com.example.hotelku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;
    public DBManager(Context c) {
        context = c;
    }
    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        dbHelper.close();
    }
    public void insert(String nama, String email, String nohp, String jumlahruangan, String tanggal) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.Nama, nama);
        contentValue.put(DatabaseHelper.Email, email);
        contentValue.put(DatabaseHelper.Nohp, nohp);
        contentValue.put(DatabaseHelper.Jmlhruangan, jumlahruangan);
        contentValue.put(DatabaseHelper.Tanggal, tanggal);
        database.insert(DatabaseHelper.TABLE_NAME2, null, contentValue);
    }
    public Cursor fetch() {
        String[] columns = new String[] {
                DatabaseHelper._ID,DatabaseHelper.Nama, DatabaseHelper.Email, DatabaseHelper.Nohp,DatabaseHelper.Jmlhruangan,DatabaseHelper.Tanggal
        };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME2, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public int update(long _id, String nama, String email, String nohp , String jumlahruangan, String tanggal) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.Nama, nama);
        contentValues.put(DatabaseHelper.Email, email);
        contentValues.put(DatabaseHelper.Nohp, nohp);
        contentValues.put(DatabaseHelper.Jmlhruangan, jumlahruangan);
        contentValues.put(DatabaseHelper.Tanggal, tanggal);
        int i = database.update(DatabaseHelper.TABLE_NAME2, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME2, DatabaseHelper._ID + "=" + _id, null);
    }
}
