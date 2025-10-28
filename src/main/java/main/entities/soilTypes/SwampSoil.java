package main.entities.soilTypes;

import lombok.Getter;
import lombok.Setter;
import main.entities.Soil;

@Getter
@Setter
public abstract class SwampSoil extends Soil {
    private double waterLogging;

    @Override
    public void computeSoilQuality() {
        setSoilQuality(this.getNitrogen() * 1.1 + this.getOrganicMatter() * 2.2 + waterLogging * 5);
        this.setSoilQuality(normalizeScore(this.getSoilQuality()));
    }
}
