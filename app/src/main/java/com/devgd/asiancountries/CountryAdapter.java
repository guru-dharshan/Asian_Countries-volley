package com.devgd.asiancountries;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
    private List<Country> countrylist =new ArrayList<>();
    Context context;
    public CountryAdapter(Context context) {
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
        Country modelClass=countrylist.get(position);
        holder.name.setText(modelClass.getName());
        holder.capital.setText(modelClass.getCapital());
        GlideToVectorYou.justLoadImage((Activity) context,
                Uri.parse(modelClass.getFlag()) , holder.flag);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,CountryDetail.class);

                intent.putExtra("country",countrylist.get(position).getName());
                intent.putExtra("capital",countrylist.get(position).getCapital());
                intent.putExtra("region",countrylist.get(position).getRegion());
                intent.putExtra("subregion",countrylist.get(position).getSubRegion());
                intent.putExtra("flag",countrylist.get(position).getFlag());
                intent.putExtra("border",countrylist.get(position).getBorder());
                intent.putExtra("language",countrylist.get(position).getLanguage());
                intent.putExtra("population",countrylist.get(position).getPopulation());

                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return countrylist.size();
    }

    public void setList(List<Country> countrylist){
        this.countrylist=countrylist;
        notifyDataSetChanged();
    }


    public static class Notes extends RecyclerView.ViewHolder{

        CardView cardView;

        TextView name,capital;
        ImageView flag;
        public Notes(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.countryname);
            capital=itemView.findViewById(R.id.countrycapital);
            flag=itemView.findViewById(R.id.countryflag);
            cardView=itemView.findViewById(R.id.cardview);
        }
    }

}
