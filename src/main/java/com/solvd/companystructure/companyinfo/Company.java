package com.solvd.companystructure.companyinfo;

import com.solvd.companystructure.companyinfo.impl.AccountingImpl;
import com.solvd.companystructure.exception.InvalidPhoneException;
import com.solvd.companystructure.infrastructure.Office;
import com.solvd.companystructure.people.CEO;
import com.solvd.companystructure.people.Worker;
import com.solvd.companystructure.services.Cleaning;
import com.solvd.companystructure.services.FoodSupply;
import com.solvd.companystructure.services.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Company {

    private static final Logger LOGGER = LogManager.getLogger(Company.class);

    private String title;
    private CEO director;
    private Set<Worker> workers;
    private final Department[] departments = Department.values();
    private AccountingImpl accounting;
    private List<Cleaning> cleanings;
    private int phoneNumber;
    private String site;
    private List<FoodSupply> foodSupplies;
    private List<Activity> activities;
    private List<Office> offices;
    private Map<Integer, Order> orders;

    public Company(String title) {
        this.title = title;
    }

    public void countIncome(){
        double income = 0.0;
        for (Map.Entry<Integer,Order> order : orders.entrySet()){
            income = income + order.getValue().getService().getPrice();
        }
        LOGGER.info("The income will be " + income + "$");
    }

    public void printDepartments(){
        String depList = "";
        for (Department dep: departments){
            depList += dep + " ";
        }
        LOGGER.info(title + " has following departments: " + depList);
    }

    @Override
    public String toString() {
        return ("The title of the company is " + title + System.lineSeparator() + title +
                " is run by " + director + System.lineSeparator() + "Company's website is "
                + site + System.lineSeparator() + "Company's phone number is " + phoneNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Company comp = (Company) obj;
        return title.equals(comp.title) && director.equals(comp.director)
                && phoneNumber == comp.phoneNumber && site.equals(comp.site);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + phoneNumber;
        result = prime * result + (title != null ? title.hashCode() : 0);
        result = prime * result + (director != null ? director.hashCode() : 0);
        result = prime * result + (site != null ? site.hashCode() : 0);
        return result;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDirector(CEO director) {
        this.director = director;
    }

    public CEO getDirector() {
        return director;
    }

    public void setWorkers(Set<Worker> workers) {
        this.workers = workers;
    }

    public Set<Worker> getWorkers() {
        return workers;
    }

    public Department[] getDepartments() {
        return departments;
    }

    public void setAccountingImpl(AccountingImpl accounting) {
        this.accounting = accounting;
    }

    public AccountingImpl getAccountingImpl() {
        return accounting;
    }

    public void setCleanings(List<Cleaning> cleanings) {
        this.cleanings = cleanings;
    }

    public List<Cleaning> getCleanings() {
        return cleanings;
    }

    public void setPhoneNumber(int phoneNumber) throws InvalidPhoneException {
        if (phoneNumber < 0) {
            throw new InvalidPhoneException("Invalid phone number");
        } else {
            this.phoneNumber = phoneNumber;
        }
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSite() {
        return site;
    }

    public void setFoodSupplies(List<FoodSupply> foodSupplies) {
        this.foodSupplies = foodSupplies;
    }

    public List<FoodSupply> getFoodSupplies() {
        return foodSupplies;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }

    public List<Office> getOffices() {
        return offices;
    }

    public void setOrders(Map<Integer,Order> orders){
        this.orders = orders;
    }

    public Map<Integer,Order> getOrders(){
        return orders;
    }
}
