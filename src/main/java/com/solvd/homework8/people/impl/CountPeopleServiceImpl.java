package com.solvd.homework8.people.impl;

import com.solvd.homework8.people.CountPeopleService;
import com.solvd.homework8.people.Countable;

public class CountPeopleServiceImpl implements CountPeopleService {

    @Override
    public void count(Countable countable) {
        countable.countPeople();
    }
}
