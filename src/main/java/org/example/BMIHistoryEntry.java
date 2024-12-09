package org.example;

import java.sql.Timestamp;

public class BMIHistoryEntry {
    private double weight;
    private double height;
    private double bmi;
    private Timestamp date;

    public BMIHistoryEntry(double weight, double height, double bmi, Timestamp date) {
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

    public Timestamp getDate() {
        return date;
    }
}
