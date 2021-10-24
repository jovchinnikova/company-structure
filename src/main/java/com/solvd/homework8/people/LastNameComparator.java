package com.solvd.homework8.people;

import java.util.Comparator;

public class LastNameComparator implements Comparator<Human> {

    public int compare(Human a, Human b) {
        return a.getLastName().compareTo(b.getLastName());
    }
}

