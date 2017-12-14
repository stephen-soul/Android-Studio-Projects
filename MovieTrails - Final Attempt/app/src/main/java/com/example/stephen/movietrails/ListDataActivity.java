package com.example.stephen.movietrails;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * This (hopefully) will read everything in the db and list it
 **/

public class ListDataActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";

    DatabaseHelper mDatabaseHelper;

    private ListView mListView;

    ImageButton backButton;

    Float itemRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        mListView = findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);
        backButton = findViewById(R.id.backButton);
        populateListView();

        // On click for the button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListDataActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    } // End of on create

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the list view...");

        // Get data and append to a list
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()) {
            listData.add(data.getString(1));
        }
        // Create the list adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

        // Set an on click listener for the list view
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                String itemDescription = "";
                String itemThumbnail = "";
                String itemUrl = "";
                Log.d(TAG, "onItemClick: You clicked on " + name);
                Cursor data = mDatabaseHelper.getItemID(name);
                int itemID = -1;
                while(data.moveToNext()) {
                    itemID = data.getInt(0);
                    itemDescription = data.getString(2);
                    itemRating = data.getFloat(3);
                    itemThumbnail = data.getString(4);
                    itemUrl = data.getString(5);
                }
                data.close();
                // Pool the rest of the values into their own variables
                if(itemID > -1) {
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent viewIntent = new Intent(ListDataActivity.this, ViewContent.class);
                    viewIntent.putExtra("id", itemID);
                    viewIntent.putExtra("name", name);
                    viewIntent.putExtra("description", itemDescription);
                    viewIntent.putExtra("rating", itemRating);
                    viewIntent.putExtra("thumbnail", itemThumbnail);
                    viewIntent.putExtra("url", itemUrl);
                    startActivity(viewIntent);
                    finish();
                } else {
                    Log.d(TAG, "onItemClick: No ID is associated with that name.");
                }
            }
        });
    }
}
