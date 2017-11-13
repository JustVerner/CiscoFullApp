package com.example.martin.ciscofullapp.CommandRunner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.martin.ciscofullapp.R;
import com.example.martin.ciscofullapp.getPorts.MainActivity;

import java.io.IOException;

/**
 * Created by Martin on 09-11-2017.
 */

public class commandClass extends AppCompatActivity {

    private Button commandButton, taskButton, fileButton;
    private TextView commandTextView;
    CommandRunner commandRunner = new CommandRunner();
    Task task = new Task();
    fileRunner filerunner = new fileRunner();
    boolean runcheck = false;
    String replaceString;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        commandButton = (Button) findViewById(R.id.commandButton);
        taskButton = (Button) findViewById(R.id.taskButton);
        fileButton = (Button) findViewById(R.id.fileButtons);
        commandTextView = (TextView) findViewById(R.id.commandTextView);



        commandButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                try {
                    commandRunner.run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        taskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                try {
                    task.run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        fileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                try {
                    filerunner.run();
                    run();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    run();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });





    }
    public void run() throws IOException {

        //replaceString = filerunner.data.replaceAll("\n", " \n ");

        commandTextView.setText(filerunner.data);

    }

}
