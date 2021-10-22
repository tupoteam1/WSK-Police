package com.example.WSKPolice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    public EditText logintext;
    public EditText pwd;
    String toastMessage;
    Button button;
    Button button2;
    DBHelper DB;
    CheckBox rememberMe;
    Boolean sessionSave = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        logintext = (EditText) findViewById(R.id.Login);
        pwd = (EditText) findViewById(R.id.Pwd);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        DB = new DBHelper(this);
        rememberMe = findViewById(R.id.checkBox);

        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");
        if (checkbox.equals("true")) {
            Intent intent = new Intent(this, MainMenu.class);
            startActivity(intent);
        }

        rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                if (compoundButton.isChecked()) {
                    sessionSave = true;
                } else if (!compoundButton.isChecked()) {
                    sessionSave = false;
                    editor.putString("remember", "false");
                    editor.apply();
                }
            }
        });
    }

    public void onButtonClick(View view){
        String login = logintext.getText().toString();
        String pass = pwd.getText().toString();

        if (TextUtils.isEmpty(login) || TextUtils.isEmpty(pass)) {
            toastMessage = "Поля не должны быть пустыми!";
            showToast(view, toastMessage);
        }
        else {
            Boolean checklogpass = DB.checkUserPassword(login, pass);
            if(checklogpass == true){
                Toast.makeText(getApplicationContext(), "Успешный вход", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainMenu.class);
                if (sessionSave == true) {
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "true");
                    editor.apply();
                }
                startActivity(intent);
            }else {
                toastMessage = "Неверный логин или пароль";
                showToast(view, toastMessage);
            }
        }
    }

    public void onRegisterButtonClick(View v){
        Intent Reg = new Intent(this, RegisterMenu.class);
        startActivity(Reg);
    }


    public void showToast(View view, String toastMessage) {
        //создаём и отображаем текстовое уведомление
        Toast toast = Toast.makeText(getApplicationContext(),
                toastMessage,
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.getView().setBackgroundColor(Color.RED);
        toast.show();
    }
}