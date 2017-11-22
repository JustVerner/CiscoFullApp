package com.example.martin.ciscofullapp.CommandRunner;

import android.util.Log;
import android.widget.TextView;

import com.example.martin.ciscofullapp.getPorts.CertificateClient;
import com.example.martin.ciscofullapp.getPorts.MainActivity;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.martin.ciscofullapp.CommandRunner.Task.fileid;
import static com.example.martin.ciscofullapp.getPorts.CertificateClient.getUnsafeOkHttpClient;

/**
 * Created by Martin on 08-11-2017.
 */

public class FileRunner {

    MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

    public String url;

    Timer timer = new Timer();

    public static String data;

    static public boolean test;

    TestFunction testFunction = new TestFunction();

    public void run() {

        url = "https://10.100.1.125/api/v1/file/"+fileid;

        //test = false;



        getUnsafeOkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .header("X-Auth-Token", MainActivity.requiredTicket)
                .addHeader("content-type", "application/json; charset=utf-8")
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

                data = responses;

                Log.w("Succes", responses);

            }


        });
    }
}

