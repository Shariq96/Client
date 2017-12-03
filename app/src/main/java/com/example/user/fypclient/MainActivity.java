package com.example.user.fypclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    EditText mobile_no;
    EditText pass;
    CheckBox sv_pass;
    Button btn_login;
    TextView signup_txt;
    OkHttpClient Client;
    String mobno, password;
    String api_mob, api_pass;
    public String url = "http://52c577d8.ngrok.io/api/useracc/get";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mobile_no = (EditText) findViewById(R.id.etext1);
        pass = (EditText) findViewById(R.id.etext2);
        sv_pass = (CheckBox) findViewById(R.id.chk_pass);
        btn_login = (Button) findViewById(R.id.button);
        signup_txt = (TextView) findViewById(R.id.textView);
        signup_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup_intent = new Intent(MainActivity.this, registeration.class);
                MainActivity.this.startActivity(signup_intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mobno = mobile_no.getText().toString();
                    password = pass.getText().toString();
                    run();

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }


        });

    }

    private void run() throws IOException {

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter("mobile_no", mobno);
        urlBuilder.addQueryParameter("password",password);
        String url1 = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url1)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }


            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                String myResponse = response.body().string();
                //  myResponse = myResponse.substring(1, myResponse.length() - 1); // yara
                myResponse = myResponse.replace("\\", "");

                //JSONObject jarray = null;
                //  jarray = new JSONObject(myResponse);
                //api_pass = jarray.getString("password");
                api_pass = myResponse;
                //api_pass = jarray.getJSONObject(0).getString("password");

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (api_pass.equals("true")) {
                            Toast.makeText(getApplicationContext(), "Login", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_LONG).show();
                        }
                    }

                });


            }


        });

    }

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();


}
