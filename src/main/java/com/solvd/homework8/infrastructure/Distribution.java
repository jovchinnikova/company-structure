package com.solvd.homework8.infrastructure;

import com.solvd.homework8.people.Client;
import com.solvd.homework8.people.Human;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class distributes workspaces and equipment between workers(including CEO)
 * and prints error if the constructor receives client
 *
 * @param <A>Worker/CEO
 * @param <B>Laptop/Computer
 * @param <C>WorkSpace
 */

public class Distribution<A extends Human, B extends Equipment, C extends WorkSpace> {

    private static final Logger LOGGER = LogManager.getLogger(Distribution.class);

    private A worker;
    private B equipment;
    private C space;

    public Distribution(A worker, B equipment, C space) {
        if (worker instanceof Client) {
            LOGGER.error("Clients don't work at the company!");
        } else {
            this.worker = worker;
            this.space = space;
            this.equipment = equipment;
        }
    }

    public void print() {
        LOGGER.info(worker + " works at " + space.getName() + " workspace using " + equipment.getMark()
                + " " + equipment.getClass().getSimpleName().toLowerCase());
    }
}
