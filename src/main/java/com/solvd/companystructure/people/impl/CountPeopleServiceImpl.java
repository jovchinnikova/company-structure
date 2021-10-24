package com.solvd.companystructure.people.impl;

import com.solvd.companystructure.people.CountPeopleService;
import com.solvd.companystructure.people.Countable;

public class CountPeopleServiceImpl implements CountPeopleService {

    @Override
    public void count(Countable countable) {
        countable.countPeople();
    }
}
