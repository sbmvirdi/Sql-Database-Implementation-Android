package com.example.sqldatabase;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private static final String DB_NAME = "temperatures.db";
    private static final String TABLE_NAME="TEMPER";
    private static final int DB_VERSION = 1;
    private static final String COLUMN_NAME1 ="TEMPERATURE";
    private static final String COLUMN_NAME2 = "LOCATION";
    private static final String COLUMN_ID ="_ID";
    private static final String QUERY ="CREATE TABLE "+ TABLE_NAME+"("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COLUMN_NAME1+" REAL,"+COLUMN_NAME2+" REAL);";

    public Database(@Nullable Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    String sql = "DROP TABLE IF EXISTS "+TABLE_NAME;
    db.execSQL(sql);
    onCreate(db);
    }
}
