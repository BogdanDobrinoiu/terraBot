package main.entities.airTypes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import main.entities.Air;

@Getter
@Setter
public class Montan extends Air {
    private double altitude;

    @Override
    public double calculateQuality() {
        double oxygenFactor = getOxygenLevel() - altitude / 1000 * 0.5;
        double airQuality = oxygenFactor * 2 + getHumidity() * 0.6;
        return normalizeScore(airQuality);
    }

    @Override
    public void calculateToxicityRate() {
        double toxicityAQ = 100 * (1 - getAirQuality() / 78);
        this.setToxicityRate(Math.round(toxicityAQ * 100.0) / 100.0);
    }
    @Override
    public ObjectNode toJson(ObjectMapper mapper) {
        ObjectNode node = super.toJson(mapper);
        node.put("altitude", altitude);
        return node;
    }
}
