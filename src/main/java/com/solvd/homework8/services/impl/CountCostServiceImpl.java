package com.solvd.homework8.services.impl;

import com.solvd.homework8.services.Costable;
import com.solvd.homework8.services.CountCostService;

public class CountCostServiceImpl implements CountCostService {

    @Override
    public void countPrice(Costable costable) {
        costable.countCost();
    }
}
