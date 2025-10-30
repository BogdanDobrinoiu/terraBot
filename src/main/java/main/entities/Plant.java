package main.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.PlantInput;
import lombok.Getter;
import lombok.Setter;
import main.entities.flowerTypes.*;

@Getter
@Setter
public class Plant extends Entities {
    private double O2;
    private String age;
    private final double young = 0.2;
    private final double mature = 0.5;
    private final double old = -0.3;
    private double oxygenLevel;
    protected double probability;
    private double mass;
    private String name;
    private String type;
    private double hangProbability;

    public double calculateHangProbability() {
        return probability / 100;
    }

    public Plant(double O2, String age, int  probability) {
        this.O2 = O2;
        this.age = age;
        this.probability = probability;
    }

    public void updateOxygen() {
        switch (this.age) {
            case "young" -> this.oxygenLevel += this.O2 + this.getYoung();
            case "mature" -> this.oxygenLevel += this.O2 + this.getMature();
            case "old" -> this.oxygenLevel += this.O2 + this.getOld();
        }
    }

    public static Plant createPlant(PlantInput plantInput) {
        Plant p;

        switch (plantInput.type) {
            case "FloweringPlants" -> p = new FloweringPlants();
            case "Ferns" ->  p = new Ferns();
            case "Algae" ->  p = new Algae();
            case "Mosses" ->  p = new Mosses();
            case "Gymnosperms" ->  p = new Gymnosperms();
            default -> p =  null;
        }

        p.setType(plantInput.type);
        p.setName(plantInput.name);
        p.setMass(plantInput.mass);
        p.setHangProbability(p.calculateHangProbability());

        return p;
    }

    public ObjectNode toJson(ObjectMapper mapper) {
        ObjectNode node = mapper.createObjectNode();
        node.put("type", type);
        node.put("name", name);
        node.put("mass", mass);
        return node;
    }
}
