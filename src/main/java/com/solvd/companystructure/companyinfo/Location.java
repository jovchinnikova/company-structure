package com.solvd.companystructure.companyinfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Location {
    FOREST("beautiful forest"), OFFICE("Solvd office"), CINEMA("cinema Belarus");

    private static final Logger LOGGER = LogManager.getLogger(Location.class);

    private final String name;

    Location(String name) {
        this.name = name;
    }

    public Optional<String> findActivity(List<Activity> activities) {
        String message = activities.stream()
                .filter(activity -> activity.getLocation().name.equals(this.name))
                .map(activity -> activity.getName() + " is held in " + this.name)
                .collect(Collectors.joining("; "));
        if (message.isEmpty()) {
            message = null;
        }
        return Optional.ofNullable(message);
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return name;
    }
}
