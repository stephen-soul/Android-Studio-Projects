package com.example.schoolcalendertwentyeighteen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 *  Android School Calendar 2018
 *  ----------------------------
 *  Starting my last semester of
 *  college in a few weeks. I'm
 *  doing this as a way to remember
 *  my classes I'll have. And an
 *  excuse to play around with
 *  Android Studio.
 *
 *  The goal of this main activity is to display the day and the classes of the day. It'll check
 *  and display only the current days activities, but the user can select with a hamburger menu
 *  Other days, and the option of making notes for when things are due and such.
 */

public class MainActivity extends AppCompatActivity {

    // Get the XML elements
    TextView weekday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On create initialize the weekday TextView
        weekday = findViewById(R.id.weekday);

        // Find out what day it is
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.CANADA);
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);

        // Set the day of the week to the TextView
        weekday.setText(dayOfTheWeek);
    }
}
