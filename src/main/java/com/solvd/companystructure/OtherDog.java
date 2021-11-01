package com.solvd.companystructure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@MyDog(name = "otherDog")
public class OtherDog {

    private static final Logger LOGGER = LogManager.getLogger(OtherDog.class);

    private String name;
    private String breed;

    private void bark(String name,String breed){
        LOGGER.info("Your " + breed + " called " + name + " is barking");
    }
}
