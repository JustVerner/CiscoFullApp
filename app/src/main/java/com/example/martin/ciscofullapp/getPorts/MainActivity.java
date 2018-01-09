package com.example.martin.ciscofullapp.getPorts;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.martin.ciscofullapp.CommandRunner.CommandRunner;
import com.example.martin.ciscofullapp.CommandRunner.Task;
//import com.example.martin.ciscofullapp.CommandRunner.CommandClass;
import com.example.martin.ciscofullapp.CommandRunner.CommandClass;
import com.example.martin.ciscofullapp.CommandRunner.searchViewTest;
import com.example.martin.ciscofullapp.CommandRunner.FileRunner;
import com.example.martin.ciscofullapp.Database.InsertData;
import com.example.martin.ciscofullapp.R;
import com.example.martin.ciscofullapp.SoftwareAdvisor.CcoLogin;
import com.example.martin.ciscofullapp.SoftwareAdvisor.SoftwareClass;
import com.example.martin.ciscofullapp.SoftwareAdvisor.SpecificLifecycle;
import com.example.martin.ciscofullapp.SoftwareAdvisor.Summary;
import com.example.martin.ciscofullapp.Spark.SparkPost;
import com.example.martin.ciscofullapp.VisualRepresentations.FragmentController;
import com.example.martin.ciscofullapp.VisualRepresentations.FragmentOne;
import com.example.martin.ciscofullapp.Database.Login;
import com.example.martin.ciscofullapp.VisualRepresentations.Menu_Mockup;

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

public class MainActivity extends AppCompatActivity{

    Login login = new Login();
    private Button update, statistics, commandRunner, taskRunner, fileButton, menuMockup;
    public static String requiredTicket;
    MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
    String url = "https://"+ipadress+"/api/v1/ticket";
    Spinner spinnerDevice, spinnerDate;
    /*String[] deviceArray = databaseHelper.getContacts();
    String[] dateArray = databaseHelper.getDate();
    public static String spinnerDeviceText;
    public static String spinnerDeviceTextDate;
    List<String> deviceUnique;
    List<String> dateUnique;
    public static ArrayAdapter<String> spinnerArrayAdapter;
    public static ArrayAdapter<String> spinnerArrayAdapterDate;*/
    String test = "{\r\n  \"password\": \"Sup3rfck!\",\r\n  \"username\": \"admin\"\r\n}";
    String full;
    String full2;
    SparkPost sparkPost = new SparkPost();
    CommandRunner commandRunnerClass = new CommandRunner();
    Task task = new Task();
    FileRunner fileRunners = new FileRunner();
    SpecificLifecycle specificLifecycle = new SpecificLifecycle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);



        //deviceUnique = Arrays.asList(deviceArray);
        //dateUnique = Arrays.asList(dateArray);


        update = (Button) findViewById(R.id.buttonUpdate);
        statistics = (Button) findViewById(R.id.buttonStatistics);
        commandRunner = (Button) findViewById(R.id.commandRunner);
        taskRunner = (Button) findViewById(R.id.taskRunner);
        fileButton = (Button) findViewById(R.id.fileButton);
        menuMockup = (Button) findViewById(R.id.menuButton);
        spinnerDate = (Spinner) findViewById(R.id.dateSpinner);

        /*List<String> deviceUniques = new ArrayList<>(new LinkedHashSet<>(deviceUnique));
        List<String> dateUniques = new ArrayList<>(new LinkedHashSet<>(dateUnique));
        deviceUniques.add("All");
        dateUniques.add("All");
        spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, deviceUniques);
        spinnerArrayAdapterDate = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dateUniques);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDevice.setAdapter(spinnerArrayAdapter);
        spinnerDate.setAdapter(spinnerArrayAdapterDate);*/
        final PortsGet portsGet = new PortsGet();
        final InsertData insertData = new InsertData();
        final FragmentOne fragmentOne = new FragmentOne();



        commandRunner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                Intent intent = new Intent(MainActivity.this, searchViewTest.class);
                startActivity(intent);

            }
            });

        fileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                Intent intent = new Intent(MainActivity.this, SoftwareClass.class);
                startActivity(intent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                /*try {
                    sparkPost.sparkrun();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
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
                //spinnerArrayAdapter.notifyDataSetChanged();
                //spinnerArrayAdapterDate.notifyDataSetChanged();
                Intent intent = new Intent(MainActivity.this, FragmentController.class);
                startActivity(intent);
            }
        });

        taskRunner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //specificLifecycle.run();
            }
        });

        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }

        menuMockup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Menu_Mockup.class);
                startActivity(intent);
            }
        });
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

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        requiredTicket  = myResponse.substring(myResponse.indexOf(":") +19, myResponse.indexOf(",")-1);

                        Toast toast = Toast.makeText(MainActivity.this, "Your token is " + requiredTicket + ". Application needs restart in 6 hours(1 hour idle)", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
                        toast.show();
                    }
                });

            }
        });
    }

}
