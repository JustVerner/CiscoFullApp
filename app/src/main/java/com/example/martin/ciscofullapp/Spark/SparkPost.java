package com.example.martin.ciscofullapp.Spark;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.martin.ciscofullapp.Database.InsertData;
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
 * Created by Martin on 12-09-2017.
 */

public class SparkPost{

    String theBody = "{\r\n\t\"roomId\": \"Y2lzY29zcGFyazovL3VzL1JPT00vMWIzOTY5YTAtNjYyNS0xMWU3LTlkYTQtNTMxNzA0NTBlZThi\",\r\n\t\"text\": \"Name : device  port % used : ports \"\r\n}";
    String body1;
    String bodyFinish;

    MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

    String url = "https://api.ciscospark.com/v1/messages";

    public void sparkrun() throws IOException {


        getUnsafeOkHttpClient();

        RequestBody body = RequestBody.create(mediaType, "{\"roomId\" : \"Y2lzY29zcGFyazovL3VzL1JPT00vMWIzOTY5YTAtNjYyNS0xMWU3LTlkYTQtNTMxNzA0NTBlZThi\", \"text\" : \"test\"}");

        Request request = new Request.Builder()
                .url(url)
                .addHeader("authorization", "Bearer NGE3M2I2MjQtNWJiNS00OGI0LThiY2ItY2QzYWM5NDgzNDE5NzU1Nzc2ZGItZmRh")
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

                Log.w("Succes", "succes");
            }
        });
    }

}
