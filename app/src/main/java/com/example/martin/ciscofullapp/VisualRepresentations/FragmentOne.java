package com.example.martin.ciscofullapp.VisualRepresentations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.martin.ciscofullapp.Database.Login;
import com.example.martin.ciscofullapp.R;
import com.example.martin.ciscofullapp.getPorts.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import com.example.martin.ciscofullapp.VisualRepresentations.FragmentGraph;
import static com.example.martin.ciscofullapp.VisualRepresentations.FragmentGraph.databaseHelper;

/**
 * Created by Martin on 21-09-2017.
 */

public class FragmentOne extends Fragment {

    private View view;
    private Spinner deviceSpinner, dateSpinner;
    private Button dataButton;

    private String[] deviceArray = databaseHelper.getContacts();
    private String[] dateArray = databaseHelper.getDate();
    public static String spinnerDeviceText;
    public static String spinnerDeviceTextDate;
    private List<String> deviceUnique;
    private List<String> dateUnique;
    public static ArrayAdapter<String> spinnerArrayAdapter;
    public static ArrayAdapter<String> spinnerArrayAdapterDate;
    private FragmentPie fragmentPie = new FragmentPie();
    private FragmentGraph fragmentGraph = new FragmentGraph();


    public FragmentOne() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragmentone, container, false);

        deviceUnique = Arrays.asList(deviceArray);
        dateUnique = Arrays.asList(dateArray);

        deviceSpinner = (Spinner)view.findViewById(R.id.spinnerDevice);
        dateSpinner = (Spinner)view.findViewById(R.id.spinnerDate);
        dataButton = (Button)view.findViewById(R.id.buttonData);

        run();

        spinnerDeviceText = "All";
        spinnerDeviceTextDate = "All";

        dataButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                spinnerDeviceText = deviceSpinner.getSelectedItem().toString();
                spinnerDeviceTextDate = dateSpinner.getSelectedItem().toString();
                fragmentPie.floatArrayList = null;
                fragmentPie.portNames = null;
               // getFragmentManager().beginTransaction().replace(R.id.chart, fragmentGraph).commit();



            }
        });

        return view;
    }



    public void run()
    {
        List<String> deviceUniques = new ArrayList<>(new LinkedHashSet<>(deviceUnique));
        List<String> dateUniques = new ArrayList<>(new LinkedHashSet<>(dateUnique));
        deviceUniques.add("All");
        dateUniques.add("All");
        spinnerArrayAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item,deviceUniques);
        spinnerArrayAdapterDate = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, dateUniques);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deviceSpinner.setAdapter(spinnerArrayAdapter);
        dateSpinner.setAdapter(spinnerArrayAdapterDate);
    }




}
