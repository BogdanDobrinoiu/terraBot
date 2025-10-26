package main.entities;

import main.entities.flowerTypes.*;

public class Plant extends Entities {
    protected double O2; // oxygen level at start
    protected String age; // young, mature, old, dead
    private static final double young = 0.2;
    private static double mature = 0.5;
    private static final double old = -0.3;
    protected double oxygenLevel;
    protected int probability;

    public double getYoung() { return young; }
    public double getMature() { return mature; }
    public double getOld() { return old; }

    public double getOxygenLevel() { return oxygenLevel; }
    public void setOxygenLevel(double oxygenLevel) { this.oxygenLevel = oxygenLevel; }

    public Plant(double O2, String age, int  probability) {
        this.O2 = O2;
        this.age = age;
        this.probability = probability;
    }

    public void updateOxygen() {
        switch (this.age) {
            case "young" -> this.oxygenLevel += this.O2 + this.getYoung();
            case "mature" -> this.oxygenLevel += this.O2 + this.getMature();
            case "old" -> this.oxygenLevel += this.O2 + this.getOld();
        }
    }
}
