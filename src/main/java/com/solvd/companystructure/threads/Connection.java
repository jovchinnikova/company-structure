package com.solvd.companystructure.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connection {

    private static final Logger LOGGER = LogManager.getLogger(Connection.class);

    public void create(){
        LOGGER.info("I created smth");
    }

    public void read(){
        LOGGER.info("I read smth");
    }

    public void update(){
        LOGGER.info("I updated smth");
    }

    public void delete(){
        LOGGER.info("I deleted smth");
    }

    public void print(){
        LOGGER.info("I printed smth");
    }
}
