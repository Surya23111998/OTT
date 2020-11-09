package com.example.ott;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Comparator;

@Entity(tableName = "Movies_DB")
public class MovieList implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "movieName")
    String movieName;

    @ColumnInfo(name = "year")
    String year;

    @ColumnInfo(name = "language")
    String language;

    @ColumnInfo(name = "rating")
    String rating;

    @ColumnInfo(name = "genre")
    String genre;


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getId() {
        return  id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }


    public static final Comparator<MovieList> BY_TITLE_ASCENDING = new Comparator<MovieList>() {
        @Override
        public int compare(MovieList o1, MovieList o2) {
            //System.out.println(o1.movieName +" "+ o2.movieName);
            return o1.movieName.compareTo(o2.movieName);
        }
    };

    public static final Comparator<MovieList> BY_TITLE_DESCENDING = new Comparator<MovieList>() {
        @Override
        public int compare(MovieList o1, MovieList o2) {
            //System.out.println(o1.movieName +" "+ o2.movieName);
            return o2.movieName.compareTo(o1.movieName);
        }
    };

    public static final Comparator<MovieList> BY_TITLE_RATING = new Comparator<MovieList>() {
        @Override
        public int compare(MovieList o1, MovieList o2) {
            //System.out.println(o1.movieName +" "+ o2.movieName);
            return o2.rating.compareTo(o1.rating);
        }
    };

    public static Comparator<MovieList> BY_TITLE_RECENTLY_ADDED = new Comparator<MovieList>() {

        @Override
        public int compare(MovieList o1, MovieList o2) {
            return  (o2.getId() - o1.getId());
        }
    };

}
