package main.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.AnimalInput;
import lombok.Getter;
import lombok.Setter;
import main.entities.animalTypes.*;

@Getter
@Setter
public class Animal extends Entities {
    private String state;
    private double animalMass;
    private double waterMass;
    private final double intakeRate = 0.08;
    private String name;
    private double mass;
    private String type;
    private double probability;
    private double attackProbability;

    public Animal(double probability) {
        this.probability = probability;
    }

    public double calculateAttackProbability() {
        return (100 - probability) / 10.0;
    }

    public static Animal createAnimal(AnimalInput animalInput) {
        Animal a;

        switch (animalInput.type) {
            case "Herbivores" -> a = new Herbivores();
            case "Carnivores" -> a = new Carnivores();
            case "Omnivores" -> a = new Omnivores();
            case "Detritivores" -> a = new Detritivores();
            case "Parasites" -> a = new Parasites();
            default -> a = null;
        }

        a.setType(animalInput.type);
        a.setName(animalInput.name);
        a.setMass(animalInput.mass);
        a.setAttackProbability(a.calculateAttackProbability());

        return a;
    }

    public ObjectNode toJson(ObjectMapper mapper) {
        ObjectNode node = mapper.createObjectNode();
        node.put("type", type);
        node.put("name", name);
        node.put("mass", mass);
        return node;
    }
}

