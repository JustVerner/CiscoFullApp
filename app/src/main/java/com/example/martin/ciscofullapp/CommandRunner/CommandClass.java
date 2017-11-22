package com.example.martin.ciscofullapp.CommandRunner;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.martin.ciscofullapp.R;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;

/**
 * Created by Martin on 09-11-2017.
 */

public class CommandClass extends AppCompatActivity {

    private EditText commandNumber;
    private Button commandButton;
    public TextView commandTextView;
    CommandRunner commandRunner = new CommandRunner();
    boolean runcheck = false;
    String replaceString;
    Object textLock = new Object();
    public static boolean text = true;
    private boolean text2 = false;
    public static String s;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        commandButton = (Button) findViewById(R.id.commandButton);
        commandTextView = (TextView) findViewById(R.id.commandTextView);
        commandTextView.setMovementMethod(new ScrollingMovementMethod());
        commandNumber = (EditText) findViewById(R.id.CommandNumber);

        if (searchViewTest.showText == true) {
            commandNumber.setVisibility(View.VISIBLE);
        }

        commandNumber.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_CLASS_TEXT);

        Intent intent = getIntent();
        s = intent.getExtras().getString("name");


        commandButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Thread tyler1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (text && !text2) {
                            commandRunner.run();
                            text2 = true;
                            run();
                        }

                        while(text2)
                            if(!text)
                            {
                                setText();
                                text2 = false;
                                run();
                        }
                    }
                });
                tyler1.start();
            }
        });
    }


    public void setText() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (FileRunner.data != null) {
                    FileRunner.data = FileRunner.data.replace("\\n", System.getProperty("line.separator"));
                }
                commandTextView.setText(FileRunner.data);
            }
        });
    }
}