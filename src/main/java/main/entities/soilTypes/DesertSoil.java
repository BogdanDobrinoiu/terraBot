package main.entities.soilTypes;

import lombok.Getter;
import lombok.Setter;
import main.entities.Soil;

@Getter
@Setter
public class DesertSoil extends Soil {
    private double salinity;

    @Override
    public void computeSoilQuality() {
        setSoilQuality(this.getNitrogen() * 0.5  + this.getWaterRetention() * 0.3 + salinity * 2);
        this.setSoilQuality(normalizeScore(this.getSoilQuality()));
    }
}
