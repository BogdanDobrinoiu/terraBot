package main.entities;

public class Plant extends Entities {
    protected double O2;
    protected String age;
    private static final double young = 0.2;
    private static double mature = 0.5;
    private static final double old = -0.3;

    public double getYoung() { return young; }
    public double getMature() { return mature; }
    public double getOld() { return old; }

    public Plant(double O2, String age) {
        this.O2 = O2;
        this.age = age;
    }
}
