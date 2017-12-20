package com.example.martin.ciscofullapp.Database;

import android.support.v7.app.AppCompatActivity;

import com.example.martin.ciscofullapp.Database.InsertData;
import com.example.martin.ciscofullapp.Database.DatabaseHelper;
import com.example.martin.ciscofullapp.Database.DatabaseManager;

import java.util.Date;

/**
 * Created by Martin on 28-08-2017.
 */

public class DatabaseTable {

    public static final String TAG = DatabaseTable.class.getSimpleName();
    public static final String TABLE = "Database";
    // Labels Table Columns names
    public static final String KEY_Id = "ID";
    public static final String KEY_Name = "Name";
    public static final String KEY_USED = "Used";
    public static final String KEY_NUSED = "NUsed";
    public static final String KEY_Percent = "Percent";
    public static final String KEY_DATE = "Date";
    public static final String Key_TIME = "Time";
    public static final String KEY_TRUNK = "Trunk";
    public static final String KEY_ACCES = "Acces";


    private String id;
    private String name;
    private Integer used;
    private Integer nUsed;
    private Integer percent;
    private String date;
    private String time;
    private Integer trunk;
    private Integer acces;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUsed() {return used;}

    public void setUsed(Integer used) { this.used = used;}

    public Integer getnUsed() {return nUsed;}

    public void setnUsed(Integer nUsed) { this.nUsed = nUsed;}

    public Integer getPercent() {return percent;}

    public void setPercent(Integer percent) {this.percent = percent;}

    public String getDate() {return date;}

    public void setDate(String date) { this.date = date;}

    public String getTime() {return time;}

    public void setTime(String time) { this.time = time;}

    public Integer getTrunk() {return trunk;}

    public void setTrunk(Integer trunk) {this.trunk = trunk;}

    public Integer getAcces() {return acces;}

    public void setAcces(Integer acces) {this.acces = acces;}






}
