package main.entities.airTypes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import main.entities.Air;

@Getter
@Setter
public class Desert extends Air {
    private double dustParticles;

    @Override
    public double calculateQuality() {
        double airQuality = getOxygenLevel() * 2 - dustParticles * 0.2 - getTemperature() * 0.3;
        return normalizeScore(airQuality);

    }

    @Override
    public void calculateToxicityRate() {
        double toxicityAQ = 100 * (1 - getAirQuality() / 65);
        this.setToxicityRate(Math.round(toxicityAQ * 100.0) / 100.0);
    }

    @Override
    public ObjectNode toJson(ObjectMapper mapper) {
        ObjectNode node = super.toJson(mapper);
        node.put("dustParticles", dustParticles);
        return node;
    }
}
