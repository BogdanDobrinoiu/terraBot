package main.entities.animalTypes;

import lombok.Getter;
import lombok.Setter;
import main.entities.Animal;

@Getter
@Setter
public class Carnivores extends Animal {
    public Carnivores() {
        super(30);
    }
}
