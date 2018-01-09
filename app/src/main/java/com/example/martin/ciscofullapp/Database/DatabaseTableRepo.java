package com.example.martin.ciscofullapp.Database;

/**
 * Created by Martin on 28-08-2017.
 */

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.martin.ciscofullapp.Database.DatabaseTable;
import com.example.martin.ciscofullapp.Database.DatabaseManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class DatabaseTableRepo  {

    private DatabaseTable table;

    public DatabaseTableRepo(){

        table= new DatabaseTable();

    }


    public static String createTable(){
        return "CREATE TABLE " + DatabaseTable.TABLE  + "("
                + DatabaseTable.KEY_Id  + " INTEGER PRIMARY KEY,"
                + DatabaseTable.KEY_Name + " TEXT NOT NULL, "
                + DatabaseTable.KEY_USED + " INTEGER NOT NULL, "
                + DatabaseTable.KEY_NUSED + " INTEGER NOT NULL, "
                + DatabaseTable.KEY_Percent + " INTEGER NOT NULL, "
                + DatabaseTable.KEY_DATE + " TEXT NOT NULL, "
                + DatabaseTable.Key_TIME + " TEXT NOT NULL, "
                + DatabaseTable.KEY_ACCES + " INTEGER NOT NULL, "
                + DatabaseTable.KEY_TRUNK + " INTEGER NOT NULL)";

    }


    public void insert(DatabaseTable table) {
        //int tableId;
        DatabaseManager databaseManager = new DatabaseManager();

        SQLiteDatabase db = databaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseTable.KEY_Id, table.getId());
        values.put(DatabaseTable.KEY_Name, table.getName());
        values.put(DatabaseTable.KEY_USED, table.getUsed());
        values.put(DatabaseTable.KEY_NUSED, table.getnUsed());
        values.put(DatabaseTable.KEY_Percent, table.getPercent());
        values.put(DatabaseTable.KEY_DATE, table.getDate());
        values.put(DatabaseTable.Key_TIME, table.getTime());
        values.put(DatabaseTable.KEY_TRUNK, table.getTrunk());
        values.put(DatabaseTable.KEY_ACCES, table.getAcces());


        // Inserting Row
        db.insert(DatabaseTable.TABLE,null, values);
        DatabaseManager.getInstance().closeDatabase();

    }



    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(DatabaseTable.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }



    public List<portDataList> getPortsList(){
        portDataList portdataList = new portDataList();
        List<portDataList> portDataLists = new ArrayList<portDataList>();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery =  " SELECT Database." + DatabaseTable.KEY_Id
                + ", Database." + DatabaseTable.KEY_Name
                + ", Database." + DatabaseTable.KEY_USED
                + ", Database." + DatabaseTable.KEY_NUSED
                + ", Database." + DatabaseTable.KEY_Percent
                + ", Database." + DatabaseTable.KEY_DATE
                + ", Database." + DatabaseTable.Key_TIME
                + ", Database." + DatabaseTable.KEY_ACCES
                + ", Database." + DatabaseTable.KEY_TRUNK
                + " FROM " + DatabaseTable.TABLE
                ;

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                portdataList= new portDataList();
                portdataList.setid(cursor.getString(cursor.getColumnIndex(DatabaseTable.KEY_Id)));
                portdataList.setName(cursor.getString(cursor.getColumnIndex(DatabaseTable.KEY_Name)));
                portdataList.setUsed(cursor.getInt(cursor.getColumnIndex(DatabaseTable.KEY_USED)));
                portdataList.setnUsed(cursor.getInt(cursor.getColumnIndex(DatabaseTable.KEY_NUSED)));
                portdataList.setPercent(cursor.getInt(cursor.getColumnIndex(DatabaseTable.KEY_Percent)));
                portdataList.setDate(cursor.getString(cursor.getColumnIndex(DatabaseTable.KEY_DATE)));
                portdataList.setTime(cursor.getString(cursor.getColumnIndex(DatabaseTable.Key_TIME)));
                portdataList.setAcces(cursor.getInt(cursor.getColumnIndex(DatabaseTable.KEY_ACCES)));
                portdataList.setTrunk(cursor.getInt(cursor.getColumnIndex(DatabaseTable.KEY_TRUNK)));

                portDataLists.add(portdataList);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return portDataLists;

    }




}
