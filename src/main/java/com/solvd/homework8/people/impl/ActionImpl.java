package com.solvd.homework8.people.impl;

import com.solvd.homework8.people.Action;
import com.solvd.homework8.people.Actionable;

public class ActionImpl implements Action {

    @Override
    public void takeAction(Actionable actionable) {
        actionable.performAction();
    }
}
