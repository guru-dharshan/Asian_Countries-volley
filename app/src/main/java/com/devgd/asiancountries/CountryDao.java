package com.devgd.asiancountries;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CountryDao {

    @Insert
    public void insert(CountryModelClass countryModelClass);

    @Query("SELECT * FROM countrymodelclass")
    public LiveData<List<CountryModelClass>> countrylist();

    @Query("DELETE FROM countrymodelclass")
    public void deleteall();
}
