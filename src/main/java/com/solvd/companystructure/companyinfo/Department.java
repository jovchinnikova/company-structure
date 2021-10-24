package com.solvd.companystructure.companyinfo;

import com.solvd.companystructure.services.Service;

import java.util.List;

public class Department {

    private String title;
    private List<Service> services;

    public Department(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return (title + " department");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Department department = (Department) obj;
        return title.equals(department.title);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (title != null ? title.hashCode() : 0);
        return result;
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
