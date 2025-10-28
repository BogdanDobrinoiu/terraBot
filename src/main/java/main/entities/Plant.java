package main.entities;

import lombok.Getter;
import lombok.Setter;
import main.entities.flowerTypes.*;

@Getter
@Setter
public class Plant extends Entities {
    private double O2;
    private String age;

    @Getter
    private static final double young = 0.2;
    @Getter
    private static final double mature = 0.5;
    @Getter
    private static final double old = -0.3;
    private double oxygenLevel;
    protected int probability;

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
