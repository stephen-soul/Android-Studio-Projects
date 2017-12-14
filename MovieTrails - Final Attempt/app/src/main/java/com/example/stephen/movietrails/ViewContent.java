package com.example.stephen.movietrails;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class ViewContent extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    DatabaseHelper mDatabaseHelper;

    ImageButton backButton;
    TextView name;
    TextView description;
    YouTubePlayerView video;
    RatingBar rating;
    ImageButton editButton;
    ImageButton deleteButton;

    String selectedUrl;
    String fixedUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabaseHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_view_content);
        Bundle recievedContent = getIntent().getExtras();
        final int selectedID = recievedContent.getInt("id", -1);
        final String selectedName = recievedContent.getString("name");
        final String selectedDescription = recievedContent.getString("description");
        final Float selectedRating = recievedContent.getFloat("rating");
        final String selectedThumbnail = recievedContent.getString("thumbnail");
        selectedUrl = recievedContent.getString("url");
        fixedUrl = selectedUrl.replace("https://youtu.be/","");

        backButton = findViewById(R.id.backButton);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        video = findViewById(R.id.video);
        video.initialize(DeveloperKey.DEVELOPER_KEY, this);
        rating = findViewById(R.id.rating);
        editButton = findViewById(R.id.editButton);
        deleteButton = findViewById(R.id.deleteButton);

        // On click for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewContent.this, ListDataActivity.class);
                startActivity(intent);
                finish();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToEdit = new Intent(ViewContent.this, EditEntry.class);
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

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteFromTable(selectedID);
                toastMessage("Successfully deleted!");
                Intent afterDelete = new Intent(ViewContent.this, MainActivity.class);
                startActivity(afterDelete);
                finish();
            }
        });

        // On create set the values of the text views, button and rating
        name.setText(selectedName);
        description.setText(selectedDescription);
        rating.setRating(selectedRating);


    }

    // Toast for errors
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) player.cueVideo(fixedUrl); // your video to play
    }
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider arg0,
                                        YouTubeInitializationResult arg1){
    }
}
