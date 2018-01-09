package com.example.martin.ciscofullapp.VisualRepresentations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.martin.ciscofullapp.CommandRunner.searchViewTest;
import com.example.martin.ciscofullapp.Database.InsertData;
import com.example.martin.ciscofullapp.Database.Login;
import com.example.martin.ciscofullapp.R;
import com.example.martin.ciscofullapp.SoftwareAdvisor.CcoLogin;
import com.example.martin.ciscofullapp.SoftwareAdvisor.SoftwareClass;
import com.example.martin.ciscofullapp.SoftwareAdvisor.SpecificLifecycle;
import com.example.martin.ciscofullapp.getPorts.CertificateClient;
import com.example.martin.ciscofullapp.getPorts.MainActivity;
import com.example.martin.ciscofullapp.getPorts.PortsGet;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.martin.ciscofullapp.Database.Login.device;
import static com.example.martin.ciscofullapp.Database.Login.ipadress;
import static com.example.martin.ciscofullapp.Database.Login.password;
import static com.example.martin.ciscofullapp.Database.Login.total;
import static com.example.martin.ciscofullapp.Database.Login.username;

/**
 * Created by Frederik on 28-11-2017.
 */

public class Menu_Mockup extends AppCompatActivity {


    private Button update, statistics, commandRunner, softwareAdvisor;
    private TextView editText1, editText2,editText3, editText4, editText5;
    private PortsGet portsGet = new PortsGet();
    private InsertData insertData = new InsertData();
    private Login login = new Login();
    private int sum;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_mockup);

        update = (Button) findViewById(R.id.buttonUpdate);
        statistics = (Button) findViewById(R.id.buttonStatistics);
        commandRunner = (Button) findViewById(R.id.commandRunner);
        softwareAdvisor = (Button) findViewById(R.id.softwareAdvisor);
        editText1 = (TextView) findViewById(R.id.editText);
        editText2 = (TextView) findViewById(R.id.editText2);
        editText3 = (TextView) findViewById(R.id.editText3);
        editText4 = (TextView) findViewById(R.id.editText4);
        editText5 = (TextView) findViewById(R.id.editText5);


        for (int i = 0 ; i <total.size();i++)
        {
            sum += total.get(i);
        }


        editText1.setText("Total devices : " + sum);
        editText2.setText(device.get(0) +" : "+ total.get(0));
        editText3.setText(device.get(1) +" : "+ total.get(1));
        editText4.setText(device.get(2) +" : "+ total.get(2));
        editText5.setText(device.get(3) +" : "+ total.get(3));




        Toast toast = Toast.makeText(Menu_Mockup.this, "Your token is " + login.requiredTicket + ". Application needs restart in 6 hours(1 hour idle)", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 50);
        toast.show();

        setupPieChart();


        commandRunner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Menu_Mockup.this, searchViewTest.class);
                startActivity(intent);

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
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

        softwareAdvisor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Menu_Mockup.this, SoftwareClass.class);
                startActivity(intent);
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
    }

    private void setupPieChart()
    {

        Integer[] arrayPie = total.toArray(new Integer[total.size()]);

        String[] arrayPieString = device.toArray(new String[device.size()]);

        List<PieEntry> pieEntries = new ArrayList<>();


        for (int i = 0; i< arrayPie.length; i++)
        {
            pieEntries.add(new PieEntry(arrayPie[i], arrayPieString[i]));
        }

        PieDataSet dataset = new PieDataSet(pieEntries, "Used ports");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataset);


        PieChart pieChart = (PieChart) findViewById(R.id.chart);
        pieChart.setData(data);
        pieChart.getDescription().setEnabled(false);
        pieChart.animateY(1000);
        pieChart.invalidate();
    }
}
