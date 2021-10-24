package com.solvd.homework8.companyinfo.impl;

import com.solvd.homework8.companyinfo.Accounting;
import com.solvd.homework8.people.Worker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.Set;

public class AccountingImpl implements Accounting {

    private static final Logger LOGGER = LogManager.getLogger(AccountingImpl.class);

    private Set<Worker> workers;

    @Override
    public void vacationCount(Worker worker) {
        LocalDateTime currentDate = LocalDateTime.now();
        Integer daysPassed = currentDate.getDayOfYear() - worker.getStartVacation().getDayOfYear();
        Integer daysLeft = Worker.vacationDuration - daysPassed;
        LOGGER.info(worker + " has " + daysLeft + " days of vacation left");
        double vacationPay = daysPassed * worker.getAverageSalary();
        LOGGER.info(worker + " will receive " + vacationPay + "$");
    }

    @Override
    public void startCount(Worker worker) {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime dateStart = currentDate.plusDays(worker.getTillProjectEnd());
        LOGGER.info(worker + " will be able to go on vacation on " + dateStart.getDayOfMonth() + " " + dateStart.getMonth());
    }

    @Override
    public void allVacationCount(Set<Worker> workers) {
        LocalDateTime currentDate = LocalDateTime.now();
        double allPay = 0;
        for (Worker person : workers) {
            Integer daysPassed = currentDate.getDayOfYear() - person.getStartVacation().getDayOfYear();
            double vacationPay = daysPassed * person.getAverageSalary();
            allPay = allPay + vacationPay;
        }
        LOGGER.info("The accounting will pay " + allPay + "$ for all workers' vacation");
    }

    public Set<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<Worker> workers) {
        this.workers = workers;
    }

}

