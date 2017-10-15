package com.example.martin.ciscofullapp.Database;
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

public class DataBaseAllRepo  {

    private DatabaseTable table;

    public DataBaseAllRepo(){

        table= new DatabaseTable();

    }


    public static String createTable(){
        return "CREATE TABLE " + DatabaseTableAll.tableAll  + "("
                + DatabaseTableAll.KEY_IdAll  + " INTEGER PRIMARY KEY,"
                + DatabaseTableAll.KEY_Percent + " TEXT NOT NULL, "
                + DatabaseTableAll.KEY_TimeAll + " TEXT NOT NULL, "
                + DatabaseTableAll.KEY_DateAll + " TEXT NOT NULL)";

    }


    public void insert(DatabaseTableAll tables) {
        //int tableId;
        DatabaseManager databaseManager = new DatabaseManager();

        SQLiteDatabase db = databaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseTableAll.KEY_IdAll, tables.getIdAll());
        values.put(DatabaseTableAll.KEY_Percent, tables.getPercent());
        values.put(DatabaseTableAll.KEY_TimeAll, tables.getTimeAll());
        values.put(DatabaseTableAll.KEY_DateAll, tables.getDate());

        // Inserting Row
        db.insert(DatabaseTableAll.tableAll,null, values);
        DatabaseManager.getInstance().closeDatabase();

        //return tableId;
    }



    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(DatabaseTableAll.tableAll,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }



    public List<portDataAllList> getPortsList(){
        portDataAllList portDataAllList = new portDataAllList();
        List<portDataAllList> portDataAllLists = new ArrayList<portDataAllList>();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery =  " SELECT TABLEALL." + DatabaseTableAll.KEY_IdAll
                + ", TABLEALL." + DatabaseTableAll.KEY_Percent
                + ", TABLEALL." + DatabaseTableAll.KEY_DateAll
                + ", TABLEALL." + DatabaseTableAll.KEY_TimeAll
                + " FROM " + DatabaseTableAll.tableAll
                ;

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                portDataAllList= new portDataAllList();
                portDataAllList.setIdAll(cursor.getString(cursor.getColumnIndex(DatabaseTableAll.KEY_IdAll)));
                portDataAllList.setPercent(cursor.getDouble(cursor.getColumnIndex(DatabaseTableAll.KEY_Percent)));
                portDataAllList.setDate(cursor.getString(cursor.getColumnIndex(DatabaseTableAll.KEY_DateAll)));
                portDataAllList.setTimeAll(cursor.getString(cursor.getColumnIndex(DatabaseTableAll.KEY_TimeAll)));
                portDataAllLists.add(portDataAllList);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return portDataAllLists;

    }




}
