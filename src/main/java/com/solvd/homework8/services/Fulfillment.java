package com.solvd.homework8.services;

import com.solvd.homework8.people.Worker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The class describes a worker and a type of service he is able to do
 *
 * @param <T> worker
 * @param <M> a types of work he can implement (foodSupply list or cleaning list)
 */

public class Fulfillment<T extends Worker, M extends List<? extends AdditionalService>> {

    private static final Logger LOGGER = LogManager.getLogger(Fulfillment.class);

    private Worker worker;
    private List<? extends AdditionalService> workTypes;

    public Fulfillment(T worker, M workTypes) {
        this.worker = worker;
        this.workTypes = workTypes;
    }

    public void print() {
        String name = "";
        for (AdditionalService task : workTypes) {
            name = name + " " + task.getServiceParam();
        }
        LOGGER.info(worker + " can fulfill " + name);
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public List<? extends AdditionalService> getWorkTypes() {
        return workTypes;
    }

    public void setWorkTypes(List<? extends AdditionalService> workTypes) {
        this.workTypes = workTypes;
    }
}
