package com.example.martin.ciscofullapp.SoftwareAdvisor;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.martin.ciscofullapp.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static com.example.martin.ciscofullapp.Database.Login.device;
import static com.example.martin.ciscofullapp.Database.Login.total;

/**
 * Created by Andreas on 28-Nov-17.
 */

public class SoftwareClass extends AppCompatActivity {

    private Button advisorButton, dataButton;
    public TextView advisorTextView;
    private Switch switchy;
    public static boolean text;
    private String[] number = new String[5];
    private int[] numbers = new int[5];
    private String[] names = {"psirtCritical", "psirtHigh", "psirtMedium", "psirtLow", "psirtEmpty"};
    private String line;
    public CcoLogin ccoLogin = new CcoLogin();
    private boolean text2;
    private int switchint = 0;
    private boolean switchState;
    private boolean textSet;
    PieChart pieChart;
    String[] token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system_advisor);

        advisorButton = (Button) findViewById(R.id.advisorButton);
        dataButton = (Button) findViewById(R.id.DataButton);
        advisorTextView = (TextView) findViewById(R.id.advisorTextView);
        advisorTextView.setMovementMethod(new ScrollingMovementMethod());
        switchy = (Switch) findViewById(R.id.switcher);
        switchy.setText("Pie chart");

        pieChart = (PieChart)findViewById(R.id.PieAdvisor);
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setCenterText("psirt chart");

        text = true;

        advisorButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Thread tyler1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (text && !text2) {

                            ccoLogin.run();
                            text2 = true;
                            run();
                        }

                        while(text2)
                            if(!text)
                            {
                                GetData();
                                text2 = false;
                                addDataSet();
                                run();
                            }
                    }
                });
                dataButton.setVisibility(View.VISIBLE);
                switchy.setVisibility(View.VISIBLE);
                tyler1.start();
            }
        });

        dataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (switchint) {
                    case 0:
                        pieChart.setVisibility(View.INVISIBLE);
                        advisorTextView.setVisibility(View.VISIBLE);
                        dataButton.setText("See pie chart");
                        setText();
                        switchint = 1;
                        break;
                    case 1:
                        pieChart.setVisibility(View.VISIBLE);
                        advisorTextView.setVisibility(View.INVISIBLE);
                        dataButton.setText("See raw data");
                        advisorTextView.setText("");
                        switchint = 0;
                        break;
                    default:
                        break;
                }
            }
        });

        switchy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    pieChart.setVisibility(View.INVISIBLE);
                    advisorTextView.setVisibility(View.VISIBLE);
                    switchy.setText("All data");
                    setText();
                    switchint = 1;
                }
                else
                {
                    pieChart.setVisibility(View.VISIBLE);
                    advisorTextView.setVisibility(View.INVISIBLE);
                    switchy.setText("Pie chart");
                    advisorTextView.setText("");
                    switchint = 0;
                }
            }
        });

    }

    public void addDataSet()
    {
      ArrayList<PieEntry> yEntries = new ArrayList<>();
      ArrayList<String> xEntries = new ArrayList<>();

      for(int i = 0; i < numbers.length; i++)
      {
          yEntries.add(new PieEntry(numbers[i], i));
      }

      for(int i = 1; i < names.length; i++)
      {
          xEntries.add(names[i]);
      }

      PieDataSet pieDataSet = new PieDataSet(yEntries, "psirts");
      pieDataSet.setSliceSpace(2);
      pieDataSet.setValueTextSize(12);

      ArrayList<Integer> colors = new ArrayList<>();

      colors.add(Color.RED);
      colors.add(Color.BLUE);
      colors.add(Color.GREEN);
      colors.add(Color.YELLOW);
      colors.add(Color.CYAN);

      pieDataSet.setColors(colors);
      pieChart.getLegend().setTextSize(20f);
      pieChart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

      PieData pieData = new PieData(pieDataSet);
      pieChart.setData(pieData);
      pieChart.invalidate();
    }

    public void GetData()
    {
        if (Summary.data != null) {

            token = Summary.data.split(",");

            for (int i = 0; i < token.length; i++) {
                if (token[i].length() > 15 && token[i].substring(1, 9).equals("psirtCnt")) {
                    line = token[i];
                    number[0] = line;
                } else if (token[i].substring(1, 10).equals("psirtHigh")) {
                    line = token[i];
                    number[1] = line;
                } else if (token[i].substring(1, 12).equals("psirtMedium")) {
                    line = token[i];
                    number[2] = line;
                } else if (token[i].substring(1, 9).equals("psirtLow")) {
                    line = token[i];
                    number[3] = line;
                } else if (token[i].substring(1, 11).equals("psirtEmpty")) {
                    line = token[i];
                    number[4] = line;
                }

            }

            for (int i = 0; i < 5; i++) {
                number[i] = number[i].replaceAll("[^0-9]", "");
            }

            numbers[0] = Integer.parseInt(number[0]);
            numbers[1] = Integer.parseInt(number[1]);
            numbers[2] = Integer.parseInt(number[2]);
            numbers[3] = Integer.parseInt(number[3]);
            numbers[4] = Integer.parseInt(number[4]);
        }
    }

    public void setText() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(!textSet) {
                    Summary.data = Summary.data.replace(",", System.getProperty("line.separator"));
                    Summary.data = Summary.data.replace("{", "{" + System.getProperty("line.separator"));
                    Summary.data = Summary.data.replace("}", System.getProperty("line.separator") + "}" + System.getProperty("line.separator"));
                    textSet = true;
                }
                advisorTextView.setText(Summary.data);
            }
        });
    }
}