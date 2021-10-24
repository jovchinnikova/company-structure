package com.solvd.homework8.services;

public class Service {

    private String title;
    private double price;

    public Service(String title, double price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return (title + " service");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Service service = (Service) obj;
        return title.equals(service.title);
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

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
