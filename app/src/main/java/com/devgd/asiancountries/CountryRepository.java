package com.devgd.asiancountries;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;

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
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountryRepository {

    private CountryRoom countryRoom;
    private CountryDao countryDao;
    List<Country> countrylist;
    LiveData<List<Country>> offlinedata;
    Context context;
    StringRequest request;
    String url;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    ExecutorService service;

    public CountryRepository(Application application){
        context=application.getApplicationContext();
       countryRoom=CountryRoom.getInstance(application);
       countryDao=countryRoom.countryDao();
       offlinedata=countryDao.countrylist();
        url="https://restcountries.eu/rest/v2/region/asia";
        sharedPreferences=context.getSharedPreferences("preference",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        service= Executors.newFixedThreadPool(1);


    }

    public LiveData<List<Country>> getAllCountry(){

        countrylist=new ArrayList<>();
        MutableLiveData<List<Country>> mutableLiveData=new MutableLiveData<>();

        request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=new JSONArray(response);
                    String language="";
                    String border="";

                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        Log.i("size", String.valueOf(jsonArray.length()));
                        language+=getlang(jsonObject.getJSONArray("languages"));
                        border+=getborder(jsonObject.getJSONArray("borders"));
                        Country country=new Country(jsonObject.getString("name"),
                                jsonObject.getString("capital"),jsonObject.getString("region")
                                ,jsonObject.getString("subregion"),jsonObject.getString("population"),
                                language,border,jsonObject.getString("flag"));
                        countrylist.add(country);

                        if(sharedPreferences.getString("check",null)==null){
                            //add
                            insert(country);
                        }




                    }
                    mutableLiveData.setValue(countrylist);
                    editor.putString("check","ok");
                    editor.apply();



                } catch (JSONException e) {
                    e.printStackTrace();


                }

            }


        }, error -> {
        });
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(request);


        return mutableLiveData;
    }

    public void insert(Country country){
        service.execute(() -> {
            countryDao.insert(country);
        });
    }

    public String getlang(JSONArray jsonArray){
        String result="";

        for (int i=0;i<jsonArray.length();i++){
            try {
                JSONObject object=jsonArray.getJSONObject(i);
                if(i!=jsonArray.length()-1)
                    result += object.getString("name")+", ";
                else
                    result += object.getString("name");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return result;
    }


    public String getborder(JSONArray jsonArray){
        String res="";

        for (int i=0;i<jsonArray.length();i++){

            try {
                if(i!=jsonArray.length()-1)
                    res += jsonArray.getString(i) + ", ";

                else
                    res += jsonArray.getString(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        return res;
    }

    public LiveData<List<Country>> getOfflinedata(){
        return offlinedata;
    }

    public void deleteall(){
        service.execute(() -> {
            countryDao.deleteall();
        });

    }

}
