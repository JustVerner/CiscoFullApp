package com.example.martin.ciscofullapp.CommandRunner;

import android.util.Log;

import com.example.martin.ciscofullapp.Database.Login;
import com.example.martin.ciscofullapp.VisualRepresentations.Menu_Mockup;
import com.example.martin.ciscofullapp.getPorts.CertificateClient;
import com.example.martin.ciscofullapp.getPorts.MainActivity;

import junit.framework.Test;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.martin.ciscofullapp.CommandRunner.CommandRunner.taskid;
import static com.example.martin.ciscofullapp.getPorts.CertificateClient.getUnsafeOkHttpClient;

/**
 * Created by Martin on 06-11-2017.
 */

public class Task {
    private String url;
    private Login login = new Login();
    public static String fileid;
    private FileRunner fileRunner = new FileRunner();

    public void run() {

        url = "https://10.100.1.125"+taskid;

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

                final String[] responseRunner = responses.split("\\\"");

                fileid = responseRunner[8];


                Log.w("Succes", responses);


                if(responses.contains("CLI Runner request creation"))
                    run();
                if(!responses.contains("CLI Runner request creation"))
                    fileRunner.run();
            }
        });

    }
}
