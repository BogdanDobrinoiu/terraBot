package main.processing;

import lombok.Getter;
import lombok.Setter;
import main.entities.*;

@Getter
@Setter
public class Cell {
    private Soil soil;
    private Water water;
    private Air air;
    private Animal animal;
    private Plant plant;
}
