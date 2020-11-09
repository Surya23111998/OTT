package com.example.ott;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText userEmail, userPassword ;
    Button  userLogin,userRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        userEmail = findViewById(R.id.userEmail);
        userPassword = findViewById(R.id.userPassword);
        userLogin = findViewById(R.id.userLogin);
        userRegister = findViewById(R.id.userRegister);


        userRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Register.class));
            }

        });


        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmailText = userEmail.getText().toString();
                String userPasswordText = userPassword.getText().toString();
                if (userEmailText.isEmpty() || userPasswordText.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Fill all Fields!", Toast.LENGTH_SHORT).show();
                }
                else if(userEmailText.equals("Admin") && userPasswordText.equals("pass"))
                {
                    startActivity(new Intent(MainActivity.this,AdminPanel.class));
                    finish();
                }


                else {
                    //Perform Query
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            UserEntity userEntity = userDao.login(userEmailText,userPasswordText);
                            if (userEntity == null) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                String userName = userEntity.userName;
                                startActivity(new Intent(MainActivity.this, MainActivity2.class)
                                        .putExtra( "name", userName));
                                finish();


                            }

                        }
                    }).start();

                }

            }
        });
    }

}