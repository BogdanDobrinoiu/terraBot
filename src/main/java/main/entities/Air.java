package main.entities;

import fileio.AirInput;
import lombok.Getter;
import lombok.Setter;
import main.entities.airTypes.*;

@Getter
@Setter
public abstract class Air extends Entities {
    private double humidity;
    private double temperature;
    private double oxygenLevel;
    private double airQuality;
    private double toxicityRate;
    private String name;
    private String type;
    private double mass;

    public abstract void calculateQuality();
    public abstract void calculateToxicityRate();

    public static Air createAir(AirInput airInput) {
        Air a = null;

        switch (a.type) {
            case "MountainAir":
                Montan montan = new Montan();
                montan.setAltitude(airInput.altitude);
                a = montan;
            case "TemperatureAir":
                Temperat temperat = new Temperat();
                temperat.setPollenLevel(airInput.pollenLevel);
                a = temperat;
            case "TropicalAir":
                Tropical tropical = new Tropical();
                tropical.setCo2Level(airInput.co2Level);
                a = tropical;
            case "PolarAir":
                Polar polar = new Polar();
                polar.setIceCrystalConcentration(airInput.iceCrystalConcentration);
                a = polar;
            case "DesertAir":
                Desert desert = new Desert();
                desert.setDustParticles(airInput.dustParticles);
                a = desert;
            default:
                a = null;
        }

        a.setType(a.type);
        a.setMass(airInput.mass);
        a.setHumidity(airInput.humidity);
        a.setTemperature(airInput.temperature);
        a.setOxygenLevel(airInput.oxygenLevel);
        a.setName(airInput.name);

        return a;
    }
}
