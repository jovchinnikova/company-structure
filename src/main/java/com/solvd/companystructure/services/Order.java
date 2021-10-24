package com.solvd.companystructure.services;

import com.solvd.companystructure.people.Client;
import com.solvd.companystructure.people.Worker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Order implements Costable {

    private static final Logger LOGGER = LogManager.getLogger(Order.class);

    private Integer orderNumber;
    private Worker worker;
    private Service service;
    private Date deadline;
    private Client client;

    public Order(Integer orderNumber, Worker worker, Service service, LocalDate deadline, Client client) {
        this.orderNumber = orderNumber;
        this.worker = worker;
        this.service = service;
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(deadline.atStartOfDay(defaultZoneId).toInstant());
        this.deadline = date;
        this.client = client;
    }

    /**
     * If the order number is 100, there is a 30% discount on the order
     */
    @Override
    public void countCost() {
        double cost = service.getPrice();
        if (this.orderNumber == 100) {
            cost = 0.7 * cost;
        }
        LOGGER.info(client + " will pay " + cost + "$ for his order");
    }

    public void printDeadline(Order order) {
        LOGGER.info("The order number " + order.getOrderNumber() + " should be finished on " + order.getDeadline());
    }

    /**
     * If the deadline expires (supposing that orders are unperformed orders), the average salary per day will be reduced by $ 5
     */
    public void salaryPenalty(Worker worker) {
        if (deadline.before(new Date())) {
            worker.setAverageSalary(worker.getAverageSalary() - 5);
        }
        LOGGER.info("Taking into account the deadline of " + service + " "
                + worker.getFirstName() + "'s salary will be " + worker.getAverageSalary() + "$ per day");
    }

    @Override
    public String toString() {
        return ("The order number " + orderNumber + " requires " + service + System.lineSeparator() +
                "It should be done by " + worker + " until " + deadline + " for " + client);
    }

    /**
     * orderNumber - unique field
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Order order = (Order) obj;
        return orderNumber == order.orderNumber;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + orderNumber;
        return result;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }
}
