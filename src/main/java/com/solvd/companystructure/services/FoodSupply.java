package com.solvd.companystructure.services;

import com.solvd.companystructure.exception.InvalidQuantityException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

public class FoodSupply extends AdditionalService {

    private static final Logger LOGGER = LogManager.getLogger(FoodSupply.class);

    private int quantity;
    private Date supplyDate;

    public FoodSupply(String food, double price, int quantity, Date supplyDate) {
        super(food, price);
        if (quantity < 0) {
            throw new InvalidQuantityException("The quantity is invalid");
        } else {
            this.quantity = quantity;
        }
        this.supplyDate = supplyDate;
    }

    @Override
    public void countCost() {
        double cost = price * quantity;
        LOGGER.info("Your " + super.serviceParam + " will cost " + cost + "$");
    }

    public void bringFood() {
        LOGGER.info("Your " + super.serviceParam + " will be made on " + supplyDate);
    }

    @Override
    public String toString() {
        return (quantity + " " + super.serviceParam + " will be made on " +
                supplyDate + " and will cost " + price * quantity + "$");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        FoodSupply supply = (FoodSupply) obj;
        return (super.equals(supply) && quantity == supply.quantity
                && supplyDate.equals(supply.supplyDate));
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = super.hashCode();
        result = prime * result + quantity;
        result = prime * result + (supplyDate != null ? supplyDate.hashCode() : 0);
        return result;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setSupplyDate(Date supplyDate) {
        this.supplyDate = supplyDate;
    }

    public Date getSupplyDate() {
        return supplyDate;
    }
}
