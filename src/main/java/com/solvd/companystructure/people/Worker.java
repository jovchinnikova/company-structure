package com.solvd.companystructure.people;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

public class Worker extends Human {

    private static final Logger LOGGER = LogManager.getLogger(Worker.class);

    public static int vacationDuration = 28;

    private double averageSalary;
    private Integer tillProjectEnd;
    private LocalDateTime startVacation;

    public Worker(String firstName, String lastName, double averageSalary) {
        super(firstName, lastName);
        this.averageSalary = averageSalary;
    }

    @Override
    public void performAction() {
        LOGGER.info(this.toString() + " does given tasks");
    }

    public static void printVacDur() {
        LOGGER.info("The duration of vacation for all workers is " + Worker.vacationDuration + " days");
    }

    public void setAverageSalary(double averageSalary) {
        this.averageSalary = averageSalary;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public void setTillProjectEnd(Integer tillProjectEnd) {
        this.tillProjectEnd = tillProjectEnd;
    }

    public Integer getTillProjectEnd() {
        return tillProjectEnd;
    }

    public void setStartVacation(LocalDateTime startVacation) {
        this.startVacation = startVacation;
    }

    public LocalDateTime getStartVacation() {
        return startVacation;
    }
}
