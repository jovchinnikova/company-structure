package com.solvd.companystructure.infrastructure;

public enum Mark {

    APPLE("USA"), LENOVO("China"),IBM("USA");

    private final String country;

    Mark(String country){
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
