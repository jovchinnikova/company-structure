package com.solvd.homework8.companyinfo;

import com.solvd.homework8.people.Worker;

import java.util.Set;

public interface Accounting {

    void vacationCount(Worker worker);

    void startCount(Worker worker);

    void allVacationCount(Set<Worker> workers);

}
