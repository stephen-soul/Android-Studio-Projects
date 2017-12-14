package com.example.stephen.movietrails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 *  This main activity hosts the portal to the "view database" and "edit database" activities
 **/

public class MainActivity extends AppCompatActivity {

    // Make the buttons known
    Button option1;
    Button option2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the views by ID
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);

        // Set on click listeners for the buttons to go to the desired view
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);
                finish();
            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddingEntries.class);
                startActivity(intent);
                finish();
            }
        });
    } // End of on create
} // End of main activity
