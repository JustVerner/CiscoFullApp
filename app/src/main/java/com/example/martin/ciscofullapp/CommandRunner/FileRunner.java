package com.example.martin.ciscofullapp.CommandRunner;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.example.martin.ciscofullapp.Database.Login;
import com.example.martin.ciscofullapp.VisualRepresentations.Menu_Mockup;
import com.example.martin.ciscofullapp.getPorts.CertificateClient;
import com.example.martin.ciscofullapp.getPorts.MainActivity;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

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
    private String url;
    private Login login = new Login();
    public String[] metadataArray, metadataArray2;
    public String metaData, metaData2;
    public static String data;
    public static boolean test;

    public void run() {

        url = "https://10.100.1.125/api/v1/file/" + fileid;


        getUnsafeOkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .header("X-Auth-Token", login.requiredTicket)
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

                metadataArray = responses.split("\\{");

                metaData = metadataArray[3];

                metadataArray2 = metaData.split("\\}");

                metaData2 = metadataArray2[0];

                data = metaData2;

                CommandClass.text = false;

                Log.w("Succes", data);

            }


        });
    }
}

