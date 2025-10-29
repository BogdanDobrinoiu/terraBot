package main.entities.airTypes;

import lombok.Getter;
import lombok.Setter;
import main.entities.Air;

@Getter
@Setter
public class Montan extends Air {
    private double altitude;

    @Override
    public void calculateQuality() {
        this.setAirQuality(this.getOxygenLevel() - altitude / 1000 * 0.5 - this.getHumidity() * 0.6);
        this.setAirQuality(normalizeScore(this.getAirQuality()));
    }

    @Override
    public void calculateToxicityRate() {
        double toxicityAQ = 100 * (1 - this.getAirQuality() / 78);
        this.setToxicityRate(Math.round(toxicityAQ * 100.0) / 100.0);
    }
}
