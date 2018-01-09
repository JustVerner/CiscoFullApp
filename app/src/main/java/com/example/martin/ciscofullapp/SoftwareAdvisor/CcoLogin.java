package com.example.martin.ciscofullapp.SoftwareAdvisor;

import android.util.Log;

import com.example.martin.ciscofullapp.CommandRunner.CommandClass;
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

import static com.example.martin.ciscofullapp.CommandRunner.Task.fileid;
import static com.example.martin.ciscofullapp.getPorts.CertificateClient.getUnsafeOkHttpClient;

/**
 * Created by Martin on 24-11-2017.
 */

public class CcoLogin {
    private Login login = new Login();
    private String url ;
    private MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
    public static String ccoToken;
    private Summary summary = new Summary();

    public void run() {

       url = "https://10.100.1.125/api/v1/advice/cco-user";


        getUnsafeOkHttpClient();

        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"password\": \"FckStyr3r!\",\r\n  \"username\": \"perjense\"\r\n}");

        Request request = new Request.Builder()
                .url(url)
                .header("X-Auth-Token", login.requiredTicket)
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

                String[] responseSplit = responses.split("\\\"");

                ccoToken = responseSplit[7];

                Log.w("Succes", ccoToken);

                summary.run();
            }
        });
    }
}
