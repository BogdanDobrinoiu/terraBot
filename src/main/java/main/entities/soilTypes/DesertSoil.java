package main.entities.soilTypes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import main.entities.Soil;

@Getter
@Setter
public class DesertSoil extends Soil {
    private double salinity;

    @Override
    public double computeSoilQuality() {
        double soilQuality = getNitrogen() * 0.5  + getWaterRetention() * 0.3 + salinity * 2;
        return normalizeScore(soilQuality);
    }

    @Override
    public ObjectNode toJson(ObjectMapper mapper) {
        ObjectNode node = super.toJson(mapper);
        node.put("salinity", salinity);
        return node;
    }
}
