package com.solvd.homework8.people;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CEO extends Human {

    private static final Logger LOGGER = LogManager.getLogger(CEO.class);

    public CEO(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public void performAction() {
        LOGGER.info(this + " makes important decisions to run the company");
    }
}
