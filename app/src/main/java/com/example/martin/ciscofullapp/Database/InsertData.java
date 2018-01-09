package com.example.martin.ciscofullapp.Database;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.martin.ciscofullapp.R;
import com.example.martin.ciscofullapp.Spark.SparkPost;
import com.example.martin.ciscofullapp.app.App;
import com.example.martin.ciscofullapp.Database.portDataList;
import com.example.martin.ciscofullapp.Database.DatabaseManager;
import com.example.martin.ciscofullapp.Database.DatabaseTable;
import com.example.martin.ciscofullapp.Database.DatabaseTableRepo;
import com.example.martin.ciscofullapp.Database.DatabaseHelper;
import com.example.martin.ciscofullapp.Database.InsertData;
import com.example.martin.ciscofullapp.getPorts.PortsGet;
import com.example.martin.ciscofullapp.getPorts.MainActivity;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.R.attr.button;
import static android.R.attr.port;
import static com.example.martin.ciscofullapp.VisualRepresentations.FragmentOne.spinnerArrayAdapter;
import static com.example.martin.ciscofullapp.VisualRepresentations.FragmentOne.spinnerArrayAdapterDate;


/**
 * Created by Martin on 28-08-2017.
 */

public class InsertData {
    private String[] networksArray;
    private Integer[] upPorts;
    private Integer[] downPorts;
    private Integer[] percent;
    private Double[] percentAll;
    private Integer[] acces;
    private Integer[] trunk;
    private Date date = new Date();
    private SimpleDateFormat df = new SimpleDateFormat("dd MMM yy");
    private SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
    public static final String TAG = InsertData.class.getSimpleName();

    public void insertData()

    {

        date.setTime(System.currentTimeMillis());


        DataBaseAllRepo dataBaseAllRepo = new DataBaseAllRepo();
        DatabaseTableRepo DataTable = new DatabaseTableRepo();

        //DataTable.delete();

        DatabaseTable table = new DatabaseTable();
        DatabaseTableAll databaseTableAll = new DatabaseTableAll();

        //PortsGet portsGet = new PortsGet();

        ArrayList<String> networkNames = PortsGet.myNetworks();
        ArrayList<Integer> upPortsRet = PortsGet.upPortReturns();
        ArrayList<Integer> downPortsRet = PortsGet.downPortReturn();
        ArrayList<Integer> percentRet = PortsGet.percentReturn();
        ArrayList<Double> percentAllret = PortsGet.percentAllReturn();
        ArrayList<Integer> accesArray = PortsGet.accesReturn();
        ArrayList<Integer> trunkArray = PortsGet.trunkReturn();


        networksArray = networkNames.toArray(new String[networkNames.size()]);
        upPorts = upPortsRet.toArray(new Integer[upPortsRet.size()]);
        downPorts = downPortsRet.toArray(new Integer[downPortsRet.size()]);
        percent = percentRet.toArray(new Integer[percentRet.size()]);
        percentAll = percentAllret.toArray(new Double[percentAllret.size()]);
        acces = accesArray.toArray(new Integer[accesArray.size()]);
        trunk = trunkArray.toArray(new Integer[accesArray.size()]);

        for (int i = 0; i < networksArray.length; i++) {

            table.setName(networksArray[i]);
            table.setUsed(upPorts[i]);
            table.setnUsed(downPorts[i]);
            table.setPercent(percent[i]);
            table.setDate(df.format(date));
            table.setTime(tf.format(date));
            table.setAcces(acces[i]);
            table.setTrunk(trunk[i]);
            DataTable.insert(table);

            /*if (percent[i]>80)
            {
                percentInt = percent[i];
                deviceString = networksArray[i];
                try {
                    sparkPost.sparkrun();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }*/
            if (i <= percentAll.length -1) {
                databaseTableAll.setPercent(percentAll[i]);
                databaseTableAll.setDate(df.format(date));
                databaseTableAll.setTimeAll(tf.format(date));
                dataBaseAllRepo.insert(databaseTableAll);
            }


            if (i == PortsGet.upPortReturns().size()-1)
            {
                //spinnerArrayAdapterDate.notifyDataSetChanged();
                //spinnerArrayAdapter.notifyDataSetChanged();
                networkNames.clear();
                upPortsRet.clear();
                downPortsRet.clear();
                percentRet.clear();
                percentAllret.clear();
                PortsGet.upPortReturns().clear();
                PortsGet.downPortReturn().clear();
                PortsGet.myNetworks().clear();
                PortsGet.percentReturn().clear();
                PortsGet.percentAllReturn().clear();
                PortsGet.percent.clear();
                PortsGet.finalNetwork.clear();
                PortsGet.finalUrl.clear();
                PortsGet.portUp.clear();
                PortsGet.portDown.clear();
                PortsGet.percentAll.clear();
                PortsGet.TrunkArray.clear();
                PortsGet.AccessArray.clear();

                listData();

            }
        }

    }

    private void listData() {

        DatabaseTableRepo databaseTableRepo = new DatabaseTableRepo();
        DataBaseAllRepo dataBaseAllRepo = new DataBaseAllRepo();
        List<portDataList> portdataList = databaseTableRepo.getPortsList();
        List<portDataAllList> portDataAllList = dataBaseAllRepo.getPortsList();


        Log.d(TAG, String.format("%-11s", "ID") +
                String.format("%-35s", "Device Name") +
                String.format("%-7s", "Used ports") +
                String.format("%-31s", "Note used ports") +
                String.format("%-15s", "Percent ports") +
                String.format("%-6s", "Date") +
                String.format("%-9s", "Time") +
                String.format("%-18s", "PercentAll") +
                String.format("%-9s", "DateAll") +
                String.format("%-9s", "TimeAll") +
                String.format("%-18s", "Acces") +
                String.format("%-9s", "Trunk")
        );

        Log.d(TAG, "=============================================================");
        for (int i = 0; i < portdataList.size(); i++) {
            Log.d(TAG, "0000000000".substring(portdataList.get(i).getid().length())
                    + portdataList.get(i).getid() +
                    " " + String.format("%-35s", portdataList.get(i).getName()) +
                    String.format("%-7s", portdataList.get(i).getUsed()) +
                    String.format("%-31s", portdataList.get(i).getnUsed()) +
                    String.format("%-31s", portdataList.get(i).getPercent()) +
                    String.format("%-12s", portdataList.get(i).getDate()) +
                    String.format("%-9s", portdataList.get(i).getTime()) +
                    String.format("%-18s", portdataList.get(i).getAcces()) +
                    String.format("%-9s", portdataList.get(i).getTrunk())



            );


        }

        for (int I = 0; I <portDataAllList.size(); I++)
        {
            Log.d(TAG, "0000000000".substring(portdataList.get(I).getid().length())
                    + portdataList.get(I).getid() +
                    " " + String.format("%-12s", portDataAllList.get(I).getPercent()) +
                    String.format("%-18s", portDataAllList.get(I).getDate()) +
                    String.format("%-9s", portDataAllList.get(I).getTimeAll())

            );
        }
        Log.d(TAG, "=============================================================");
    }
}