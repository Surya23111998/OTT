package com.example.ott;


import android.content.Context;
import android.graphics.Movie;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = MovieList.class, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    private static MovieDatabase movieDatabase = null;
    private static final String databaseName = "movies";

    public abstract MovieDao movieDao();

    public static synchronized MovieDatabase getMovieDatabase(Context context)
    {
        if(movieDatabase == null)
            movieDatabase = Room.databaseBuilder(context.getApplicationContext(), MovieDatabase.class,databaseName).
                    allowMainThreadQueries().build();

        return movieDatabase;
    }

}
