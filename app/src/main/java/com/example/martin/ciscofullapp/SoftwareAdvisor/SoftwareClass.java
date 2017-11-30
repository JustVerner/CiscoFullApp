package com.example.martin.ciscofullapp.SoftwareAdvisor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.martin.ciscofullapp.R;

/**
 * Created by Andreas on 28-Nov-17.
 */

public class SoftwareClass extends AppCompatActivity {

    private Button advisorButton;
    public TextView advisorTextView;
    public static boolean text;
    public CcoLogin ccoLogin = new CcoLogin();
    private boolean text2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system_advisor);

        advisorButton = (Button) findViewById(R.id.advisorButton);
        advisorTextView = (TextView) findViewById(R.id.advisorTextView);
        advisorTextView.setMovementMethod(new ScrollingMovementMethod());

        text = true;

        advisorButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Thread tyler1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (text && !text2) {

                            ccoLogin.run();
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
                if (Summary.data != null) {
                    Summary.data = Summary.data.replace("\\n", System.getProperty("line.separator"));
                }
                advisorTextView.setText(Summary.data);
            }
        });
    }
}
