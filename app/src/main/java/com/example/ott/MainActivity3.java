package com.example.ott;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.TagLostException;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    ImageView imageView;
    TextView title, language, userRate;
    RatingBar ratingBar;
    String message;
    MovieList movieImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        imageView = findViewById(R.id.imageView);
        title = findViewById(R.id.title);
        language = findViewById(R.id.language);
        userRate = findViewById(R.id.rate);
        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setRating(load());
        userRate.setText(loadMsg());


        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            movieImage = (MovieList) intent.getSerializableExtra("data");
            title.setText(movieImage.getMovieName());
            language.setText(movieImage.getLanguage());
        }
        

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {


                int rating = (int) v;
                message = null;


                switch (rating) {
                    case 1:
                        message = "WORST";
                        break;
                    case 2:
                        message = "BETTER";
                        break;
                    case 3:
                        message = "GOOD";
                        break;
                    case 4:
                        message = "GREAT";
                        break;
                    case 5:
                        message = "AWESOME";
                        break;
                }

                userRate.setText(message);
                save(v,message);
            }
        });


    }

    public void save(float f, String msg)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("folder", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("rating",f);
        editor.putString("msg",msg);
        editor.commit();
    }
    public float load()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("folder", MODE_PRIVATE);
        float f = sharedPreferences.getFloat("rating",0f);
        return f;
    }

    public String loadMsg()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("folder", MODE_PRIVATE);
        String msg = sharedPreferences.getString("msg","");
        return msg;
    }


}