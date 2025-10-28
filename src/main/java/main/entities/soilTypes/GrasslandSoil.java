package main.entities.soilTypes;

import lombok.Getter;
import lombok.Setter;
import main.entities.Soil;

@Getter
@Setter
public abstract class GrasslandSoil extends Soil {
    private double rootDensity;

    @Override
    public void computeSoilQuality() {
        setSoilQuality(this.getNitrogen() * 1.3  + this.getOrganicMatter() * 1.5 + rootDensity * 0.8);
        this.setSoilQuality(normalizeScore(this.getSoilQuality()));
    }
}
