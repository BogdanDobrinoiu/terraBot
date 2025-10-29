package main.entities;

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

    public abstract void computeSoilQuality();

    public static Soil createSoil (SoilInput soilInput) {
        Soil s = null;

        switch (soilInput.type) {
            case "ForestSoil":
                ForestSoil forestSoil = new ForestSoil();
                forestSoil.setLeafLitter(soilInput.leafLitter);
                s = forestSoil;
            case "SwapSoil":
                SwampSoil swampSoil = new SwampSoil();
                swampSoil.setWaterLogging(soilInput.waterLogging);
                s = swampSoil;
            case "TundraSoil":
                TundraSoil tundraSoil = new TundraSoil();
                tundraSoil.setPermafrostDepth(soilInput.permafrostDepth);
                s = tundraSoil;
            case "DesertSoil":
                DesertSoil desertSoil = new DesertSoil();
                desertSoil.setSalinity(soilInput.salinity);
                s = desertSoil;
            case "GrasslandSoil":
                GrasslandSoil grasslandSoil = new GrasslandSoil();
                grasslandSoil.setRootDensity(soilInput.rootDensity);
                s = grasslandSoil;
            default:
                s = null;
        }

        s.setType(soilInput.type);
        s.setName(soilInput.name);
        s.setMass(soilInput.mass);
        s.setNitrogen(soilInput.nitrogen);
        s.setWaterRetention(soilInput.waterRetention);
        s.setSoilpH(soilInput.soilpH);
        s.setOrganicMatter(soilInput.organicMatter);

        return s;
    }
}
