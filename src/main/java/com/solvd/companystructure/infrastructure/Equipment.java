package com.solvd.companystructure.infrastructure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Equipment {

    private static final Logger LOGGER = LogManager.getLogger(Equipment.class);

    private Mark mark;
    private Integer quantity;

    public Equipment(Mark mark, Integer quantity) {
        this.mark = mark;
        this.quantity = quantity;
    }

    public void writeOrigin(){

        LOGGER.info("This " + this.getClass().getSimpleName().toLowerCase() + " was made in " + mark.getCountry());
    }

    public void writeCharacteristic(){
        switch (mark){
            case APPLE:
                LOGGER.info("This equipment is great");
                break;
            case LENOVO:
                LOGGER.info("It's not the best choice of equipment");
                break;
            case IBM:
                LOGGER.info("This equipment is good");
                break;
            default:
                LOGGER.info("There's no info about your equipment");
                break;
        }
    }

    @Override
    public String toString() {
        return ("There are " + quantity + " " + this.getClass().getSimpleName().toLowerCase() + "s of " + mark.name() + " mark");
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Equipment equipment = (Equipment) object;
        return (mark.equals(equipment.mark) && quantity == equipment.quantity);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + quantity;
        result = prime * result + (mark != null ? mark.hashCode() : 0);
        return result;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public Mark getMark() {
        return mark;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
