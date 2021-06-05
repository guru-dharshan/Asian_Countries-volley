package com.devgd.asiancountries;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class CountryViewModel extends AndroidViewModel {
     List<Country> countrylist=new ArrayList<>();
    private CountryRepository countryRepository;
    LiveData<List<Country>> listLiveData;
    LiveData<List<Country>> offlinedata;

    public CountryViewModel(@NonNull Application application) {
        super(application);
        countryRepository=new CountryRepository(application);
        listLiveData=countryRepository.getAllCountry();
        offlinedata=countryRepository.getOfflinedata();

    }

    public LiveData<List<Country>> getAllCountry(){
        return listLiveData;
    }

    public LiveData<List<Country>> getOfflinedata(){
        return offlinedata;
    }
    public void deleteall(){
        countryRepository.deleteall();
    }
}
