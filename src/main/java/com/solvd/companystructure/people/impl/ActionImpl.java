package com.solvd.companystructure.people.impl;

import com.solvd.companystructure.people.Action;
import com.solvd.companystructure.people.Actionable;

public class ActionImpl implements Action {

    @Override
    public void takeAction(Actionable actionable) {
        actionable.performAction();
    }
}
