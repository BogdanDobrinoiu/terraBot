package main.entities.soilTypes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import main.entities.Soil;

@Getter
@Setter
public class GrasslandSoil extends Soil {
    private double rootDensity;

    @Override
    public double computeSoilQuality() {
        double soilQuality = getNitrogen() * 1.3  + getOrganicMatter() * 1.5 + rootDensity * 0.8;
        return normalizeScore(soilQuality);
    }

    @Override
    public ObjectNode toJson(ObjectMapper mapper) {
        ObjectNode node = super.toJson(mapper);
        node.put("rootDensity", rootDensity);
        return node;
    }

    @Override
    public double calculateBlockProbability() {
        return ((50 - rootDensity) + getWaterRetention() * 0.5) / 75 * 100;
    }
}
