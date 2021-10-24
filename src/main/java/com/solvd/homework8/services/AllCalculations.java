package com.solvd.homework8.services;

import com.solvd.homework8.people.Countable;

/**
 * The class enables to count people (at an activity or in the office) or price (of a service or an order)
 *
 * @param <E> activity/course/office/workspace
 * @param <B> foodSupply/cleaning/order
 */

public class AllCalculations<E extends Countable, B extends Costable> {

    private E element;
    private B otherElement;

    /**
     * The method is overloaded to enable count either people or price assuming what type of parameter was given
     */
    public <M extends Countable> void calculate(M element) {
        element.countPeople();
    }

    public <N extends Costable> void calculate(N element) {
        element.countCost();
    }

    public void setElement(E element) {
        this.element = element;
    }

    public E getElement() {
        return element;
    }

    public B getOtherElement() {
        return otherElement;
    }

    public void setOtherElement(B otherElement) {
        this.otherElement = otherElement;
    }
}
