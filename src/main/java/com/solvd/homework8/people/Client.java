package com.solvd.homework8.people;

import com.solvd.homework8.companyinfo.Company;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client extends Human {

    private static final Logger LOGGER = LogManager.getLogger(Client.class);

    public Client(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public void performAction() {
        LOGGER.info(this.toString() + " makes an order");
    }

    public void makeOrder(Company company, int telNumber) {
        if (telNumber == company.getPhoneNumber()) {
            LOGGER.info("The order was made by phone");
        } else {
            LOGGER.info("The wrong company phone number!");
        }
    }

    public void makeOrder(Company company, String website) {
        if (website == company.getSite()) {
            LOGGER.info("The order was made online");
        } else {
            LOGGER.info("The wrong company website!");
        }
    }
}
