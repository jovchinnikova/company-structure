package com.solvd.companystructure.infrastructure;

import com.solvd.companystructure.people.Worker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class WorkSpace extends Office {

    private static final Logger LOGGER = LogManager.getLogger(WorkSpace.class);

    private int seatsQuantity;

    public WorkSpace(String name, String location, Set<Worker> workers, int seatsQuantity) {
        super(name, location, workers);
        this.seatsQuantity = seatsQuantity;
    }

    @Override
    public void countPeople() {
        int quantity = workers.size();
        if (quantity < seatsQuantity) {
            int vacant = seatsQuantity - quantity;
            LOGGER.info("There are " + vacant + " vacant seats");
        } else if (quantity > seatsQuantity) {
            LOGGER.info("The quantity of seats is not enough");
        } else {
            LOGGER.info("All the workers have seats");
        }
    }

    @Override
    public String toString() {
        return (super.getName() + " workspace is located on the " + super.getLocation() +
                " and has " + seatsQuantity + " seats");
    }

    public void setSeatsQuantity(int seatsQuantity) {
        this.seatsQuantity = seatsQuantity;
    }

    public int getSeatsQuantity() {
        return seatsQuantity;
    }
}
