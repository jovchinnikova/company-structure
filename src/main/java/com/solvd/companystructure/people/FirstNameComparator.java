package com.solvd.companystructure.people;

import java.util.Comparator;

public class FirstNameComparator implements Comparator<Human> {

    public int compare(Human a, Human b) {
        return a.getFirstName().compareTo(b.getFirstName());
    }
}
