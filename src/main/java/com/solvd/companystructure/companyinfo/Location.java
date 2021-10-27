package com.solvd.companystructure.companyinfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public enum Location{
    FOREST("beautiful forest"),OFFICE("Solvd office"), CINEMA("cinema Belarus");

    private static final Logger LOGGER = LogManager.getLogger(Location.class);

    private final String name;

    Location(String name){
        this.name = name;
    }

    public String findActivity(List<Activity> activities){
        String message = "";
        for(Activity activity:activities){
            if(activity.getLocation().name.equals(this.name)){
                message = activity.getName() + " is held in " + this.name + "; " + message;
            }
        }
        if(message.isEmpty()){
            message = "There are no activities held in the location";
        }
        return message;
    }

    public String getName(){
        return name;
    }
}
