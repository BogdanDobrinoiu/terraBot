package main.entities.airTypes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import main.entities.Air;

@Getter
@Setter
public class Temperat extends Air {
    private double pollenLevel;

    @Override
    public double calculateQuality() {
        double airQuality = getOxygenLevel() * 2 + getHumidity() * 0.7 - pollenLevel * 0.1;
        return normalizeScore(airQuality);
    }

    @Override
    public void calculateToxicityRate() {
        double toxicityAQ = 100 * (1 - getAirQuality() / 84);
        this.setToxicityRate(Math.round(toxicityAQ * 100.0) / 100.0);
    }

    @Override
    public ObjectNode toJson(ObjectMapper mapper) {
        ObjectNode node = super.toJson(mapper);
        node.put("pollenLevel", pollenLevel);
        return node;
    }
}
