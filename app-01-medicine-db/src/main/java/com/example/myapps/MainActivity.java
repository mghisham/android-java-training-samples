package com.example.myapps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapps.constants.Constants;
import com.example.myapps.repository.DBHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mEdtMedicine;
    private EditText mEdtDate;
    private Spinner mSpnTime;
    private Button mBtnInsert;
    private int mYear, mMonth, mDay;
    private DBHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();
        setClickListener();
        setTimeSpinnerData();
        init();
    }

    private void initializeView() {
        mEdtMedicine = findViewById(R.id.edtMedicineName);
        mEdtDate = findViewById(R.id.edtDate);
        mSpnTime = findViewById(R.id.spnTimeOfTheDay);
        mBtnInsert = findViewById(R.id.btnInsert);

    }

    private void init() {
        mDbHelper = new DBHelper(this);
    }

    private void setClickListener() {
        mEdtDate.setOnClickListener(view -> showDatePicker());
        mBtnInsert.setOnClickListener(view -> insertDataIntoDb());
    }

    private void showDatePicker() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        mEdtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void setTimeSpinnerData() {
        final List<String> plantsList = new ArrayList<>(Arrays.asList(Constants.TIME_OF_THE_DAY_LIST));
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, plantsList);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_drop_down);
        mSpnTime.setAdapter(spinnerArrayAdapter);
    }

    private void insertDataIntoDb() {
        String medicineName = mEdtMedicine.getText().toString();
        String date = mEdtDate.getText().toString();
        int timeOfTheDa= mSpnTime.getSelectedItemPosition();
        boolean status = mDbHelper.insertMedicine(medicineName, date,timeOfTheDa);
        if(status) {
            setDefaultValues();
            Toast.makeText(getApplicationContext(), "Data updated Successfully",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Data updated Failed",Toast.LENGTH_LONG).show();
        }
        Toast.makeText(getApplicationContext(), "Number of rows " + mDbHelper.numberOfRows(),Toast.LENGTH_LONG).show();
    }

    private void setDefaultValues() {
        mEdtMedicine.getText().clear();
        mEdtDate.getText().clear();
        mSpnTime.setSelection(0);
    }
}