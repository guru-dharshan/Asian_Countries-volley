package com.devgd.asiancountries;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities=CountryModelClass.class,version = 1)
public abstract class CountryRoom extends RoomDatabase {

    private static CountryRoom instance;
    public abstract CountryDao countryDao();

    public static synchronized CountryRoom getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),CountryRoom.class,"CountryRoom")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
