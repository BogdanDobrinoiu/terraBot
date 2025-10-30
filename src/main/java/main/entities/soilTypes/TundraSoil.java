package main.entities.soilTypes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import main.entities.Soil;

@Getter
@Setter
public class TundraSoil extends Soil {
    private double permafrostDepth;

    @Override
    public double computeSoilQuality() {
        double soilQuality = getNitrogen() * 0.7  + getOrganicMatter() * 0.5 + permafrostDepth * 1.5;
        return normalizeScore(soilQuality);
    }

    @Override
    public ObjectNode toJson(ObjectMapper mapper) {
        ObjectNode node = super.toJson(mapper);
        node.put("permafrostDepth", permafrostDepth);
        return node;
    }

    @Override
    public double calculateBlockProbability() {
        return (50 - permafrostDepth) / 50 * 100;
    }
}
