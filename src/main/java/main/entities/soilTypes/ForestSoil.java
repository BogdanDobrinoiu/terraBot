package main.entities.soilTypes;

import main.entities.Soil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForestSoil extends Soil {
    private double leafLitter;

    @Override
    public void computeSoilQuality() {
        setSoilQuality(this.getNitrogen() * 1.2 + this.getOrganicMatter() * 2 + this.getWaterRetention() * 1.5 + leafLitter * 0.3);
        this.setSoilQuality(normalizeScore(this.getSoilQuality()));
    }
}
