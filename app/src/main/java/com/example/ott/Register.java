package com.example.ott;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText userName,userEmail, userPassword,userMobile ;
    Button userRegister,b_userLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);
        userMobile = findViewById(R.id.userMobile);
        userPassword = findViewById(R.id.userPassword);
        userRegister = findViewById(R.id.userRegister);
        b_userLogin = findViewById(R.id.b_userLogin);

        b_userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,MainActivity.class));
            }
        });



        userRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating User Entity
                UserEntity userEntity = new UserEntity();
                userEntity.setUserEmail(userEmail.getText().toString());
                userEntity.setUserPassword(userPassword.getText().toString());
                userEntity.setUserName(userName.getText().toString());
                userEntity.setUserMobile(userMobile.getText().toString());

                if (validateInput(userEntity)){
                    // Do insert operation
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    final UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // Register User
                            userDao.registerUser(userEntity);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    Toast.makeText(getApplicationContext(), "User Registered", Toast.LENGTH_SHORT).show();

                                }
                            });


                        }
                    }).start();

                }else
                {
                    Toast.makeText(getApplicationContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }

    private boolean validateInput(UserEntity userEntity) {

        if( userEntity.getUserName().isEmpty() ||
                userEntity.getUserPassword().isEmpty() ||
                userEntity.getUserEmail().isEmpty()) {
            return false;
        }
        return true;

    }
}