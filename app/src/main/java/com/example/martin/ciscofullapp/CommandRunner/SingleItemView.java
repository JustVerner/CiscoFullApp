package com.example.martin.ciscofullapp.CommandRunner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.martin.ciscofullapp.R;


/**
 * Created by Frederik on 09-11-2017.
 */

public class SingleItemView extends Activity {
     TextView txtCommand;

     String command;

     @Override
    public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.single_item_view);

         txtCommand = (TextView) findViewById(R.id.command);

     }
}
