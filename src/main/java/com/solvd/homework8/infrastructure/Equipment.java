package com.solvd.homework8.infrastructure;

public abstract class Equipment {

    private String mark;
    private Integer quantity;

    public Equipment(String mark, Integer quantity) {
        this.mark = mark;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return ("There are " + quantity + " " + this.getClass().getSimpleName().toLowerCase() + "s of " + mark + " mark");
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

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
