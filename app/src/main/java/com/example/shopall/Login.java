package com.example.shopall;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    public static final String savedPassword = "com.example.shopall.passwordKey";
    private static String password;
    private EditText passwordEditText;
    private TextView invalidTextView;
    private Button loginButton;

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String psw) {
        password = psw;

    }




    public String getSavedPassword()
    {
        SharedPreferences sp = getSharedPreferences(savedPassword,MODE_PRIVATE);
        String str = sp.getString("PASSWORD","DEFAULT");
        return str;
    }




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(getSavedPassword().equals("DEFAULT")){
            setPassword("1234");
            SharedPreferences sharedPref = getSharedPreferences(savedPassword,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("PASSWORD", "1234");
            editor.commit();
        }
        else{
            setPassword(getSavedPassword());
        }


        passwordEditText = (EditText) findViewById(R.id.password);
        invalidTextView = (TextView)findViewById(R.id.invalid);
        loginButton = (Button) findViewById(R.id.loginButton);

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                loginButton.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>=4){
                    loginButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(passwordEditText.getText().toString().equals(getPassword())){
                    //success

                    invalidTextView.setVisibility(View.GONE);
                    passwordEditText.setText("");
                    Toast.makeText(getApplicationContext(),"Successfully Login",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }
                else{
                    //failed
                    invalidTextView.setVisibility(View.VISIBLE);
                    passwordEditText.setText("");


                }
            }
        });



    }

    public Boolean checkLogin(String enterPassword){
        if(enterPassword.equalsIgnoreCase(Login.getPassword())){
            return true;
        }
        else{
            return false;
        }
    }

}
