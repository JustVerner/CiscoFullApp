package com.example.martin.ciscofullapp.SoftwareAdvisor;

import android.app.Activity;
import android.util.Log;

import com.example.martin.ciscofullapp.Database.Login;
import com.example.martin.ciscofullapp.VisualRepresentations.Menu_Mockup;
import com.example.martin.ciscofullapp.getPorts.CertificateClient;
import com.example.martin.ciscofullapp.getPorts.MainActivity;

import java.io.IOException;
import java.util.Timer;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * Created by Frederik on 24-11-2017.
 */

public class SpecificLifecycle {
    private CcoLogin ccoLogin = new CcoLogin();
    private Login login = new Login();
    private String url = "https://10.100.1.125/api/v1/advice/cco-user/lifecycle?eolType=PSIRT&limit=100&offset=0";

    public void run() {

        CertificateClient.getUnsafeOkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .header("X-Auth-Token", login.requiredTicket)
                .addHeader("X-CAA-AUTH-TOKEN", ccoLogin.ccoToken)
                .addHeader("content-type", "application/json; charset=utf-8")
                .addHeader("cache-control", "no-cache")
                .build();

        CertificateClient.getUnsafeOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();

                Log.w("Failure", "failure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responses = response.toString();

                Log.w("Success", responses);
            }
        });

    }




}
