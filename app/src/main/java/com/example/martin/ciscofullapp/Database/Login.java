package com.example.martin.ciscofullapp.Database;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.martin.ciscofullapp.R;
import com.example.martin.ciscofullapp.VisualRepresentations.FragmentController;
import com.example.martin.ciscofullapp.VisualRepresentations.Menu_Mockup;
import com.example.martin.ciscofullapp.getPorts.CertificateClient;
import com.example.martin.ciscofullapp.getPorts.MainActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Martin on 22-09-2017.
 */

public class Login extends AppCompatActivity{

    EditText userName, passWord, ipAdress;
    Button login;
    public static String username, password, ipadress;
    String test = "{\r\n  \"password\": \"Sup3rfck!\",\r\n  \"username\": \"admin\"\r\n}";
    String full;
    String full2;
    MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
    public  static String requiredTicket;
    public boolean deviceCheck = false;
    public String[] splitString;
    public static ArrayList<String> device = new ArrayList<String>();
    public static ArrayList<Integer> total = new ArrayList<Integer>();
    public String deviceString;
    public Integer indexId;


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


               checkStuff();


            }
        });


    }

    public void run() {


        String url = "https://"+ipadress+"/api/v1/ticket";

        if (ipadress != null) {

            CertificateClient.getUnsafeOkHttpClient();

            full = test.replace("Sup3rfck!", password);
            full2 = full.replace("admin", username);


            RequestBody body = RequestBody.create(mediaType, full2);

            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("content-type", "application/json; charset=utf-8")
                    .post(body)
                    .build();


            CertificateClient.getUnsafeOkHttpClient().newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    call.cancel();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    final String myResponse = response.body().string();

                    Login.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            requiredTicket = myResponse.substring(myResponse.indexOf(":") + 19, myResponse.indexOf(",") - 1);

                            checkStuff();
                        }
                    });

                }
            });
        }
        else
        {
            checkStuff();
        }
    }

    public void runs()  {

        String networkdevicesURL = "https://"+ipadress+"/api/v1/network-device";

        CertificateClient.getUnsafeOkHttpClient();

        Request request = new Request.Builder()
                .url(networkdevicesURL)
                .header("X-Auth-Token", requiredTicket)
                .addHeader("content-type", "application/json; charset=utf-8")
                .build();


        CertificateClient.getUnsafeOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                final String myResponseNetwork = response.body().string();

                deviceCheck = true;

                Log.w("Succes", myResponseNetwork);

                splitString = myResponseNetwork.split("\\{");

                Log.w ("test", splitString[2].substring(1,7));
                Log.w ("test", splitString[2].substring(9, splitString[2].indexOf(",")));


                for (int i = 2; i < splitString.length; i++)
                {
                    deviceString = splitString[i].substring(10,splitString[i].indexOf(",")-1);

                    if(splitString[i].substring(1,7).equals("family") && !device.contains(deviceString))
                    {
                        device.add(deviceString);
                        total.add(1);
                    }
                    else
                    {
                        indexId = device.indexOf(deviceString);

                        total.set(indexId, Integer.valueOf(total.get(indexId)+1));
                    }

                }

                checkStuff();
            }
        });
    }


    public void checkStuff()
    {
        if(requiredTicket == null) {

            Log.w("Fail", "Fail");
            run();
        }

        if(requiredTicket != null && deviceCheck == false)
        {
            runs();
        }

        if ( requiredTicket != null && deviceCheck == true) {

            Log.w("Succes", "Succes");
            Intent intent = new Intent(Login.this, Menu_Mockup.class);
            startActivity(intent);
        }
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
