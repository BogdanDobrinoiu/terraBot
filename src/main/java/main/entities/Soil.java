package main.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.SoilInput;
import lombok.Getter;
import lombok.Setter;
import main.entities.soilTypes.*;

@Getter
@Setter
public abstract class Soil extends Entities {
    private double nitrogen;
    private double waterRetention;
    private double soilpH;
    private double organicMatter;
    private double soilQuality;
    private String name;
    private double mass;
    private String type;

    public abstract double computeSoilQuality();

    public static Soil createSoil (SoilInput soilInput) {
        Soil s = null;

        switch (soilInput.type) {
            case "ForestSoil":
                ForestSoil forestSoil = new ForestSoil();
                forestSoil.setLeafLitter(soilInput.leafLitter);
                s = forestSoil;
                break;
            case "SwampSoil":
                SwampSoil swampSoil = new SwampSoil();
                swampSoil.setWaterLogging(soilInput.waterLogging);
                s = swampSoil;
                break;
            case "TundraSoil":
                TundraSoil tundraSoil = new TundraSoil();
                tundraSoil.setPermafrostDepth(soilInput.permafrostDepth);
                s = tundraSoil;
                break;
            case "DesertSoil":
                DesertSoil desertSoil = new DesertSoil();
                desertSoil.setSalinity(soilInput.salinity);
                s = desertSoil;
                break;
            case "GrasslandSoil":
                GrasslandSoil grasslandSoil = new GrasslandSoil();
                grasslandSoil.setRootDensity(soilInput.rootDensity);
                s = grasslandSoil;
                break;
            default:
                s = null;
                break;
        }

        if (s != null) {
            s.setType(soilInput.type);
            s.setName(soilInput.name);
            s.setMass(soilInput.mass);
            s.setNitrogen(soilInput.nitrogen);
            s.setWaterRetention(soilInput.waterRetention);
            s.setSoilpH(soilInput.soilpH);
            s.setOrganicMatter(soilInput.organicMatter);
            s.setSoilQuality(s.computeSoilQuality());
        }

        return s;
    }

    public ObjectNode toJson(ObjectMapper mapper) {
        ObjectNode soilNode = mapper.createObjectNode();
        soilNode.put("type", getType());
        soilNode.put("name", getName());
        soilNode.put("mass", getMass());
        soilNode.put("nitrogen", getNitrogen());
        soilNode.put("waterRetention", getWaterRetention());
        soilNode.put("soilpH", getSoilpH());
        soilNode.put("organicMatter",  getOrganicMatter());
        soilNode.put("soilQuality", getSoilQuality());
        return soilNode;
    }
}
