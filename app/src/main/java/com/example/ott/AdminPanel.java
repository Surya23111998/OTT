package com.example.ott;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class AdminPanel extends AppCompatActivity {
    TextView movieName,language,year,genre,rating;
    Button upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        movieName = findViewById(R.id.movieName);
        language = findViewById(R.id.language);
        year = findViewById(R.id.year);
        genre = findViewById(R.id.genre);
        rating = findViewById(R.id.rating);
        upload = findViewById(R.id.upload);

        final MovieDao movieDao = MovieDatabase.getMovieDatabase(this).movieDao();


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(movieName.getText().toString().isEmpty() | language.getText().toString().isEmpty()|
                        year.getText().toString().isEmpty() | genre.getText().toString().isEmpty()|
                        rating.getText().toString().isEmpty()) {
                    Toast.makeText(AdminPanel.this, "Enter Required Information", Toast.LENGTH_SHORT).show();
                }
                else{


                    MovieList movieList = new MovieList();
                    movieList.setMovieName(movieName.getText().toString());
                    movieList.setLanguage(language.getText().toString());
                    movieList.setYear(year.getText().toString());
                    movieList.setGenre(genre.getText().toString());
                    movieList.setRating(rating.getText().toString());

                    movieDao.insert(movieList);

                        Toast.makeText(AdminPanel.this, "Submitted successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AdminPanel.this,MainActivity2.class));

                }
            }
        });
    }
}
