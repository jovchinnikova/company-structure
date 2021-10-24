package com.solvd.companystructure.infrastructure;

import com.solvd.companystructure.people.Countable;
import com.solvd.companystructure.people.Worker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Set;

public class Office implements Countable {

    private static final Logger LOGGER = LogManager.getLogger(Office.class);

    private String name;
    private String location;
    private List<Equipment> allEquipment;
    protected Set<Worker> workers;

    public Office(String name, String location, Set<Worker> workers) {
        this.name = name;
        this.location = location;
        this.workers = workers;
    }

    @Override
    public void countPeople() {
        int quantity = workers.size();
        LOGGER.info(quantity + " people work in " + name + " office");
    }

    public void countEquipment() {
        Integer quantity = 0;
        for (Equipment element : allEquipment) {
            quantity = quantity + element.getQuantity();
        }
        LOGGER.info("The quantity of equipment at " + name + " office is " + quantity);
    }

    @Override
    public String toString() {
        return (name + " office is located in " + location);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Office office = (Office) obj;
        return (name.equals(office.name) && location.equals(office.location));
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (name != null ? name.hashCode() : 0);
        result = prime * result + (location != null ? location.hashCode() : 0);
        return result;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setAllEquipment(List<Equipment> allEquipment) {
        this.allEquipment = allEquipment;
    }

    public List<Equipment> getAllEquipment() {
        return allEquipment;
    }

    public void setWorkers(Set<Worker> workers) {
        this.workers = workers;
    }

    public Set<Worker> getWorkers() {
        return workers;
    }
}
