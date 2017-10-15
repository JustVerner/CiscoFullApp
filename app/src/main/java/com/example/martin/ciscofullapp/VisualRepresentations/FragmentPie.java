package com.example.martin.ciscofullapp.VisualRepresentations;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.martin.ciscofullapp.R;
import com.example.martin.ciscofullapp.getPorts.MainActivity;
import com.example.martin.ciscofullapp.getPorts.PortsGet;
import com.github.mikephil.charting.charts.*;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.example.martin.ciscofullapp.VisualRepresentations.FragmentGraph.databaseHelper;


/**
 * Created by Martin on 21-09-2017.
 */

public class FragmentPie extends Fragment {

    String portNames[] = {"upPorts", "downPorts"};
    String[] deviceArray = databaseHelper.getContacts();
    Integer[] upArray = databaseHelper.getUpPorts();
    Integer[] downArray = databaseHelper.getDownPorts();
    String[] dateArray = databaseHelper.getDate();

    Integer totalInt;
    Integer totalInt2 = 0;
    Integer totalInt3 = 0;
    Integer testInt;

    boolean allDevice;
    boolean allAll;
    boolean dateDevice;
    boolean allDate;



    PortsGet portsGet = new PortsGet();

    float floatUp;
    float floatDown;

    public ArrayList<Float> floatArrayList = new ArrayList<Float>();

    View view;

    public FragmentPie() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.piechart, container, false);

        setupPieChart();

        return view; }



    private void setupPieChart() {


        allDevice = false;
        allDate = false;
        dateDevice = false;
        allAll = false;

        String portNames[] = {"upPorts", "downPorts"};

        testInt = 0;

        totalInt = deviceArray.length/portsGet.devices;

        if (floatArrayList.size()== 2)
        {
            floatArrayList.remove(1);
            floatArrayList.remove(0);
        }




        for (int i = 0; i< deviceArray.length;i++)
        {


                if(deviceArray[i].equals(FragmentOne.spinnerDeviceText) && dateArray[i].equals(FragmentOne.spinnerDeviceTextDate))
            {

                floatUp = 0;
                floatDown = 0;
                floatUp += (float) upArray[i];
                floatDown += (float) downArray[i];
                dateDevice = true;


            }

            if (FragmentOne.spinnerDeviceText.equals("All") && dateArray[i].equals(FragmentOne.spinnerDeviceTextDate)) {

                    totalInt2++;
                    floatUp += (float) upArray[i];
                    floatDown += (float) downArray[i];
                    allDevice = true;


            }

            if (FragmentOne.spinnerDeviceText.equals("All") && FragmentOne.spinnerDeviceTextDate.equals("All")) {

                floatUp += (float) upArray[i];
                floatDown += (float) downArray[i];
                allAll = true;

            }

            if (deviceArray[i].equals(FragmentOne.spinnerDeviceText) && FragmentOne.spinnerDeviceTextDate.equals("All")) {



                totalInt3++;
                floatUp += (float) upArray[i];
                floatDown += (float) downArray[i];

                allDate = true;


            }


        }

        if (dateDevice == true) {
            floatArrayList.add(floatUp);
            floatArrayList.add(floatDown);
            floatUp = 0;
            floatDown = 0;

        }

        if (allDevice == true)
        {
            totalInt2 = totalInt2/ portsGet.devices;
            floatUp = floatUp/totalInt2;
            floatDown = floatDown/totalInt2;
            floatArrayList.add(floatUp);
            floatArrayList.add(floatDown);
            floatUp = 0;
            floatDown = 0;
            totalInt2 = 0;
        }

        if (allAll == true)
        {
            floatUp = floatUp / totalInt;
            floatDown = floatDown / totalInt;
            floatArrayList.add(floatUp);
            floatArrayList.add(floatDown);
            floatUp = 0;
            floatDown = 0;
            totalInt = 0;

        }

        if(allDate == true) {
            floatUp = floatUp / totalInt3;
            floatDown = floatDown / totalInt3;
            totalInt3 = 0;
            floatArrayList.add(floatUp);
            floatArrayList.add(floatDown);
            floatUp = 0;
            floatDown = 0;
        }

        Float[] floatArray = floatArrayList.toArray(new Float[floatArrayList.size()]);

        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i< floatArray.length; i++)
        {
            pieEntries.add(new PieEntry(floatArray[i], portNames[i]));
        }

        PieDataSet dataset = new PieDataSet(pieEntries, "");



        PieData data = new PieData(dataset);

        dataset.setColors(ContextCompat.getColor(getContext(), R.color.green),ContextCompat.getColor(getContext(),R.color.blue));

        PieChart pieChart = (PieChart) view.findViewById(R.id.chart);
        data.setValueTextSize(30f);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setTextSize(20f);
        pieChart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        pieChart.setDrawEntryLabels(false);
        pieChart.setData(data);
        pieChart.animateY(1000);
        pieChart.invalidate();
    }
}
