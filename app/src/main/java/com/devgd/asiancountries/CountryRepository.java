package com.devgd.asiancountries;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
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

public class CountryRepository {

    private CountryRoom countryRoom;
    private CountryDao countryDao;
    private List<CountryModelClass> countrylist;
    Context context;

    public CountryRepository(Application application){
        context=application.getApplicationContext();
       countryRoom=CountryRoom.getInstance(application);
       countryDao=countryRoom.countryDao();

    }

    public List<CountryModelClass> getAllCountry(){
        countrylist=new ArrayList<>();

        String url="https://restcountries.eu/rest/v2/region/asia";

        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    String language="";
                    String border="";

                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        language+=getlang(jsonObject.getJSONArray("languages"));
                        border+=getborder(jsonObject.getJSONArray("borders"));
                        countrylist.add(new CountryModelClass(jsonObject.getString("name"),
                                jsonObject.getString("capital"),jsonObject.getString("region")
                                ,jsonObject.getString("subregion"),jsonObject.getString("population"),
                                language,border,jsonObject.getString("flag")));

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, error -> {

        });
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(request);

    return countrylist;
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

}
