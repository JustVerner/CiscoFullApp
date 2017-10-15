package com.example.martin.ciscofullapp.getPorts;

/**
 * Created by Martin on 28-08-2017.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.martin.ciscofullapp.R;
import com.example.martin.ciscofullapp.getPorts.CertificateClient;
import com.example.martin.ciscofullapp.getPorts.MainActivity;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

import com.example.martin.ciscofullapp.Database.InsertData;

import com.example.martin.ciscofullapp.app.App;

import static com.example.martin.ciscofullapp.Database.Login.ipadress;
import static com.example.martin.ciscofullapp.R.id.portTextView;


public class PortsGet extends AppCompatActivity {

    public static String networks;
    public static String newResponse;
    public String portsGet;
    public String portFix;
    public int upPorts;
    public int downPorts;
    public String deviceName;
    public String deviceNameFix;
    public String ports;
    public String sentence;
    public int testus;
    public String hostname;
    public String urlGet;
    public String urlFix;
    public String urlReal;
    public String networkName;
    public String id;
    public String uptime;
    public String uptimeFix;
    public static Integer devices;
    public int testI = 0;
    public int compare;
    public int compare2;
    public int percentValue;
    public int percentValue2;
    public int percentValue3;
    public double percentup;
    public double percentdown;
    public double percentAlls;
    public double percentFinal;
    public static ArrayList<String> finalNetwork = new ArrayList<String>();
    public static ArrayList<String> finalUrl = new ArrayList<String>();
    public static ArrayList<Integer> portUp = new ArrayList<Integer>();
    public static ArrayList<Integer> portDown = new ArrayList<Integer>();
    public static ArrayList<Integer> percent = new ArrayList<Integer>();
    public static ArrayList<Double> percentAll = new ArrayList<Double>();
    public static ArrayList<String> date = new ArrayList<String>();
    public static ArrayList<String> upTime = new ArrayList<>();
    String[] urls;
    String[] upTimeArray;
    String[] finalNetworkArray;


    String networkURL;
    String networkdevicesURL = "https://"+ipadress+"/api/v1/network-device";

    InsertData insertData = new InsertData();

    String url = "https://10.100.1.125/api/v1/interface/network-device/" + networkURL;

    private Button portGet, portPls, buttonData, returnButton;
    private TextView portTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portpost);

        portGet = (Button) findViewById(R.id.portGet);
        portPls = (Button) findViewById(R.id.portpls);
        buttonData = (Button) findViewById(R.id.insertDatabase);
        returnButton = (Button) findViewById(R.id.returnButton);


        portTextView = (TextView) findViewById(R.id.portTextView);
        PortsGet portsGet = new PortsGet();

    }


    //Thread t = new Thread(){

        public void run()  {

            CertificateClient.getUnsafeOkHttpClient();

            Request request = new Request.Builder()
                    .url(networkdevicesURL)
                    .header("X-Auth-Token", MainActivity.requiredTicket)
                    .addHeader("content-type", "application/json; charset=utf-8")
                    .build();


            CertificateClient.getUnsafeOkHttpClient().newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    call.cancel();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {


                    final String myResponseNetwork = response.body().string();


                    PortsGet.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            networks = myResponseNetwork;

                            String[] networkSplit = networks.split("\\{");
                            //int x = 1;
                            for (int i = 1; i < networkSplit.length; i++) {
                                String[] networksplit2 = networkSplit[i].split("\\,");

                                for (int o = 0; o < networksplit2.length; o++) {

                                    if (i == networkSplit.length - 1 && o == networksplit2.length - 1) {
                                        try {
                                            runs();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if (networksplit2.length > 13 && networksplit2[o].substring(10, networksplit2[o].length() - 1).equals("Switches and Hubs")) {



                                for (int I = 0; I < networksplit2.length; I++) {


                                    if (networksplit2.length > 13 && networksplit2[I].substring(1, 3).equals("id") /*&& (!uptimeFix.equals("null"))*/) {
                                        urlGet = networksplit2[I];
                                        urlFix = urlGet.substring(6, 42);
                                        finalUrl.add(urlFix);
                                        urls = finalUrl.toArray(new String[finalUrl.size()]);
                                    }
                                    if (networksplit2.length > 13 && networksplit2[I].substring(1, 9).equals("hostname") /*&& (!uptimeFix.equals("null"))*/) {
                                        networkName = networksplit2[I];
                                        deviceNameFix = networkName.substring(12, networkName.length() - 1);
                                        finalNetwork.add(deviceNameFix);
                                        devices = finalNetwork.size();
                                    }

                                    /*if (networksplit2.length > 13 && (networksplit2[I].substring(1, 7).equals("upTime"))) {
                                        uptime = networksplit2[I];

                                        uptimeFix = uptime.substring(9, uptime.length());

                                    }*/



                                }
                            }
                        }

                            }

                        }
                    });
                }
            });
    //    }
    };

        public void runs() throws IOException {


            compare = finalUrl.size();

            upPorts = 0;
            downPorts = 0;
            percentAlls = 0;
            percentFinal = 0;

            String realURL = "https:/"+ipadress+"/api/v1/interface/network-device/" + urls[testI];

            CertificateClient.getUnsafeOkHttpClient();

            Request request = new Request.Builder()
                    .url(realURL)
                    .header("X-Auth-Token", MainActivity.requiredTicket)
                    .addHeader("content-type", "application/json; charset=utf-8")
                    .build();


            CertificateClient.getUnsafeOkHttpClient().newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    call.cancel();
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {


                    final String responses = response.body().string();
                    PortsGet.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            newResponse = responses;

                            String[] responseSplit = newResponse.split("\\{");

                            for (int I = 0; I < responseSplit.length; I++) {

                                compare2 = responseSplit.length;

                                String[] responeSplit2 = responseSplit[I].split("\\,");

                                for (int i = 0; i < responeSplit2.length; i++) {
                                    if (responeSplit2.length > 10 && responeSplit2[i].substring(responeSplit2[i].length() - 11, responeSplit2[i].length() - 1).equals("SwitchPort")) {
                                        for (int o = 0; o < responeSplit2.length; o++) {
                                            //if (responeSplit2.length > 10 && responeSplit2[o].substring(responeSplit2[o].length() -11, responeSplit2[o].length() -1).equals("SwitchPort")) {
                                            if (responeSplit2.length > 10 && responeSplit2[o].substring(1, 7).equals("status")) {

                                                portsGet = responeSplit2[o];
                                                portFix = portsGet.substring(10, portsGet.length() - 1);
                                                if (portFix.equals("up")) {
                                                    upPorts++;
                                                    percentup++;
                                                }
                                                if (portFix.equals("down")) {
                                                    downPorts++;
                                                    percentdown++;

                                                }
                                                /*if (compare2 - 1 == I) {
                                                    percentValue = upPorts + downPorts;
                                                    percentValue2 = (upPorts * 100) / (percentValue);
                                                    portUp.add(upPorts);
                                                    portDown.add(downPorts);
                                                    percent.add(percentValue2);


                                                    if (compare - 1 > testI) {
                                                        testI++;

                                                        try {
                                                            runs();
                                                        } catch (IOException e) {
                                                            e.printStackTrace();
                                                        }

                                                    } else {
                                                        percentAlls = percentup + percentdown;

                                                        percentup = percentup * 100;

                                                        percentFinal = percentup / percentAlls;

                                                        percentAll.add(percentFinal);

                                                        percentup = 0;
                                                        percentdown = 0;
                                                        percentAlls = 0;
                                                        percentFinal = 0;

                                                        testI = 0;


                                                    }


                                                }*/


                                            }


                                        }
                                    }
                                    if (compare2 - 1 == I && i == responeSplit2.length -1) {
                                        percentValue = upPorts + downPorts;
                                        percentValue2 = (upPorts * 100) / (percentValue);
                                        portUp.add(upPorts);
                                        portDown.add(downPorts);
                                        percent.add(percentValue2);


                                        if (compare - 1 > testI) {
                                            testI++;

                                            try {
                                                runs();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }

                                        } else {
                                            percentAlls = percentup + percentdown;

                                            percentup = percentup * 100;

                                            percentFinal = percentup / percentAlls;

                                            percentAll.add(percentFinal);

                                            percentup = 0;
                                            percentdown = 0;
                                            percentAlls = 0;
                                            percentFinal = 0;

                                            testI = 0;


                                        }


                                    }


                                }
                            }

                        }

                    });
                }

            });

    };

    public static ArrayList<String> myNetworks() {

        return finalNetwork;
    }
    public static ArrayList<Integer> upPortReturns() {

        return portUp;
    }
    public static ArrayList<Integer> downPortReturn() {

        return portDown;
    }
    public static ArrayList<Integer> percentReturn()
    {
        return percent;
    }
    public static ArrayList<Double> percentAllReturn()
    {
        return percentAll;
    }

    public static ArrayList<String> dateReturn()
    {
        return date;
    }

}
