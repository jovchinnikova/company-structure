package com.solvd.companystructure.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

public class Cleaning extends AdditionalService {

    private static final Logger LOGGER = LogManager.getLogger(Cleaning.class);

    private static int cleanDur = 2;

    private LocalDateTime startCleaning;

    public Cleaning(String cleanType, double price, LocalDateTime startCleaning) {
        super(cleanType, price);
        this.startCleaning = startCleaning;
    }

    @Override
    public void countCost() {
        double cost = price * cleanDur;
        LOGGER.info("The " + super.serviceParam + " will cost " + cost + "$");
    }

    public void finishCleaning() {
        LocalDateTime cleanEnd = this.startCleaning.plusDays(cleanDur);
        LOGGER.info("The " + super.serviceParam + " will be finished on "
                + cleanEnd.getDayOfMonth() + " " + cleanEnd.getMonth());
    }

    @Override
    public String toString() {
        return (super.serviceParam + " will cost " + price * cleanDur + "$ and  will last " + cleanDur + " days");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Cleaning clean = (Cleaning) obj;
        return (super.equals(clean) && startCleaning.equals(clean.startCleaning));
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = super.hashCode();
        result = prime * result + (startCleaning != null ? startCleaning.hashCode() : 0);
        return result;
    }

    public void setStartCleaning(LocalDateTime startCleaning) {
        this.startCleaning = startCleaning;
    }

    public LocalDateTime getStartCleaning() {
        return startCleaning;
    }
}
