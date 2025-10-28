package main.entities.airTypes;

import lombok.Getter;
import lombok.Setter;
import main.entities.Air;

@Getter
@Setter
public abstract class Desert extends Air {
    private double dustParticles;

    @Override
    public void calculateQuality() {
        this.setAirQuality(this.getOxygenLevel() * 2 - dustParticles * 0.2 - this.getTemperature() * 0.3);
        this.setAirQuality(normalizeScore(this.getAirQuality()));
    }

    @Override
    public void calculateToxicityRate() {
        double toxicityAQ = 100 * (1 - this.getAirQuality() / 65);
        this.setToxicityRate(Math.round(toxicityAQ * 100.0) / 100.0);
    }
}
