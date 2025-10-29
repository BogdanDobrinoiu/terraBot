package main.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Animal extends Entities {
    private String state;
    private double animalMass;
    private double waterMass;
    private final double intakeRate = 0.08;
}
