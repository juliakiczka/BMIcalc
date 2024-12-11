package org.example;

import java.sql.Date;

public class BMIHistoryEntry {
    private double weight;
    private double height;
    private double bmi;
    private Date date;

    public BMIHistoryEntry(double weight, double height, double bmi, Date date) {
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public double getBmi() {
        return bmi;
    }

    public Date getDate() {
        return date;
    }
}
