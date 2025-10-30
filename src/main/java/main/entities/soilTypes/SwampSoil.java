package main.entities.soilTypes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import main.entities.Soil;

@Getter
@Setter
public class SwampSoil extends Soil {
    private double waterLogging;

    @Override
    public double computeSoilQuality() {
        double soilQuality = getNitrogen() * 1.1 + getOrganicMatter() * 2.2 - waterLogging * 5;
        return normalizeScore(soilQuality);
    }

    @Override
    public ObjectNode toJson(ObjectMapper mapper) {
        ObjectNode node = super.toJson(mapper);
        node.put("waterLogging", waterLogging);
        return node;
    }
}
