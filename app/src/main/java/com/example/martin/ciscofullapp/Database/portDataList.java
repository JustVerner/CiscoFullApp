package com.example.martin.ciscofullapp.Database;

/**
 * Created by Martin on 04-09-2017.
 */

import com.example.martin.ciscofullapp.Database.portDataList;
import com.example.martin.ciscofullapp.Database.DatabaseManager;
import com.example.martin.ciscofullapp.Database.DatabaseTable;
import com.example.martin.ciscofullapp.Database.DatabaseTableRepo;
import com.example.martin.ciscofullapp.Database.DatabaseHelper;
import com.example.martin.ciscofullapp.Database.InsertData;

public class portDataList {

    private String id;
    private String name;
    private Integer used;
    private Integer nUsed;
    private Integer percent;
    private String date;
    private String time;
    private Integer acces;
    private Integer trunk;

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    public Integer getnUsed() {
        return nUsed;
    }

    public void setnUsed(Integer nUsed) {
        this.nUsed = nUsed;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getAcces() {
        return acces;
    }

    public void setAcces(Integer acces) {
        this.acces = acces;
    }

    public Integer getTrunk() {
        return trunk;
    }

    public void setTrunk(Integer trunk) {
        this.trunk = trunk;
    }
}
