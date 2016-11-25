package com.example.lenovo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
   private TextView console;
    private EditText mAccount;
    private EditText mPassword;
    private TextView complete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        console=(TextView)findViewById(R.id.console);
        SharedPreferences saver= getSharedPreferences("config",Context.MODE_PRIVATE);
        final SharedPreferences.Editor mSaver=saver.edit();
        complete=(TextView)findViewById(R.id.complete);
        console.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
        final Toast toast=   Toast.makeText(this,"账号密码不能为空",Toast.LENGTH_SHORT);
        mAccount=(EditText)findViewById(R.id.phonenumber);

        mPassword=(EditText)findViewById(R.id.password2);

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String putPassword=mPassword.getText().toString();
                String putAccount=mAccount.getText().toString();
                if (!putPassword.equals("")&&!putAccount.equals("")){
                      mSaver.putString(putAccount,putPassword);
                    mSaver.commit();
                    Intent i= new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);
                }
                else
                {
                    toast .show();
                  }
            };

            });



    }

}
