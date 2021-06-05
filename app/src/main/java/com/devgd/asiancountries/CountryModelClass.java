package com.devgd.asiancountries;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CountryModelClass {
    @PrimaryKey(autoGenerate = true)
    int id;
    String name,capital,region,subRegion,population,language,border,flag;

    public CountryModelClass(String name, String capital, String region, String subRegion, String population, String language, String border, String flag) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.subRegion = subRegion;
        this.population = population;
        this.language = language;
        this.border = border;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(String subRegion) {
        this.subRegion = subRegion;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
