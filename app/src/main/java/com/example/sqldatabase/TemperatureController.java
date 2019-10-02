package com.example.sqldatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TemperatureController extends Database {
    private Context context;

    public TemperatureController(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public boolean create(TemperatureModel model) {

        ContentValues values = new ContentValues();

        values.put("TEMPERATURE", model.temp);
        values.put("LOCATION",model.location);

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("TEMPER", null, values) > 0;
        db.close();

        return createSuccessful;


    }

    public int count() {

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM TEMPER";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;

    }
    public List<TemperatureModel> read() {

        List<TemperatureModel> recordsList = new ArrayList<TemperatureModel>();

        String sql = "SELECT * FROM TEMPER ORDER BY TEMPERATURE DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {

                String location = cursor.getString(cursor.getColumnIndex("LOCATION"));
                String temp = cursor.getString(cursor.getColumnIndex("TEMPERATURE"));

                TemperatureModel objectStudent = new TemperatureModel();
                objectStudent.temp = temp;
                objectStudent.location = location;

                recordsList.add(objectStudent);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recordsList;
    }
}
