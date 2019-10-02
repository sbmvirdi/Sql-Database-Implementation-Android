package com.example.sqldatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert = findViewById(R.id.insert);
        insert.setOnClickListener(new OnClickTemperatureListener());



        readRecords();
        countRecords();

}


     public void countRecords() {
        int recordCount = new TemperatureController(this).count();


        TextView textViewRecordCount = findViewById(R.id.recordcount);
        textViewRecordCount.setText(recordCount + " records found.");
    }


    public void readRecords() {

        LinearLayout linearLayoutRecords = findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();

        List<TemperatureModel> students = new TemperatureController(this).read();

        if (students.size() > 0) {

            for (TemperatureModel obj : students) {

                String temp = obj.location;
                String loc = obj.temp;

                String textViewContents = "Location::"+loc+"    -    "+"Temperature::"+temp;

                TextView textViewStudentItem= new TextView(this);
                textViewStudentItem.setPadding(0, 10, 0, 10);
                textViewStudentItem.setText(textViewContents);
                //textViewStudentItem.setTag(Integer.toString(id));

                linearLayoutRecords.addView(textViewStudentItem);
            }

        }

        else {

            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("No records yet.");

            linearLayoutRecords.addView(locationItem);
        }

    }
}
