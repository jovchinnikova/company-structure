package com.solvd.companystructure.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Consumer;

public class Connection{

    private static final Logger LOGGER = LogManager.getLogger(Connection.class);

    private String name;

    public Connection(String name){
        this.name = name;
    }

    public void create(Consumer<Integer> time){
        LOGGER.info("I created " + getName());
        time.accept(500);
        }

    public void read(Consumer<Integer> time){
        LOGGER.info("I read " + getName());
        time.accept(500);
    }

    public void update(Consumer<Integer> time){
        LOGGER.info("I updated " + getName());
        time.accept(500);
    }

    public void delete(Consumer<Integer> time){
        LOGGER.info("I deleted " + getName());
        time.accept(500);
    }

    public void print(Consumer<Integer> time){
        LOGGER.info("I printed " + getName());
        time.accept(500);
    }

    public String getName(){
        return name;
    }
}
