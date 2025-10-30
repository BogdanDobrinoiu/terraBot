package main.entities.soilTypes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.entities.Soil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForestSoil extends Soil {
    private double leafLitter;

    @Override
    public double computeSoilQuality() {
        double soilQuality = getNitrogen() * 1.2 + getOrganicMatter() * 2 + getWaterRetention() * 1.5 + leafLitter * 0.3;
        return normalizeScore(soilQuality);
    }

    @Override
    public ObjectNode toJson(ObjectMapper mapper) {
        ObjectNode node = super.toJson(mapper);
        node.put("leafLitter", leafLitter);
        return node;
    }

    @Override
    public double calculateBlockProbability() {
        return (getWaterRetention() * 0.6 + leafLitter * 0.4) / 80 * 100;
    }
}
