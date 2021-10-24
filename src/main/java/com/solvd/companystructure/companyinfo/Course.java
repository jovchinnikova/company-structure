package com.solvd.companystructure.companyinfo;

import com.solvd.companystructure.people.Worker;

import java.util.Set;

public class Course extends Activity {

    public Course(String name, String location, Set<Worker> workers) {
        super(name, location, workers);
    }
}
