package com.sb.splashactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class OdometerActivity extends AppCompatActivity {

    private EditText txt_Date;
    private TextView lb_dateTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odometer);

        lb_dateTime = findViewById(R.id.lb_dateTime);

    }


}