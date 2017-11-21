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

/**
 * Created by Martin on 09-11-2017.
 */

public class CommandClass extends AppCompatActivity {

    private Button commandButton;
    public TextView commandTextView;
    CommandRunner commandRunner = new CommandRunner();
    Task task = new Task();
    FileRunner filerunner = new FileRunner();
    //TestFunction testFunction = new TestFunction();
    boolean runcheck = false;
    String replaceString;
    Timer timer = new Timer();
    Timer timer2 = new Timer();
    Timer timer3 = new Timer();
    Timer timer4 = new Timer();




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

                run();



                /*timer.schedule(new TimerTask() {

                    public void run() {
                        try {
                            commandRunner.run();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, 1000);
                timer2.schedule(new TimerTask() {

                    public void run() {
                        try {

                            task.run();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, 4000);
                timer3.schedule(new TimerTask() {

                    public void run() {
                        try {

                            filerunner.run();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, 5000);
                timer4.schedule(new TimerTask() {

                    public void run() {
                        setText();
                    }
                }, 6000);*/
            }
        });
    }


        public void run() {

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {

                    commandRunner.run();

                }
                }); t1.start();

                Thread t2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        task.run();
                    }
                }); t2.start();
                Thread t3 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        filerunner.run();
                    }
                }); t3.start();

                    Thread t4 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            setText();
                        }
                    }); t4.start();
                }

        /*taskButton.setOnClickListener(new View.OnClickListener() {
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

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });*/



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
