package com.example.user.fypclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class registeration extends AppCompatActivity {

    EditText name, pass, email, mobno;
    Button signup_btn, fb_btn, g_btn;
    String password, eMail, namee, Jsonobj, hello, mob_no;

    public String url = "http://52c577d8.ngrok.io/api/loginuser/post";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        name = (EditText) findViewById(R.id.e_name);
        pass = (EditText) findViewById(R.id.e_pass);
        email = (EditText) findViewById(R.id.e_email);
        mobno = (EditText) findViewById(R.id.mbl_no);
        signup_btn = (Button) findViewById(R.id.button2);
        fb_btn = (Button) findViewById(R.id.fbbtn);
        g_btn = (Button) findViewById(R.id.gbtn);
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namee = name.getText().toString();
                password = pass.getText().toString();
                eMail = email.getText().toString();
                mob_no = mobno.getText().toString();
                try {
                    post(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    OkHttpClient client = new OkHttpClient();

    public void post(String url) throws IOException {
        RequestBody body = new FormBody.Builder()
                .add("mobile_no", mob_no)
                .add("name", namee)
                .add("userName", namee)
                .add("password", password)
                .add("email", eMail)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getApplicationContext(), "somethng went wrong", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String myResponse = response.body().string();
                //  myResponse = myResponse.substring(1, myResponse.length() - 1); // yara
                myResponse = myResponse.replace("\\", "");
                hello = myResponse;
                registeration.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (hello.equals("true")) {
                            Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(registeration.this, MainActivity.class);
                            startActivity(intent);


                        } else {
                            Toast.makeText(getApplicationContext(), "tch tch", Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });

    }

}
