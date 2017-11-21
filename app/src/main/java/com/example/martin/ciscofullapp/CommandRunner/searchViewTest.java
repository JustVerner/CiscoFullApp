package com.example.martin.ciscofullapp.CommandRunner;

import android.app.Activity;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import com.example.martin.ciscofullapp.R;
import com.example.martin.ciscofullapp.getPorts.MainActivity;

/**
 * Created by Frederik on 06-11-2017.
 */

public class searchViewTest extends AppCompatActivity{
    private ImageButton imageButton;
    private TextView text;
    private ListView list;
    int textLength = 0;
    private EditText editSearch;
    private String s;




    ArrayAdapter<String> adapter;
    ArrayList<HashMap<String, String>> arrayList;

    public searchViewTest(){

    }

    public searchViewTest(String s1) {
        this.s = s1;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.command_runner);

        imageButton = (ImageButton) findViewById(R.id.imageButton);

        final String command[] =  {"show version", "show running", "sh interface gig" , "ping" };

        list = (ListView) findViewById(R.id.listview);
        editSearch = (EditText) findViewById(R.id.c_runnerSearch);

        adapter = new ArrayAdapter<String>(this, R.layout.listview_item, R.id.command, command);
        list.setAdapter(adapter);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence cs, int i, int i1, int i2) {
                searchViewTest.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void onTextChanged(CharSequence cs, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                searchViewTest.this.adapter.getFilter().filter(editable);


            }
        });

        imageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(searchViewTest.this , MainActivity.class);
                startActivity(intent);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                s = parent.getAdapter().getItem(position).toString();

                Intent intent = new Intent(searchViewTest.this, CommandClass.class);
                intent.putExtra("name", s);
                startActivity(new Intent(searchViewTest.this, CommandRunner.class).putExtra("name1", s));
                startActivity(intent);
            }
        });

    }
}
