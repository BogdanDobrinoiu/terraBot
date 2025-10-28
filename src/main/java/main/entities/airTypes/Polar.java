package main.entities.airTypes;

import lombok.Getter;
import lombok.Setter;
import main.entities.Air;

@Getter
@Setter
public class Polar extends Air {
    private double iceCrystalConcentration;

    @Override
    public void calculateQuality() {
        this.setAirQuality(this.getOxygenLevel() * 2 + (100 - Math.abs(this.getTemperature())) - (iceCrystalConcentration * 0.05));
        this.setAirQuality(normalizeScore(this.getAirQuality()));
    }

    @Override
    public void calculateToxicityRate() {
        double toxicityAQ = 100 * (1 - this.getAirQuality() / 142);
        this.setToxicityRate(Math.round(toxicityAQ * 100.0) / 100.0);
    }
}
