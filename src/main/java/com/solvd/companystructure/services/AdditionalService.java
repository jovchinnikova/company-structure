package com.solvd.companystructure.services;

public abstract class AdditionalService implements Costable {

    protected String serviceParam;
    protected double price;

    public AdditionalService(String serviceParam, double price) {
        this.serviceParam = serviceParam;
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        AdditionalService addService = (AdditionalService) obj;
        return (serviceParam.equals(addService.serviceParam)
                && price == addService.price);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (int) price;
        result = prime * result + (serviceParam != null ? serviceParam.hashCode() : 0);
        return result;

    }

    public void setServiceParam(String serviceParam) {
        this.serviceParam = serviceParam;
    }

    public String getServiceParam() {
        return serviceParam;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
