package com.solvd.homework8.infrastructure;

import com.solvd.homework8.exception.AccessException;
import com.solvd.homework8.people.CEO;
import com.solvd.homework8.people.Human;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccessGranting implements AutoCloseable {

    private static final Logger LOGGER = LogManager.getLogger(AccessGranting.class);

    private Human human;

    public AccessGranting(Human human) {
        this.human = human;
    }

    public void grantAccess() {
        if (human.getClass().isAssignableFrom(CEO.class)) {
            LOGGER.trace("The access to the resource is received");
        } else {
            throw new AccessException("You don't have enough rights to access the resource");
        }
    }

    @Override
    public void close() {
        LOGGER.trace("The access is closed");
    }
}
