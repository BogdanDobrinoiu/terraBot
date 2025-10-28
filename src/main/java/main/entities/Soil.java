package main.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Soil extends Entities {
    private double nitrogen;
    private double waterRetention;
    private double soilpH;
    private double organicMatter;
    private double soilQuality;

    public abstract void computeSoilQuality();
}
