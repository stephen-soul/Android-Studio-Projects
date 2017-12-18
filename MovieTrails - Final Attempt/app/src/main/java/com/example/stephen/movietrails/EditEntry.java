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

public class EditEntry extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;

    ImageButton backButton;
    Button confirmEdit;
    EditText title;
    EditText description;
    EditText url;
    RatingBar ratingBar;

    String newTitle;
    String newDescription;
    String filteredDescription;
    float newRating;
    String newUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);

        Bundle recievedContent = getIntent().getExtras();
        final int selectedID = recievedContent.getInt("id", -1);
        final String selectedName = recievedContent.getString("name");
        final String selectedDescription = recievedContent.getString("description");
        final float selectedRating = recievedContent.getFloat("rating");
        final String selectedThumbnail = recievedContent.getString("thumbnail");
        final String selectedUrl = recievedContent.getString("url");

        mDatabaseHelper = new DatabaseHelper(this);

        backButton = findViewById(R.id.backButton);
        confirmEdit = findViewById(R.id.confirmEdit);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        url = findViewById(R.id.url);
        ratingBar = findViewById(R.id.ratingBar);

        title.setText(selectedName);
        description.setText(selectedDescription);
        url.setText(selectedUrl);
        ratingBar.setRating(selectedRating);

        // On click for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToEdit = new Intent(EditEntry.this, ViewContent.class);
                intentToEdit.putExtra("id", selectedID);
                intentToEdit.putExtra("name", selectedName);
                intentToEdit.putExtra("description", selectedDescription);
                intentToEdit.putExtra("rating", selectedRating);
                intentToEdit.putExtra("thumbnail", selectedThumbnail);
                intentToEdit.putExtra("url", selectedUrl);
                startActivity(intentToEdit);
                finish();
            }
        });

        // On submit, validate and update
        confirmEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(title.getText().toString().equals(""))
                    toastMessage("You must enter a title");
                else if(description.getText().toString().equals(""))
                    toastMessage("You must enter a description");
                else if(url.getText().toString().equals(""))
                    toastMessage("You must enter a trailer url");
                else {
                    newTitle = title.getText().toString();
                    newDescription = description.getText().toString();
                    filteredDescription = newDescription.replace("'","ll");
                    newRating = ratingBar.getRating();
                    newUrl = url.getText().toString();
                    mDatabaseHelper.updateInformation(selectedID, newTitle, filteredDescription, newRating, newUrl);
                    toastMessage("Successfully edited the movie trailer");
                    Intent passEdits = new Intent(EditEntry.this, ViewContent.class);
                    passEdits.putExtra("id", selectedID);
                    passEdits.putExtra("name", newTitle);
                    passEdits.putExtra("description", newDescription);
                    passEdits.putExtra("rating", newRating);
                    passEdits.putExtra("thumbnail", "");
                    passEdits.putExtra("url", newUrl);
                    startActivity(passEdits);
                    finish();
                }
            }
        });
    }

    // Toast for errors
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
