package com.devgd.asiancountries;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;
import java.util.List;

public class CountryViewModel extends AndroidViewModel {
     List<CountryModelClass> countrylist=new ArrayList<>();
    private CountryRepository countryRepository;

    public CountryViewModel(@NonNull Application application) {
        super(application);
        countryRepository=new CountryRepository(application);
        countrylist=getAllCountry();

    }

    public List<CountryModelClass> getAllCountry(){
        return countryRepository.getAllCountry();
    }
}
