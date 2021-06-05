package com.devgd.asiancountries;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

public class CountryDetail extends AppCompatActivity {

    ImageView flagtv;
    TextView countrytv,capitaltv,regiontv,subregiontv,languagetv,populationtv,bordertv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);
        Intent intent=getIntent();
        String country = intent.getStringExtra("country");
        String capital = intent.getStringExtra("capital");
        String flag = intent.getStringExtra("flag");
        String region = intent.getStringExtra("region");
        String subreion = intent.getStringExtra("subregion");
        String language = intent.getStringExtra("language");
        String population = intent.getStringExtra("population");
        String border = intent.getStringExtra("border");

        //setting views
        countrytv=findViewById(R.id.detail_coutry);
        capitaltv=findViewById(R.id.detail_capital);
        regiontv=findViewById(R.id.detail_region);
        subregiontv=findViewById(R.id.detail_subregion);
        languagetv=findViewById(R.id.detail_language);
        populationtv=findViewById(R.id.detail_population);
        bordertv=findViewById(R.id.detail_border);
        flagtv=findViewById(R.id.detail_flag);

        //setting data
        countrytv.setText(country);
        capitaltv.setText(capital);
        regiontv.setText(region);
        subregiontv.setText(subreion);
        languagetv.setText(language);
        populationtv.setText(population);
        bordertv.setText(border);
        GlideToVectorYou.justLoadImage(this,
                Uri.parse(flag) , flagtv);




    }
}