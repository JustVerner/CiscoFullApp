package com.example.martin.ciscofullapp.Database;

/**
 * Created by Martin on 28-08-2017.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.martin.ciscofullapp.app.App;
import com.example.martin.ciscofullapp.Database.DatabaseTableRepo;
import com.example.martin.ciscofullapp.Database.DatabaseManager;
import com.example.martin.ciscofullapp.Database.DatabaseTable;
import com.example.martin.ciscofullapp.Database.DatabaseManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class DatabaseHelper  extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION =64;
    private static final String LOG = "DatabaseHelper";
    private static final String DATABASE_NAME = "Database_name.db";
    private static final String TAG = DatabaseHelper.class.getSimpleName().toString();
    private SQLiteOpenHelper _helper;
    public ArrayList getDevice;

    public DatabaseHelper( ) {
        super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here
        db.execSQL(DatabaseTableRepo.createTable ());
        db.execSQL(DataBaseAllRepo.createTable ());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d)", oldVersion, newVersion));

        // Drop table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseTable.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseTableAll.tableAll);
        onCreate(db);
    }
    /*public Cursor getPerson(int id) {
        DatabaseManager databaseHelper = new DatabaseManager();

        SQLiteDatabase db = openOrCreateDatabase("Database_name.db", android.content.Context.MODE_PRIVATE, null);
        //SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT " + DatabaseTable.TABLE + " FROM " +
                DatabaseTable.KEY_Name + " WHERE " + "komm_wlc ", new String[] { Integer.toString(id) } );
        return res;*/


    public String[] getContacts(){
        Cursor cursor = getReadableDatabase().rawQuery("SELECT Name FROM DATATABLE", null);
        cursor.moveToFirst();
        ArrayList<String> names = new ArrayList<String>();
        while(!cursor.isAfterLast()) {
            names.add(cursor.getString(cursor.getColumnIndex("Name")));
            cursor.moveToNext();
        }
        cursor.close();
        return names.toArray(new String[names.size()]);
    }

    public Integer[] getUpPorts(){
        Cursor cursor = getReadableDatabase().rawQuery("SELECT Used FROM DATATABLE", null);
        cursor.moveToFirst();
        ArrayList<Integer> upPorts = new ArrayList<Integer>();
        while(!cursor.isAfterLast()) {
            upPorts.add(cursor.getInt(cursor.getColumnIndex("Used")));
            cursor.moveToNext();
        }
        cursor.close();
        return upPorts.toArray(new Integer[upPorts.size()]);
    }

    public Integer[] getDownPorts(){
        Cursor cursor = getReadableDatabase().rawQuery("SELECT NUsed FROM DATATABLE", null);
        cursor.moveToFirst();
        ArrayList<Integer> downPorts = new ArrayList<Integer>();
        while(!cursor.isAfterLast()) {
            downPorts.add(cursor.getInt(cursor.getColumnIndex("NUsed")));
            cursor.moveToNext();
        }
        cursor.close();
        return downPorts.toArray(new Integer[downPorts.size()]);
    }

    public Integer[] getPercent(){
        Cursor cursor = getReadableDatabase().rawQuery("SELECT Percent FROM DATATABLE", null);
        cursor.moveToFirst();
        ArrayList<Integer> percent = new ArrayList<Integer>();
        while(!cursor.isAfterLast()) {
            percent.add(cursor.getInt(cursor.getColumnIndex("Percent")));
            cursor.moveToNext();
        }
        cursor.close();
        return percent.toArray(new Integer[percent.size()]);
    }

    public String[] getDate(){
        Cursor cursor = getReadableDatabase().rawQuery("SELECT Date FROM DATATABLE", null);
        cursor.moveToFirst();
        ArrayList<String> date = new ArrayList<String>();
        while(!cursor.isAfterLast()) {
            date.add(cursor.getString(cursor.getColumnIndex("Date")));
            cursor.moveToNext();
        }
        cursor.close();
        return date.toArray(new String[date.size()]);
    }

    public Double[] getPercentAll(){
        Cursor cursor = getReadableDatabase().rawQuery("SELECT Percent FROM TABLEALL", null);
        cursor.moveToFirst();
        ArrayList<Double> percentAll = new ArrayList<Double>();
        while(!cursor.isAfterLast()) {
            percentAll.add(cursor.getDouble(cursor.getColumnIndex("Percent")));
            cursor.moveToNext();
        }
        cursor.close();
        return percentAll.toArray(new Double[percentAll.size()]);
    }

    public String[] getDateAll(){
        Cursor cursor = getReadableDatabase().rawQuery("SELECT Date FROM TABLEALL", null);
        cursor.moveToFirst();
        ArrayList<String> dateAll = new ArrayList<String>();
        while(!cursor.isAfterLast()) {
            dateAll.add(cursor.getString(cursor.getColumnIndex("Date")));
            cursor.moveToNext();
        }
        cursor.close();
        return dateAll.toArray(new String[dateAll.size()]);
    }

    public String[] getTime(){
        Cursor cursor = getReadableDatabase().rawQuery("SELECT Time FROM DATATABLE", null);
        cursor.moveToFirst();
        ArrayList<String> time = new ArrayList<String>();
        while(!cursor.isAfterLast()) {
            time.add(cursor.getString(cursor.getColumnIndex("Time")));
            cursor.moveToNext();
        }
        cursor.close();
        return time.toArray(new String[time.size()]);
    }

    public String[] getTimeAll(){
        Cursor cursor = getReadableDatabase().rawQuery("SELECT TimeAll FROM TABLEALL", null);
        cursor.moveToFirst();
        ArrayList<String> timeAll = new ArrayList<String>();
        while(!cursor.isAfterLast()) {
            timeAll.add(cursor.getString(cursor.getColumnIndex("TimeAll")));
            cursor.moveToNext();
        }
        cursor.close();
        return timeAll.toArray(new String[timeAll.size()]);
    }



    /*
    public Cursor getAllPersons() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + PERSON_TABLE_NAME, null );
        return res;
    }
*/



}