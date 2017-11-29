package com.example.martin.ciscofullapp.Database;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.martin.ciscofullapp.R;
import com.example.martin.ciscofullapp.VisualRepresentations.FragmentController;
import com.example.martin.ciscofullapp.getPorts.MainActivity;

import java.io.IOException;

/**
 * Created by Martin on 22-09-2017.
 */

public class Login extends AppCompatActivity{

    EditText userName, passWord, ipAdress;
    Button login;
    public static String username, password, ipadress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        userName = (EditText)findViewById(R.id.Username);
        passWord = (EditText)findViewById(R.id.Password);
        ipAdress = (EditText)findViewById(R.id.IpAdress);

        login = (Button)findViewById(R.id.buttonLogin);

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                username = userName.getText().toString();
                password = passWord.getText().toString();
                ipadress = ipAdress.getText().toString();

                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);


            }
        });


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        View v = getCurrentFocus();
        boolean ret = super.dispatchTouchEvent(event);

        if (v instanceof EditText) {
            View w = getCurrentFocus();
            int scrcoords[] = new int[2];
            w.getLocationOnScreen(scrcoords);
            float x = event.getRawX() + w.getLeft() - scrcoords[0];
            float y = event.getRawY() + w.getTop() - scrcoords[1];

            Log.d("Activity", "Touch event "+event.getRawX()+","+event.getRawY()+" "+x+","+y+" rect "+w.getLeft()+","+w.getTop()+","+w.getRight()+","+w.getBottom()+" coords "+scrcoords[0]+","+scrcoords[1]);
            if (event.getAction() == MotionEvent.ACTION_UP && (x < w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w.getBottom()) ) {

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
            }
        }
        return ret;
    }
}
