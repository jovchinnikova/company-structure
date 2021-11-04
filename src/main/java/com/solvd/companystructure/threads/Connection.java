package com.solvd.companystructure.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connection{

    private static final Logger LOGGER = LogManager.getLogger(Connection.class);

    private String name;

    public Connection(String name){
        this.name = name;
    }

    public void create(){
        LOGGER.info("I created " + getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void read(){
        LOGGER.info("I read " + getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        LOGGER.info("I updated " + getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(){
        LOGGER.info("I deleted " + getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void print(){
        LOGGER.info("I printed " + getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getName(){
        return name;
    }
}
