package com.example.stephen.movietrails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

public class AddingEntries extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;

    ImageButton backButton;
    Button submit;
    EditText title;
    EditText description;
    EditText url;
    RatingBar rating;

    String submittedTitle;
    String submittedDescription;
    Float submittedRating;
    String submittedUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_entries);

        mDatabaseHelper = new DatabaseHelper(this);

        backButton = findViewById(R.id.backButton);
        submit = findViewById(R.id.submit);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        url = findViewById(R.id.url);
        rating = findViewById(R.id.rating);

        if (!mDatabaseHelper.checkForTables()) {
            backButton.setVisibility(View.INVISIBLE);
        } else {
            backButton.setVisibility(View.VISIBLE);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(title.getText().toString().equals(""))
                    toastMessage("You must enter a title");
                else if(description.getText().toString().equals(""))
                    toastMessage("You must enter a description");
                else if(url.getText().toString().equals(""))
                    toastMessage("You must enter a trailer url");
                else {
                    submittedTitle = title.getText().toString();
                    submittedDescription = description.getText().toString();
                    submittedRating = rating.getRating();
                    submittedUrl = url.getText().toString();
                    addToDB(submittedTitle, submittedDescription, submittedRating, "", submittedUrl);
                    Intent intent = new Intent(AddingEntries.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public void addToDB(String title, String description, double rating, String thumbnail, String url) {
        boolean added = mDatabaseHelper.addData(title, description, rating, "", url);
        if(added)
            toastMessage("Added Successfully!");
        else
            toastMessage("Something went wrong.");
    }

    // Toast for errors
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
}
