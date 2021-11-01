package com.solvd.companystructure.companyinfo;

import com.solvd.companystructure.people.Worker;

import java.util.Set;
import java.util.function.Function;

public interface Accounting {

    void vacationCount(Worker worker, Function<Worker,Integer> days);

    void startCount(Worker worker);

    void allVacationCount(Set<Worker> workers,Function<Worker,Integer> days);

}
