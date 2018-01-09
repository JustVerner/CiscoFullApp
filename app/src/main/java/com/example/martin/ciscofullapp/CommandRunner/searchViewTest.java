package com.example.martin.ciscofullapp.CommandRunner;

import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.example.martin.ciscofullapp.VisualRepresentations.Menu_Mockup;
import com.example.martin.ciscofullapp.getPorts.MainActivity;

import static android.view.inputmethod.EditorInfo.IME_ACTION_DONE;
import static android.view.inputmethod.EditorInfo.IME_MASK_ACTION;

/**
 * Created by Frederik on 06-11-2017.
 */

public class searchViewTest extends AppCompatActivity{
    private String s;
    private String s2;
    private EditText editText;
    private EditText editNumber;
    private Button ownButton;
    private ListView list;
    public static boolean showText = false;
    private ArrayAdapter<String> adapter;

    public searchViewTest(){
        // Required empty public constructor
    }

    public searchViewTest(String s1) {
        this.s = s1;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.command_runner);

        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        editText = (EditText) findViewById(R.id.c_ownCommand);
        editNumber = (EditText) findViewById(R.id.CommandNumber);
        ownButton = (Button) findViewById(R.id.ownCommandButton);

        final String command[] =  {"show version", "show running", "show proc cpu" , "sh interface gig" , "ping" };

        list = (ListView) findViewById(R.id.listview);
        final EditText editSearch = (EditText) findViewById(R.id.c_runnerSearch);

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
                Intent intent = new Intent(searchViewTest.this , Menu_Mockup.class);
                startActivity(intent);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                s = parent.getAdapter().getItem(position).toString();

                startActivity(new Intent(searchViewTest.this, CommandClass.class).putExtra("name", s));
                startActivity(new Intent(searchViewTest.this, CommandRunner.class).putExtra("name1", s));

                switch(s) {
                    case "ping":
                        showText = true;
                        editNumber.setHint("Enter IP Address");
                        break;
                    case "sh interface gig":
                        showText = true;
                        editNumber.setHint("Enter Port");
                        break;
                }
            }
        });

        ownButton.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
                s2 = editText.getText().toString();

                switch (s2) {
                    case "ping":
                        showText = true;
                        editNumber.setInputType(InputType.TYPE_CLASS_TEXT);

                        editNumber.setHint("Enter IP Address");
                        break;
                    case "sh interface gig":
                        showText = true;
                        editNumber.setInputType(InputType.TYPE_CLASS_TEXT);

                        editNumber.setHint("Enter Port");
                        break;
                }

                startActivity(new Intent(searchViewTest.this, CommandRunner.class).putExtra("name1", s2));
                startActivity(new Intent(searchViewTest.this, CommandClass.class).putExtra("name", s2));


            }
        });

    }
}
