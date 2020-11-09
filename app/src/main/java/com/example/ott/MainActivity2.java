package com.example.ott;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;
    MovieDao movieDao;
    List<MovieList> movieLists;
    MyAdapter myAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        movieDao = MovieDatabase.getMovieDatabase(this).movieDao();
        movieLists = movieDao.getAllList();

        //recyclerview
        recyclerView = findViewById(R.id.recyclerView);
        showData();

    }



    private void showData() {

//        userModelList.add(new UserModel("Knocked Up", "2007", R.mipmap.ic_launcher_round,"Genre: Comedy,Romance","Rating: 6.9/10"));
//        userModelList.add(new UserModel("Avatar", "2009", R.mipmap.ic_launcher_round,"Genre: Adventure","Rating: 7.8/10"));
//        userModelList.add(new UserModel("Jumanji", "1995", R.mipmap.ic_launcher_round,"Genre: Family","Rating: 7.0/10"));
//        userModelList.add(new UserModel("Batman Begins", "2005", R.mipmap.ic_launcher_round,"Genre: Action","Rating: 8.2/10"));
//        userModelList.add(new UserModel("Iron Man", "2008", R.mipmap.ic_launcher_round,"Genre: Action","Rating: 7.9/10"));
//        userModelList.add(new UserModel("Casino Royale", "2006", R.mipmap.ic_launcher_round,"Genre: Action","Rating: 8.0/10"));
//        userModelList.add(new UserModel("Hot Tub Time Machine", "2010", R.mipmap.ic_launcher_round,"Genre: Sci-Fi","Rating: 6.4/10"));
//        userModelList.add(new UserModel("Due Date", "2010", R.mipmap.ic_launcher_round,"Genre: Comedy","Rating: 6.5/10"));
//        userModelList.add(new UserModel("Good Will Hunting", "1997", R.mipmap.ic_launcher_round,"Genre: Romance","Rating: 8.3/10"));
//        userModelList.add(new UserModel("Easy A", "2010", R.mipmap.ic_launcher_round,"Genre: Drama","Rating: 7.0/10"));
//        userModelList.add(new UserModel("Frequency", "2000", R.mipmap.ic_launcher_round,"Genre: Mystery","Rating: 7.3/10"));




        //set recyclerView's properties
        recyclerView.setLayoutManager(new LinearLayoutManager(this));   //linear layout

        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        myAdapter = new MyAdapter(movieLists,this::selectedUser);

        recyclerView.setAdapter(myAdapter);

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                myAdapter.getFilter().filter(newText);
                return true;
            }

        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id==R.id.action_logout) {
            logout();
            return true;

        }

        if (id==R.id.action_filter)
        {
            showFilterDialog();
            return true;

        }

        if (id==R.id.action_sort) {
            showSortDialog();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void showFilterDialog() {

        String [] options = {"Genre","Languages"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Filter By");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which == 0 ) { //Genre is clicked
                    showGenre();
                }

                if(which ==1) { //Languages is clicked
                   showLanguages();
                }
            }
        });
        builder.create().show(); //show Dialog
    }

    private void showGenre() {

        String [] options = {"Action","Comedy","Horror","Romance","Thriller"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Filter By Genre");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                myAdapter.getFilter().filter(options[which]);
                myAdapter.notifyDataSetChanged();
            }
        });
        builder.create().show(); }







    private void showLanguages() {

        String [] options = {"Tamil","English","Hindi","Malayalam"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Filter By Languages");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

               myAdapter.getFilter().filter(options[which]);
               myAdapter.notifyDataSetChanged();
            }
        });
        builder.create().show(); }







    private void showSortDialog() {

        String [] options = {"Ascending","Descending","Rating","Recently Added"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort By");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which ==0) { //Ascending is clicked
                    Collections.sort(movieLists,MovieList.BY_TITLE_ASCENDING);
                    myAdapter.notifyDataSetChanged();
                }

                    if(which ==1) { //Descending is clicked
                                Collections.sort(movieLists,MovieList.BY_TITLE_DESCENDING);
                        myAdapter.notifyDataSetChanged();
                    }

                        if(which ==2) { //Rating is clicked
                            Collections.sort(movieLists,MovieList.BY_TITLE_RATING);
                            myAdapter.notifyDataSetChanged();
                        }

                            if(which ==3) { //Recently Added is clicked
                                Collections.sort(movieLists,MovieList.BY_TITLE_RECENTLY_ADDED);
                                myAdapter.notifyDataSetChanged();
                            }
            }
        });
        builder.create().show(); //show Dialog
    }

    private void logout() {
        startActivity(new Intent(MainActivity2.this, MainActivity.class));
        finish();
    }


    public void selectedUser(MovieList movieImage) {
        startActivity(new Intent(MainActivity2.this, MainActivity3.class).
                putExtra("data",movieImage));
    }


}
