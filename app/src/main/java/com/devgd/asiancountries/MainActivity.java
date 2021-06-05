package com.devgd.asiancountries;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.EventLogTags;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<CountryModelClass> countrylist;
    RecyclerView countryRecyclerView;
    CountryViewModel countryViewModel;
    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setting views
        countryRecyclerView=findViewById(R.id.recyclerview);

        //CHECKING INTERNET CONNECTION
        ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null){
            dataFromDownload();
        }else{
          dataFromApi();

        }

        //view model
        countryViewModel=new ViewModelProvider(this).get(CountryViewModel.class);


    }

    public void dataFromApi(){
        countrylist=new ArrayList<>();
        countrylist=countryViewModel.getAllCountry();
        CountryAdapter adapter=new CountryAdapter(countrylist,MainActivity.this);
        countryRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        countryRecyclerView.setAdapter(adapter);
    }

    public void dataFromDownload(){


    }


}