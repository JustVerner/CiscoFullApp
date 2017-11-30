package com.example.martin.ciscofullapp.VisualRepresentations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.martin.ciscofullapp.CommandRunner.searchViewTest;
import com.example.martin.ciscofullapp.Database.InsertData;
import com.example.martin.ciscofullapp.R;
import com.example.martin.ciscofullapp.SoftwareAdvisor.CcoLogin;
import com.example.martin.ciscofullapp.SoftwareAdvisor.SpecificLifecycle;
import com.example.martin.ciscofullapp.getPorts.CertificateClient;
import com.example.martin.ciscofullapp.getPorts.MainActivity;
import com.example.martin.ciscofullapp.getPorts.PortsGet;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.martin.ciscofullapp.Database.Login.ipadress;
import static com.example.martin.ciscofullapp.Database.Login.password;
import static com.example.martin.ciscofullapp.Database.Login.username;

/**
 * Created by Frederik on 28-11-2017.
 */

public class Menu_Mockup extends AppCompatActivity{


    public  static String requiredTicket;
    private Button update, statistics, commandRunner, taskRunner, fileButton;
    MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
    String url = "https://"+ipadress+"/api/v1/ticket";
    CcoLogin ccoLogin = new CcoLogin();
    PortsGet portsGet = new PortsGet();
    InsertData insertData = new InsertData();
    SpecificLifecycle specificLifecycle = new SpecificLifecycle();
    String test = "{\r\n  \"password\": \"Sup3rfck!\",\r\n  \"username\": \"admin\"\r\n}";
    String full;
    String full2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_mockup);

        update = (Button) findViewById(R.id.buttonUpdate);
        statistics = (Button) findViewById(R.id.buttonStatistics);
        commandRunner = (Button) findViewById(R.id.commandRunner);



        commandRunner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                Intent intent = new Intent(Menu_Mockup.this, searchViewTest.class);
                startActivity(intent);

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                portsGet.run();
                update.setEnabled(false);
                statistics.setEnabled(false);
                Timer buttonTimer = new Timer();
                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                statistics.setEnabled(true);
                            }
                        });
                    }
                }, 5000);
            }

        });

        statistics.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                insertData.insertData();
                update.setEnabled(true);
                Intent intent = new Intent(Menu_Mockup.this, FragmentController.class);
                startActivity(intent);
            }
        });

        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void run() throws IOException {

        CertificateClient.getUnsafeOkHttpClient();

        full = test.replace("Sup3rfck!",password);
        full2 = full.replace("admin",username);


        RequestBody body = RequestBody.create(mediaType, full2);

        Request request = new Request.Builder()
                .url(url)
                .addHeader("content-type", "application/json; charset=utf-8")
                .post(body)
                .build();


        CertificateClient.getUnsafeOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();

                Menu_Mockup.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        requiredTicket  = myResponse.substring(myResponse.indexOf(":") +19, myResponse.indexOf(",")-1);

                        Toast toast = Toast.makeText(Menu_Mockup.this, "Your token is " + requiredTicket + ". Application needs restart in 6 hours(1 hour idle)", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                        toast.show();
                    }
                });

            }
        });
    }

}
