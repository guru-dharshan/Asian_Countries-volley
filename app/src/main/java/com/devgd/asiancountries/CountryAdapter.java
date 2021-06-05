package com.devgd.asiancountries;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.util.ArrayList;
import java.util.List;


public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.Notes>{
    private List<CountryModelClass> countrylist =new ArrayList<>();
    Context context;
    public CountryAdapter(List<CountryModelClass> countrylist, Context context) {
        this.countrylist = countrylist;
        this.context = context;
    }
    @NonNull
    @Override
    public Notes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.countrylayout,parent,false);
        return new Notes(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final Notes holder, final int position) {
        CountryModelClass modelClass=countrylist.get(position);
        holder.name.setText(modelClass.getName());
        holder.capital.setText(modelClass.getCapital());
        GlideToVectorYou.justLoadImage((Activity) context,
                Uri.parse(modelClass.getFlag()) , holder.flag);
    }
    @Override
    public int getItemCount() {
        return countrylist.size();
    }


    public static class Notes extends RecyclerView.ViewHolder{

        TextView name,capital;
        ImageView flag;
        public Notes(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.countryname);
            capital=itemView.findViewById(R.id.countrycapital);
            flag=itemView.findViewById(R.id.countryflag);
        }
    }

}
