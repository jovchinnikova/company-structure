package com.solvd.companystructure.companyinfo;

import com.solvd.companystructure.people.Countable;
import com.solvd.companystructure.people.Worker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class Activity implements Countable {

    private static final Logger LOGGER = LogManager.getLogger(Activity.class);

    private String name;
    private String location;
    private Set<Worker> workers;

    public Activity(String name, String location, Set<Worker> workers) {
        this.name = name;
        this.location = location;
        this.workers = workers;
    }

    @Override
    public void countPeople() {
        Integer quantity = workers.size();
        LOGGER.info("The " + name + " will be visited by " + quantity + " workers");
    }

    @Override
    public String toString() {
        return ("The " + name + " will be held in the " + location);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Activity activity = (Activity) obj;
        return (name.equals(activity.name) && location.equals(activity.location));
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

    public void setWorkers(Set<Worker> workers) {
        this.workers = workers;
    }

    public Set<Worker> getWorkers() {
        return workers;
    }
}
