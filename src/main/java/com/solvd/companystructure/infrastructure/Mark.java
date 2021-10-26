package com.solvd.companystructure.infrastructure;

public enum Mark {

    APPLE("USA"), LENOVO("China"),IBM("USA");

    private String country;

    Mark(String country){
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
