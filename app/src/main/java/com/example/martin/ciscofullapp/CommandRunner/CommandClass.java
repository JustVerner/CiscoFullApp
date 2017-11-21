package com.example.martin.ciscofullapp.CommandRunner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
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

    private Button commandButton;
    public TextView commandTextView;
    CommandRunner commandRunner = new CommandRunner();
    Task task = new Task();
    FileRunner filerunner = new FileRunner();
    TestFunction testFunction = new TestFunction();
    boolean runcheck = false;
    String replaceString;
    Timer timer = new Timer();
    Timer timer2 = new Timer();
    Timer timer3 = new Timer();
    Timer timer4 = new Timer();
    Object textLock = new Object();
    private boolean text = true;
    private boolean text2 = true;
    public static String s;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        commandButton = (Button) findViewById(R.id.commandButton);
        commandTextView = (TextView) findViewById(R.id.commandTextView);
        commandTextView.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();
        s = intent.getExtras().getString("name");

        commandButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final Thread tyler1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if(text && text2) {
                            commandRunner.run();
                            run();
                        }
                        if(FileRunner.data != null) {
                            text = false;
                            run();
                        }
                        while (!text){
                            setText();
                            text = true;
                        }

                    }
                });
                tyler1.start();




            }
        });
    }
/*   public void update(float deltaTime) {
        while(FileRunner.data != null && text == true) {
            setText();
            text = false;
        }
   }*/


   public void setText() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(FileRunner.data != null) {
                    FileRunner.data = FileRunner.data.replace("\\n", System.getProperty("line.separator"));
                }
                commandTextView.setText(FileRunner.data);
            }
        });
    }
}
