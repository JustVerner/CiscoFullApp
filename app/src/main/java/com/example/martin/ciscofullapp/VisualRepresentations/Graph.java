package com.example.martin.ciscofullapp.VisualRepresentations;

/*import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;

import com.example.martin.ciscofullapp.Database.DatabaseHelper;
import com.example.martin.ciscofullapp.Database.DatabaseManager;
import com.example.martin.ciscofullapp.Database.InsertData;
import com.example.martin.ciscofullapp.R;
import com.example.martin.ciscofullapp.getPorts.MainActivity;
import com.example.martin.ciscofullapp.getPorts.PortsGet;
import com.github.mikephil.charting.components.XAxis;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;



import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.example.martin.ciscofullapp.R.id.lineGraph;

/**
 * Created by Martin on 14-09-2017.
 */

/*public class Graph extends AppCompatActivity {

    private GraphView graph;
    public static DatabaseHelper databaseHelper = new DatabaseHelper();
    ArrayList<Integer> percentPorts;


    String[] deviceArray = databaseHelper.getContacts();
    Integer[] upArray = databaseHelper.getUpPorts();
    Integer[] downArray = databaseHelper.getDownPorts();
    Integer[] percentArray = databaseHelper.getPercent();
    Double[] percentAllArray = databaseHelper.getPercentAll();
    String[] dateAllArray = databaseHelper.getDateAll();
    String[] dateArray = databaseHelper.getDate();
    String[] timeArray = databaseHelper.getTime();
    String[] timeArrayAll = databaseHelper.getTimeAll();

    SimpleDateFormat format = new SimpleDateFormat("dd MMM yy");
    SimpleDateFormat tFormat  = new SimpleDateFormat("HH:mm");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart);
        graph = (GraphView) findViewById(lineGraph);

        run();

        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    return tFormat.format(new Date((long) value));
                } else {
                    return super.formatLabel(value, isValueX);
                }
            }
        });
        graph.getGridLabelRenderer().setHumanRounding(false);
        //graph.getGridLabelRenderer().setNumHorizontalLabels(10);
    }


  /*  public DataPoint[] data()
    {

        ArrayList<Double> testArray = new ArrayList<Double>(Arrays.asList(percentAllArray));

        int n = testArray.size();
        DataPoint[] values = new DataPoint[n];
        for (int i = 0; i< n; i++)
        {
            DataPoint v = new DataPoint(Double.parseDouble(dateAllArray[i]), (percentAllArray[i]));
            values[i] = v;
        }
        return values;
    }*/

   /* void run() {

       {

            LineGraphSeries<DataPoint> series = new LineGraphSeries<>();


            for (int i = 0; i < percentAllArray.length ; i++)
            {
                if (MainActivity.spinnerDeviceText.equals("All") && MainActivity.spinnerDeviceTextDate.equals("All")) {
                    try {
                        Date date = tFormat.parse(timeArrayAll[i]);
                        DataPoint point = new DataPoint(date, (percentAllArray[i]));
                        series.appendData(point, true, percentAllArray.length);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                if (MainActivity.spinnerDeviceText.equals("All") && dateAllArray[i].equals(MainActivity.spinnerDeviceTextDate))
                {
                    try {
                        Date date = tFormat.parse(timeArrayAll[i]);
                        DataPoint point = new DataPoint(date, (percentAllArray[i]));
                        series.appendData(point, true, percentAllArray.length);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (i == percentAllArray.length-1)
                {
                    graph.addSeries(series);
                }


            }

            for (int I = 0; I < percentArray.length; I++) {
                if (deviceArray[I].equals(MainActivity.spinnerDeviceText) && dateArray[I].equals(MainActivity.spinnerDeviceTextDate))
                {
                    try {
                        Date date = tFormat.parse(timeArray[I]);
                        DataPoint point = new DataPoint(date, (percentArray[I]));
                        series.appendData(point, true, percentArray.length);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (deviceArray[I].equals(MainActivity.spinnerDeviceText) && MainActivity.spinnerDeviceTextDate.equals("All"))
                {
                    try {
                        Date date = tFormat.parse(timeArray[I]);
                        DataPoint point = new DataPoint(date, (percentArray[I]));
                        series.appendData(point, true, percentArray.length);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (I == percentArray.length-1){
                    graph.addSeries(series);
                }
                series.setColor(Color.GREEN);
                graph.getViewport().setScalable(true);
                graph.getViewport().setScrollable(true);
            }


        }
    }

}
*/