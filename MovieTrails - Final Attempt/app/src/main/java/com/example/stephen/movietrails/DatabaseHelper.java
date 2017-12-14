package com.example.stephen.movietrails;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Declare TAG and database stuff
    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "movies";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "description";
    private static final String COL4 = "rating";
    private static final String COL5 = "thumbnail";
    private static final String COL6 = "url";

    // Because I somehow keep losing it, THIS is where the version number is
    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 7);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // On create, make the table
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT, " + COL3 + " TEXT, " + COL4 +
                " DOUBLE, " + COL5 + " TEXT, " + COL6 + " TEXT)";
        sqLiteDatabase.execSQL(createTable);
    } // End of on create

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Execute sql statement to drop if it exists
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    } // End of on upgrade

    public boolean addData(String name, String description, double rating, String thumbnail, String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, description);
        contentValues.put(COL4, rating);
        contentValues.put(COL5, thumbnail);
        contentValues.put(COL6, url);
        Log.d(TAG, "addData: Adding " + name + ", " + description + ", " + rating + ", " + thumbnail + ", " + url + " to " + TABLE_NAME);
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        if(result == -1) {
            return false;
        } else {
            return true;
        }
    } // End of add

    // Make a function to return all data from the database
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemID(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + ", " + COL2 + ", " + COL3 + ", " + COL4 + ", " + COL5 + ", " + COL6 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateInformation(int id, String newTitle, String newDescription, Float newRating, String newUrl) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newTitle + "', " + COL3 + " = '" + newDescription +
                "', " + COL4 + " = '" + newRating + "', " + COL5 + " = '', " +
                COL6 + " = '" + newUrl + "' WHERE " + COL1 + " = '" + id + "'";
        Log.d(TAG, "updateName: query: " + query);
        db.execSQL(query);
    }

    public void deleteFromTable(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " +
                COL1 + " = '" + id + "'";
        Log.d(TAG, "deleteFromTable: query " + query);
        db.execSQL(query);
    }

    // Check to see if data exists
    public boolean checkForTables(){
        SQLiteDatabase db;
        boolean hasTables = false;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if(cursor != null && cursor.getCount() > 0){
            hasTables=true;
            cursor.close();
        }

        return hasTables;
    }
}
