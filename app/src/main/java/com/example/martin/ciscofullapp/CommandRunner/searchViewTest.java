package com.example.martin.ciscofullapp.CommandRunner;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;
import android.widget.ListView;
import java.util.ArrayList;



import com.example.martin.ciscofullapp.R;

/**
 * Created by Frederik on 06-11-2017.
 */

public class searchViewTest extends AppCompatActivity {

    String commandList = getApplicationContext().getResources().getString(R.string.array_command_runner);
    ListView list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.command_runner);

        Intent intent = getIntent();
        if (intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            searchCommands(query);
        }
    }

    public void searchCommands(String query){

    }



}
