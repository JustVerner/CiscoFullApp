package com.example.martin.ciscofullapp.app;
/*
/**
 * Created by Martin on 28-08-2017.
 */

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.example.martin.ciscofullapp.Database.DatabaseHelper;
import com.example.martin.ciscofullapp.Database.DatabaseManager;


public class  App extends Application {
    private static Context context;
    private static DatabaseHelper dbHelper;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = this.getApplicationContext();
        dbHelper = new DatabaseHelper();
        DatabaseManager.initializeInstance(dbHelper);

    }


    public static Context getContext(){
        return context;
    }

}
