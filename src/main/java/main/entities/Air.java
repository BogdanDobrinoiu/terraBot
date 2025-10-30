package main.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

    public abstract double calculateQuality();
    public abstract void calculateToxicityRate();

    public static Air createAir(AirInput airInput) {
        Air a = null;

        switch (airInput.type) {
            case "MountainAir":
                Montan montan = new Montan();
                montan.setAltitude(airInput.altitude);
                a = montan;
                break;
            case "TemperateAir":
                Temperat temperat = new Temperat();
                temperat.setPollenLevel(airInput.pollenLevel);
                a = temperat;
                break;
            case "TropicalAir":
                Tropical tropical = new Tropical();
                tropical.setCo2Level(airInput.co2Level);
                a = tropical;
                break;
            case "PolarAir":
                Polar polar = new Polar();
                polar.setIceCrystalConcentration(airInput.iceCrystalConcentration);
                a = polar;
                break;
            case "DesertAir":
                Desert desert = new Desert();
                desert.setDustParticles(airInput.dustParticles);
                a = desert;
                break;
            default:
                a = null;
                break;
        }

        if (a != null) {
            a.setType(airInput.type);
            a.setMass(airInput.mass);
            a.setHumidity(airInput.humidity);
            a.setTemperature(airInput.temperature);
            a.setOxygenLevel(airInput.oxygenLevel);
            a.setName(airInput.name);
            a.setAirQuality(a.calculateQuality());
        }

        return a;
    }

    public ObjectNode toJson(ObjectMapper mapper) {
        ObjectNode node = mapper.createObjectNode();
        node.put("type", type);
        node.put("name", name);
        node.put("mass", mass);
        node.put("humidity", humidity);
        node.put("temperature", temperature);
        node.put("oxygenLevel", oxygenLevel);
        node.put("airQuality", airQuality);
        return node;
    }
}
