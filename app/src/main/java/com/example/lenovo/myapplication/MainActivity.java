package com.example.lenovo.myapplication;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   private TextView login;
    private EditText mAccount;
    private EditText mPassword;
    private TextView log;
    private  CheckBox isRemeber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        login=(TextView)findViewById(R.id.logIn);
        final Toast toast=Toast.makeText(this,"账号或密码错误",Toast.LENGTH_SHORT);

        mAccount=(EditText)findViewById(R.id.account) ;
        mPassword=(EditText)findViewById(R.id.password);
        isRemeber=(CheckBox)findViewById(R.id.remeber) ;
        final SharedPreferences sharedPreferences=this.getSharedPreferences("config", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();





        log=(TextView)findViewById(R.id.log) ;
        if (sharedPreferences.getBoolean("isRemeber",false)) {
            mAccount.setText(sharedPreferences.getString("rAccount", ""));
            mPassword.setText(sharedPreferences.getString("rPassword", ""));

        }
        else
        {
            mAccount.setText("");
            mPassword.setText("");
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });



            log.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String password =mPassword.getText().toString();
                    String string=mAccount.getText().toString();
                    Intent i=new Intent(MainActivity.this,WelcomeActivity.class);
                    if (password.equals(sharedPreferences.getString(string,""))){
                    startActivity(i);
                }

                    else
                        toast.show();
            };
    });

      isRemeber.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              if (isChecked){
                  editor.putBoolean("isRemeber",isChecked);
                  editor.putString("rAccount",mAccount.getText().toString());
                  editor.putString("rPassword",mPassword.getText().toString());
                  editor.apply();

              }
              else
                   editor.putBoolean("isRemeber",isChecked);
                   editor.commit();

          }
      });

}
}



