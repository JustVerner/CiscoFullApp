package com.example.martin.ciscofullapp.VisualRepresentations;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.martin.ciscofullapp.R;

import java.util.ArrayList;

import static com.example.martin.ciscofullapp.VisualRepresentations.FragmentGraph.databaseHelper;

/**
 * Created by Martin on 25-09-2017.
 */

public class FragmentTest extends Fragment {


    String[] deviceArray = databaseHelper.getContacts();
    Integer[] upArray = databaseHelper.getUpPorts();
    Integer[] downArray = databaseHelper.getDownPorts();
    String[] dateArray = databaseHelper.getDate();
    String[] timeArray = databaseHelper.getTime();
    ArrayList<String> allList = new ArrayList<String>();
    GridView gv;
    View view;

    public FragmentTest() {
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

        view = inflater.inflate(R.layout.fragmenttest, container, false);

        run();

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Toast.makeText(getActivity().getApplicationContext(), "Device name|Used ports|Unused ports|Date"+gv.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();

            }
        });

        return view;
    }


    public void run() {

        allList.add("Device name | Up ports | Down ports | Date");
        for (int i = 0; i < deviceArray.length; i++) {
            allList.add(deviceArray[i] + " | " + upArray[i].toString() + " | " + downArray[i].toString() + " | " + dateArray[i]+"-"+timeArray[i]);
        }

        gv = (GridView) view.findViewById(R.id.gridView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, allList);

        gv.setAdapter(adapter);




    }



}