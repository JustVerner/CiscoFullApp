package com.example.martin.ciscofullapp.SoftwareAdvisor;

import android.util.Log;
import android.view.Menu;

import com.example.martin.ciscofullapp.Database.Login;
import com.example.martin.ciscofullapp.VisualRepresentations.Menu_Mockup;
import com.example.martin.ciscofullapp.getPorts.CertificateClient;
import com.example.martin.ciscofullapp.getPorts.MainActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.martin.ciscofullapp.getPorts.CertificateClient.getUnsafeOkHttpClient;

/**
 * Created by Andreas on 24-Nov-17.
 */

public class Summary {
    private String url = "https://10.100.1.125/api/v1/advice/cco-user/lifecycle/summary";
    private Login login = new Login();
    public static String data;
    public void run() {


        getUnsafeOkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .header("X-Auth-Token", login.requiredTicket)
                .addHeader("X-CAA-AUTH-TOKEN", CcoLogin.ccoToken)
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

                SoftwareClass.text = false;
                Log.w("Succes", responses);
            }
        });

    }
}
