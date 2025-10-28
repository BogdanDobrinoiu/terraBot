package main.entities.soilTypes;

import lombok.Getter;
import lombok.Setter;
import main.entities.Soil;

@Getter
@Setter
public abstract class TundraSoil extends Soil {
    private double permafrostDepth;

    @Override
    public void computeSoilQuality() {
        setSoilQuality(this.getNitrogen() * 0.7  + this.getOrganicMatter() * 0.5 + permafrostDepth * 1.5);
        this.setSoilQuality(normalizeScore(this.getSoilQuality()));
    }
}
