package com.solvd.companystructure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@MyDog(name = "dog")
public class Dog {

    private static final Logger LOGGER = LogManager.getLogger(Dog.class);

    private String name;
    private String breed;

    public Dog(String name, String breed){
        this.name = name;
        this.breed = breed;
    }

    private void bark(){
        LOGGER.info("Your " + breed + " called " + name + " is barking");
    }

    public String getBreed(){
        return breed;
    }

    public void setBreed(String breed){
        this.breed = breed;
    }
}
