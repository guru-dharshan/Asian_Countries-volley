package com.devgd.asiancountries;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CountryDao {

    @Insert
    public void insert(Country country);

    @Query("SELECT * FROM Country")
    public LiveData<List<Country>> countrylist();

    @Query("DELETE FROM Country")
    public void deleteall();
}
