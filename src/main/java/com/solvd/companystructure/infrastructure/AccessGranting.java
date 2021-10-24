package com.solvd.companystructure.infrastructure;

import com.solvd.companystructure.exception.AccessException;
import com.solvd.companystructure.people.CEO;
import com.solvd.companystructure.people.Human;
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
