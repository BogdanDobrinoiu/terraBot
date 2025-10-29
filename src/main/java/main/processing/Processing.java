package main.processing;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fileio.*;

import java.util.ArrayList;
import main.entities.*;
import main.entities.soilTypes.*;

public class Processing {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public Cell[][] generateMap(SimulationInput sim) {
        TerritorySectionParamsInput params = sim.territorySectionParams;

        String[] parts = sim.territoryDim.split("x");
        int n = Integer.parseInt(parts[1]);
        int m = Integer.parseInt(parts[2]);
        Cell[][] territory = new Cell[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                territory[i][j] = new Cell();
            }
        }

        for (SoilInput soil : params.soil) {
            for (PairInput pair : soil.sections) {
                int x = pair.x;
                int y = pair.y;

                Soil s = Soil.createSoil(soil);
                territory[x][y].setSoil(s);
            }
        }

        for (PlantInput plant :  params.plants) {
            for (PairInput pair : plant.sections) {
                int x = pair.x;
                int y = pair.y;

                Plant p = Plant.createPlant(plant);
                territory[x][y].setPlant(p);
            }
        }

        for (AnimalInput animal :  params.animals) {
            for (PairInput pair : animal.sections) {
                int x = pair.x;
                int y = pair.y;

                Animal a = Animal.createAnimal(animal);
                territory[x][y].setAnimal(a);
            }
        }

        for (WaterInput water :  params.water) {
            for (PairInput pair : water.sections) {
                int x = pair.x;
                int y = pair.y;

                Water w = Water.createWater(water);
                territory[x][y].setWater(w);
            }
        }

        for (AirInput air :  params.air) {
            for (PairInput pair : air.sections) {
                int x = pair.x;
                int y = pair.y;

                Air a = Air.createAir(air);
                territory[x][y].setAir(a);
            }
        }

        return territory;
    }

    public ArrayNode run(InputLoader inputLoader) {
        ArrayNode output = MAPPER.createArrayNode();

        ArrayList<SimulationInput> sims = inputLoader.getSimulations();
        ArrayList<CommandInput> commands = inputLoader.getCommands();

        SimulationInput sim = sims.get(0);

        for (CommandInput command : commands) {
            ObjectNode commandNode = MAPPER.createObjectNode();
            commandNode.put("command", command.command);

            switch (command.command) {
                case "startSimulation":
                    commandNode.put("message", "Simulation has started.");
                    Cell[][] territory = generateMap(sim);
                case "endSimulation":
                    commandNode.put("message", "Simulation has ended.");
                case "startPlant":
                    commandNode.put("message", "Plant has started.");
                default:
                    System.out.println("Impossible");
            }

            commandNode.put("timestamp", command.timestamp);
            output.add(commandNode);
        }

        return output;
    }
}
