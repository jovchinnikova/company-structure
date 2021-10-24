package com.solvd.companystructure.companyinfo;

import com.solvd.companystructure.people.Worker;

import java.util.Set;

public interface Accounting {

    void vacationCount(Worker worker);

    void startCount(Worker worker);

    void allVacationCount(Set<Worker> workers);

}
