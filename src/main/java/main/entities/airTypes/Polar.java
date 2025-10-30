package main.entities.airTypes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import main.entities.Air;

@Getter
@Setter
public class Polar extends Air {
    private double iceCrystalConcentration;

    @Override
    public double calculateQuality() {
        double airQuality = getOxygenLevel() * 2 + (100 - Math.abs(getTemperature())) - (iceCrystalConcentration * 0.05);
        return normalizeScore(airQuality);
    }

    @Override
    public void calculateToxicityRate() {
        double toxicityAQ = 100 * (1 - this.getAirQuality() / 142);
        this.setToxicityRate(Math.round(toxicityAQ * 100.0) / 100.0);
    }

    @Override
    public ObjectNode toJson(ObjectMapper mapper) {
        ObjectNode node = super.toJson(mapper);
        node.put("iceCrystalConcentration", iceCrystalConcentration);
        return node;
    }
}
