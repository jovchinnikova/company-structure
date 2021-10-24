package com.solvd.companystructure.services.impl;

import com.solvd.companystructure.services.Costable;
import com.solvd.companystructure.services.CountCostService;

public class CountCostServiceImpl implements CountCostService {

    @Override
    public void countPrice(Costable costable) {
        costable.countCost();
    }
}
