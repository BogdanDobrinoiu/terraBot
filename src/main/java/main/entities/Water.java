package main.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.WaterInput;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Water {
    private double salinity;
    private double pH;
    private double purity;
    private double turbidity;
    private double contaminantIndex;
    private boolean isFrozen;
    private String name;
    private String type;
    private double mass;

    public double waterQuality() {
        double purity_score = this.purity / 100;
        double pH_score = 1 - Math.abs(this.pH - 7.5) / 7.5;
        double salinity_score = 1 - (salinity / 350);
        double turbidity_score = 1 - (turbidity / 150);
        double contaminant_score = 1 - (contaminantIndex / 100);
        double frozen_score = isFrozen ? 0 : 1;

        return (0.3 * purity_score + 0.2 * pH_score + 0.15 * salinity_score +
                0.1 * turbidity_score + 0.15 *  contaminant_score + 0.2 * frozen_score) * 100;
    }

    public static Water createWater(WaterInput waterInput) {
        Water w = new Water();

        w.setType(waterInput.type);
        w.setName(waterInput.name);
        w.setPurity(waterInput.purity);
        w.setSalinity(waterInput.salinity);
        w.setTurbidity(waterInput.turbidity);
        w.setContaminantIndex(waterInput.contaminantIndex);
        w.setFrozen(waterInput.isFrozen);
        w.setPH(waterInput.pH);
        w.setMass(waterInput.mass);

        return w;
    }

    public ObjectNode toJson(ObjectMapper mapper) {
        ObjectNode node = mapper.createObjectNode();
        node.put("type", type);
        node.put("name", name);
        node.put("mass", mass);
        node.put("purity", purity);
        node.put("salinity", salinity);
        node.put("turbidity", turbidity);
        node.put("contaminantIndex", contaminantIndex);
        node.put("pH", pH);
        node.put("isFrozen", isFrozen);
        return node;
    }
}
