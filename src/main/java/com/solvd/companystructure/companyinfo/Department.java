package com.solvd.companystructure.companyinfo;

import com.solvd.companystructure.services.Service;

import java.util.List;

public enum Department {

    QA("Quality Assurance"),WEBDEV("Software Web Development"),HR("Human resources"),PR("Public relations");

    private String title;
    private List<Service> services;

    Department(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return (title + " department");
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Service> getServices() {
        return services;
    }

}
