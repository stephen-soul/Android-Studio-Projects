package com.example.stephen.movietrails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 *  This is a splash screen to give the name of the program before jumping into it.
 *  -------------------------------------------------------------------------------
 *  STEPS:
 *      1. Call the on create method and make a new thread.
 *      2. In said thread, try to sleep for 3000 milliseconds.
 *      3. The sleep pauses the "Splash Screen." After the pause,
 *      4. Start a new intent to take us to the next part and finish,
 *      catching any errors.
 *      5. Start the thread and move on from there.
 *  --------------------------------------------------------------------------------
 **/

public class SplashScreen extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    // On create, run the splash screen for 3000 milliseconds, then go to main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mDatabaseHelper = new DatabaseHelper(this);
        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    if (mDatabaseHelper.checkForTables()) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(getApplicationContext(), AddingEntries.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }// End of onCreate
}
