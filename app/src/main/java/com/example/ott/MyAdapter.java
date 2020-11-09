package com.example.ott;


import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {

    private List<MovieList> firstList, originalList;
    private SelectedUser selectedUser;


    public MyAdapter(List<MovieList> list, SelectedUser selectedUser ){
        firstList = list;
        originalList = list;
        this.selectedUser = selectedUser;

    }
    public  interface SelectedUser{
        void selectedUser(MovieList movieList);
    }



    @Override
    public Filter getFilter() {
        String [] language = {"Tamil","English","Hindi","Malayalam"};
        String [] genre = {"Action","Adventure","Comedy","Horror","Romance","Thriller"};
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();

                if(charSequence == null | charSequence.length() == 0)
                {
                    filterResults.count = firstList.size();
                    filterResults.values = firstList;
                }

                else if(Arrays.asList(genre).contains(charSequence))
                {
                    String searchChar =  charSequence.toString().toLowerCase();
                    List<MovieList> resultData = new ArrayList<>();
                    for(MovieList movieList : firstList)
                    {
                        if(movieList.getGenre().toLowerCase().contains(searchChar) )
                            resultData.add(movieList);
                    }
                    filterResults.count = resultData.size();
                    filterResults.values = resultData;
                }


                else if(Arrays.asList(language).contains(charSequence))
                {
                    String searchChar =  charSequence.toString().toLowerCase();
                    List<MovieList> resultData = new ArrayList<>();
                    for(MovieList movieList : firstList)
                    {
                        if(movieList.getLanguage().toLowerCase().contains(searchChar) )
                            resultData.add(movieList);
                    }
                    filterResults.count = resultData.size();
                    filterResults.values = resultData;
                }

                else{
                    String searchChar =  charSequence.toString().toLowerCase();
                    List<MovieList> resultData = new ArrayList<>();
                    for(MovieList movieList : firstList)
                    {
                        if(movieList.getMovieName().toLowerCase().contains(searchChar) )
                            resultData.add(movieList);
                    }
                    filterResults.count = resultData.size();
                    filterResults.values = resultData;
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                originalList = (List<MovieList>) filterResults.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }



    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        MovieList movieList= originalList.get(position);

        holder.myText1.setText(movieList.getMovieName());
        holder.myText2.setText(movieList.getYear());
        holder.myImage.setImageResource(R.mipmap.ic_launcher_round);
        holder.genre.setText(movieList.getGenre());
        holder.rating.setText(movieList.getRating());
        holder.language.setText(movieList.getLanguage());


    }

    @Override
    public int getItemCount() {
        return originalList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView myText1,myText2,genre,rating,language;
        ImageView myImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            myText1 = itemView.findViewById(R.id.myText1);
            myText2 = itemView.findViewById(R.id.myText2);
            myImage = itemView.findViewById(R.id.myImageView);
            genre = itemView.findViewById(R.id.genre);
            rating = itemView.findViewById(R.id.rating);
            language = itemView.findViewById(R.id.language);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedUser.selectedUser(originalList.get(getAdapterPosition()));
                }
            });


        }
    }
}
