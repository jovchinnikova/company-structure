package com.solvd.companystructure.people;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.Comparator;

public abstract class Human implements Actionable, Comparable<Human> {

    private static final Logger LOGGER = LogManager.getLogger(Human.class);

    private String firstName;
    private String lastName;
    private LocalDateTime dob;

    public Human(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void countAge() {
        LocalDateTime currentDate = LocalDateTime.now();
        Integer age = currentDate.getYear() - dob.getYear();
        if (currentDate.getDayOfYear() < dob.getDayOfYear()) {
            age = age - 1;
        }
        LOGGER.info(this + " is " + age + " years old");
    }

    @Override
    public int compareTo(Human human) {
        Comparator<Human> humanComparator = new FirstNameComparator().thenComparing(new LastNameComparator());
        return humanComparator.compare(this, human);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Human human = (Human) obj;
        return firstName.equals(human.firstName) && lastName.equals(human.lastName)
                && dob.equals(human.dob);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (dob != null ? dob.hashCode() : 0);
        result = prime * result + (firstName != null ? firstName.hashCode() : 0);
        result = prime * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setDob(LocalDateTime dob) {
        this.dob = dob;
    }

    public LocalDateTime getDob() {
        return dob;
    }
}
