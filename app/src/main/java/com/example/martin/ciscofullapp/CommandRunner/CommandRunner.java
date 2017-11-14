package com.example.martin.ciscofullapp.CommandRunner;

import android.util.Log;

import com.example.martin.ciscofullapp.getPorts.CertificateClient;
import com.example.martin.ciscofullapp.getPorts.MainActivity;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.martin.ciscofullapp.getPorts.CertificateClient.getUnsafeOkHttpClient;

/**
 * Created by Martin on 06-11-2017.
 */

public class CommandRunner {

    public static String taskid = null;

    MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

    String url = "https://10.100.1.125/api/v1/network-device-poller/cli/read-request";

    //TestFunction testFunction = new TestFunction();
    //TestFunction testFunction;

    boolean commandCheck = false;


    public void run() throws IOException {


        
            getUnsafeOkHttpClient();

            RequestBody body = RequestBody.create(mediaType, "{\r\n  \"name\": \"martin\",\r\n  \"commands\": [\r\n    \"show version\"\r\n  ],\r\n  \"description\": \"\",\r\n  \"timeout\": 0,\r\n  \"deviceUuids\": [\r\n    \"7f94c530-7933-48e6-8aed-e094ebe1e368\"\r\n  ]\r\n}");

            Request request = new Request.Builder()
                    .url(url)
                    .header("X-Auth-Token", MainActivity.requiredTicket)
                    .addHeader("content-type", "application/json; charset=utf-8")
                    .post(body)
                    .build();


            CertificateClient.getUnsafeOkHttpClient().newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    call.cancel();

                    Log.w("Failure", "failure");

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {


                    final String responses = response.body().string();

                    final String[] responseRunner = responses.split("\\\"");

                    commandCheck = true;

                    taskid = responseRunner[9];

                    Log.w("Succes", taskid);

                    //testFunction = new TestFunction();
                    //testFunction.run();


                }
            });
        }

}
