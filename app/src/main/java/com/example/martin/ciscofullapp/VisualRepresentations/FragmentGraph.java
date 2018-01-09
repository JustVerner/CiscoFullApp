package com.example.martin.ciscofullapp.VisualRepresentations;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.martin.ciscofullapp.Database.DatabaseHelper;
import com.example.martin.ciscofullapp.R;
import com.example.martin.ciscofullapp.getPorts.MainActivity;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;

import com.example.martin.ciscofullapp.VisualRepresentations.FragmentOne;
import com.example.martin.ciscofullapp.VisualRepresentations.FragmentGraph;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Martin on 21-09-2017.
 */

public class FragmentGraph extends Fragment {

    private GraphView graph;
    public static DatabaseHelper databaseHelper = new DatabaseHelper();
    private String[] deviceArray = databaseHelper.getContacts();
    private Integer[] percentArray = databaseHelper.getPercent();
    private Double[] percentAllArray = databaseHelper.getPercentAll();
    private String[] dateAllArray = databaseHelper.getDateAll();
    private String[] dateArray = databaseHelper.getDate();
    private String[] timeArray = databaseHelper.getTime();
    private String[] timeArrayAll = databaseHelper.getTimeAll();
    private boolean test = false;
    private SimpleDateFormat finalFormat = new SimpleDateFormat("dd MMM yy HH:mm");
    private View view;

    public FragmentGraph() {
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
        view = inflater.inflate(R.layout.chart, container, false);
        run();

        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    return finalFormat.format(new Date((long) value));
                } else {
                    return super.formatLabel(value, isValueX);
                }
            }
        });
        graph.getGridLabelRenderer().setHumanRounding(false);
        graph.getGridLabelRenderer().setHorizontalLabelsAngle(45);

        return view;
    }


    public void run() {

        graph = (GraphView) view.findViewById(R.id.lineGraph);

        graph.removeAllSeries();

            LineGraphSeries<DataPoint> series = new LineGraphSeries<>();

        test = false;




            for (int i = 0; i < percentAllArray.length ; i++)
            {
                if (FragmentOne.spinnerDeviceText.equals("All") && FragmentOne.spinnerDeviceTextDate.equals("All")) {
                    try {
                        Date date = finalFormat.parse(dateAllArray[i]+" "+timeArrayAll[i]);
                        DataPoint point = new DataPoint(date, (percentAllArray[i]));
                        series.appendData(point, true, percentAllArray.length);
                        test = true;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                if (FragmentOne.spinnerDeviceText.equals("All") && dateAllArray[i].equals(FragmentOne.spinnerDeviceTextDate))
                {
                    try {
                        Date date = finalFormat.parse(dateAllArray[i]+" "+timeArrayAll[i]);
                        DataPoint point = new DataPoint(date, (percentAllArray[i]));
                        series.appendData(point, true, percentAllArray.length);
                        test = true;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (i == percentAllArray.length-1 && test == true)
                {
                    graph.addSeries(series);
                }


            }

            for (int I = 0; I < percentArray.length; I++) {
                if (deviceArray[I].equals(FragmentOne.spinnerDeviceText) && dateArray[I].equals(FragmentOne.spinnerDeviceTextDate))
                {
                    try {
                        Date date = finalFormat.parse(dateArray[I]+" "+timeArray[I]);
                        DataPoint point = new DataPoint(date, (percentArray[I]));
                        series.appendData(point, true, percentArray.length);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (deviceArray[I].equals(FragmentOne.spinnerDeviceText) && FragmentOne.spinnerDeviceTextDate.equals("All"))
                {
                    try {
                        Date date = finalFormat.parse(dateArray[I]+" "+timeArray[I]);
                        DataPoint point = new DataPoint(date, (percentArray[I]));
                        series.appendData(point, true, percentArray.length);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (I == percentArray.length-1 && test == false){
                    graph.addSeries(series);

                }

                series.setOnDataPointTapListener(new OnDataPointTapListener() {
                    @Override
                    public void onTap(Series series, DataPointInterface dataPoint) {
                        Toast.makeText(getActivity(), "Series1: On Data Point clicked: "+dataPoint.getY()+ "%", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        series.setColor(Color.GREEN);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(100);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollable(true);

        }

    }
