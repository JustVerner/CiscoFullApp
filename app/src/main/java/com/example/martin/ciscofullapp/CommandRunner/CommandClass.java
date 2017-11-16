package com.example.martin.ciscofullapp.CommandRunner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.TextWatcher;
import android.widget.Toast;

import com.example.martin.ciscofullapp.R;

import java.io.IOException;

/**
 * Created by Martin on 09-11-2017.
 */

public class CommandClass extends AppCompatActivity {

    private Button commandButton, taskButton, fileButton;
    public TextView commandTextView;
    //CommandRunner commandRunner = new CommandRunner();
    //Task task = new Task();
    //FileRunner filerunner = new FileRunner();
    TestFunction testFunction = new TestFunction();
    boolean runcheck = false;
    String replaceString;
    EditText edit;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        commandButton = (Button) findViewById(R.id.commandButton);
        taskButton = (Button) findViewById(R.id.taskButton);
        fileButton = (Button) findViewById(R.id.fileButtons);
        commandTextView = (TextView) findViewById(R.id.commandTextView);
        commandTextView.setMovementMethod(new ScrollingMovementMethod());



        commandButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                try {
                    testFunction.run();
                    TextWatcherTest();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });


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
    }

    public void ChangeText()
    {
        commandTextView.setText(FileRunner.data);
    }

    public void TextWatcherTest() {
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                FileRunner.data = edit.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Toast toast = Toast.makeText(CommandClass.this, edit.toString(), Toast.LENGTH_SHORT  );
                toast.show();
            }
        });
    }
}
