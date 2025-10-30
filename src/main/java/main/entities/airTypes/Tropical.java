package main.entities.airTypes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import main.entities.Air;

@Getter
@Setter
public class Tropical extends Air {
    private double co2Level;

    @Override
    public double calculateQuality() {
        double airQuality = getOxygenLevel() * 2 + getHumidity() * 0.5 - co2Level * 0.01;
        return normalizeScore(airQuality);
    }

    @Override
    public void calculateToxicityRate() {
        double toxicityAQ = 100 * (1 - getAirQuality() / 82);
        this.setToxicityRate(Math.round(toxicityAQ * 100.0) / 100.0);
    }

    @Override
    public ObjectNode toJson(ObjectMapper mapper) {
        ObjectNode node = super.toJson(mapper);
        node.put("co2Level", co2Level);
        return node;
    }
}
