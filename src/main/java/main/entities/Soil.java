package main.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Soil {
    private double nitrogen;
    private double waterRetention;
    private double soilpH;
    private double organicMatter;
    private double soilQuality;

    public double normalizeScore(double score) {
        return Math.max(0, Math.min(100, score));
    }

    public abstract void computeSoilQuality();
}
