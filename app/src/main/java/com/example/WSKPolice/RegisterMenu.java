package com.example.WSKPolice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterMenu extends AppCompatActivity {
    public EditText Captcha;
    public Button RegButton;
    public EditText Login;
    public EditText Password;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_menu);
        Captcha = (EditText) findViewById(R.id.Captha);
        RegButton = (Button) findViewById(R.id.RegistrButton);
        Login = (EditText)  findViewById(R.id.login);
        Password = (EditText) findViewById(R.id.pwd);
        DB = new DBHelper(this);
    }

    public void onButtonClick(View view){
        String testCaptcha = "ic8";
        String login = Login.getText().toString();
        String pass = Password.getText().toString();
        if(Captcha.getText().toString().equals(testCaptcha)){
            if((login.length() == 0) || (pass.length() == 0)){
                Toast.makeText(getApplicationContext(), "Все поля должны быть заполнены!", Toast.LENGTH_SHORT).show();
            }
            else{
                Boolean checklogin = DB.checkLogin(login);
                if(checklogin == false) {
                    Boolean insert = DB.insertData(login,pass);
                    if (insert == true) {
                        Toast.makeText(getApplicationContext(), "Регистрация выполнена", Toast.LENGTH_SHORT).show();
                        this.finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Не удалось зарегистрировать", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Пользователь уже существует", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Invalid captcha", Toast.LENGTH_SHORT).show();
        }
    }
}