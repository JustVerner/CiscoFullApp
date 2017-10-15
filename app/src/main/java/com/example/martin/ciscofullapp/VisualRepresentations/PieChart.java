package com.example.martin.ciscofullapp.VisualRepresentations;

/*import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.martin.ciscofullapp.R;
import com.example.martin.ciscofullapp.getPorts.MainActivity;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.example.martin.ciscofullapp.VisualRepresentations.Graph.databaseHelper;

/**
 * Created by Martin on 14-09-2017.
 */
/*
public class PieChart extends AppCompatActivity {

    String portNames[] = {"upPorts", "downPorts"};
    String[] deviceArray = databaseHelper.getContacts();
    Integer[] upArray = databaseHelper.getUpPorts();
    Integer[] downArray = databaseHelper.getDownPorts();
    String[] dateArray = databaseHelper.getDate();


    float floatUp;
    float floatDown;
    ArrayList<Float> floatArrayList = new ArrayList<Float>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.piechart);



        setupPieChart();
    }

    private void setupPieChart() {


        for (int i = 0; i< deviceArray.length;i++)
        {
            if(deviceArray[i].equals(MainActivity.spinnerDeviceText) && dateArray[i].equals(MainActivity.spinnerDeviceTextDate))
            {

                floatUp += (float) upArray[i];
                floatDown += (float) downArray[i];


            }

            if (MainActivity.spinnerDeviceText.equals("All") && dateArray[i].equals(MainActivity.spinnerDeviceTextDate)) {
                floatUp += (float) upArray[i];
                floatDown += (float) downArray[i];
            }

                if (MainActivity.spinnerDeviceText.equals("All") && MainActivity.spinnerDeviceTextDate.equals("All")) {
                    floatUp += (float) upArray[i];
                    floatDown += (float) downArray[i];


                }

                if (deviceArray[i].equals(MainActivity.spinnerDeviceText) && MainActivity.spinnerDeviceTextDate.equals("All")) {
                    floatUp += (float) upArray[i];
                    floatDown += (float) downArray[i];

                }
                else if (i == upArray.length - 1) {
                        floatArrayList.add(floatUp);
                        floatArrayList.add(floatDown);



                }

        }

        Float[] floatArray = floatArrayList.toArray(new Float[floatArrayList.size()]);

        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i< floatArray.length; i++)
        {
            pieEntries.add(new PieEntry(floatArray[i], portNames[i]));
        }

        PieDataSet dataset = new PieDataSet(pieEntries, "Used ports");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataset);


        com.github.mikephil.charting.charts.PieChart pieChart = (com.github.mikephil.charting.charts.PieChart) findViewById(R.id.chart);
        pieChart.setData(data);
        pieChart.animateY(1000);
        pieChart.invalidate();
    }
}
*/