package com.devgd.asiancountries;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.EventLogTags;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Country> countrylist;
    RecyclerView countryRecyclerView;
    CountryViewModel countryViewModel;
    SharedPreferences preferences;
    FloatingActionButton deletebutton;
    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setting views
        countryRecyclerView=findViewById(R.id.recyclerview);
        deletebutton=findViewById(R.id.delete);

        //sharedpreference
        preferences=this.getPreferences(MODE_PRIVATE);


        //CHECKING INTERNET CONNECTION
        ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null){
            dataFromDownload();
        }else {
            dataFromApi();

        }

    }

    public void dataFromApi(){
        Toast.makeText(this, "you're online", Toast.LENGTH_SHORT).show();
        //hiding the delete button
        deletebutton.setVisibility(View.INVISIBLE);

        //getting data
        countryViewModel=new ViewModelProvider(this).get(CountryViewModel.class);
        countrylist=new ArrayList<>();
        CountryAdapter adapter=new CountryAdapter(MainActivity.this);
        countryViewModel.getAllCountry().observe(this, new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {
                Log.i("sizeesssss", String.valueOf(countries.size()));
                adapter.setList(countries);
            }
        });

        countryRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        countryRecyclerView.setAdapter(adapter);
    }

    public void dataFromDownload() {


        if (preferences.getString("deleted", null) == null) {


            //getting data
            CountryAdapter adapter = new CountryAdapter(MainActivity.this);
            countryViewModel = new ViewModelProvider(this).get(CountryViewModel.class);
            countryViewModel.getOfflinedata().observe(this, new Observer<List<Country>>() {
                @Override
                public void onChanged(List<Country> countries) {
                    if(countries.size()!=0) {
                        //visible the delete button
                        deletebutton.setVisibility(View.VISIBLE);
                        Toast.makeText(MainActivity.this, "you're offline, your watching downloaded data", Toast.LENGTH_SHORT).show();
                        adapter.setList(countries);

                    }
                    else {
                        //hiding the delete button
                        deletebutton.setVisibility(View.INVISIBLE);
                        Toast.makeText(MainActivity.this, "you're offline, no download available", Toast.LENGTH_SHORT).show();

                    }
                }
            });
            countryRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            countryRecyclerView.setAdapter(adapter);

        }
        else{
            //hiding the delete button
            deletebutton.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "you're offline,no download data available", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteall(View view){
        SharedPreferences.Editor editor=preferences.edit();
        Toast.makeText(this, "downloaded data deleted successfully", Toast.LENGTH_SHORT).show();
        countryViewModel = new ViewModelProvider(this).get(CountryViewModel.class);
        countryViewModel.deleteall();
        countryRecyclerView.setVisibility(View.INVISIBLE);
        editor.putString("deleted","yes");
        editor.apply();


    }



}