package com.example.sqldatabase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OnClickTemperatureListener  implements View.OnClickListener {


    @Override
    public void onClick(View view) {
        final Context context = view.getRootView().getContext();


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.temp_input_form, null, false);


        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Measure Temperature")
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                EditText editTextTemp = formElementsView.findViewById(R.id.editTextTemperature);
                                EditText editTextLocation = formElementsView.findViewById(R.id.editTextLocation);
                                String tempData = editTextTemp.getText().toString();
                                String locData = editTextLocation.getText().toString();
                                TemperatureModel t = new TemperatureModel();
                                t.temp = tempData;
                                t.location = locData;

                                boolean createSuccessful = new TemperatureController(context).create(t);

                                if (createSuccessful){
                                    Toast.makeText(context, "Data Written Successfully!", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(context, "failed to save data!", Toast.LENGTH_SHORT).show();
                                }

//                                int count  =  new TemperatureController(context).count();
//                                TextView tt = formElementsView.findViewById(R.id.recordcount);
//                                tt.setText(count + " records found.");
//                                dialog.cancel();
                            }

                        }).show();





    }

}
