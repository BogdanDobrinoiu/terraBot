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

    private Cell[][] generateMap(SimulationInput sim) {
        TerritorySectionParamsInput params = sim.territorySectionParams;

        String[] parts = sim.territoryDim.split("x");
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
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

    private ObjectNode printEnvConditions(Cell cell) {
        ObjectNode output = MAPPER.createObjectNode();

        if (cell.getSoil() != null) {
            output.set("soil", cell.getSoil().toJson(MAPPER));
        }
        if (cell.getPlant() != null) {
            output.set("plants", cell.getPlant().toJson(MAPPER));
        }
        if (cell.getAnimal() != null) {
            output.set("animals", cell.getAnimal().toJson(MAPPER));
        }
        if (cell.getWater() != null) {
            output.set("water", cell.getWater().toJson(MAPPER));
        }
        if (cell.getAir() != null) {
            output.set("air", cell.getAir().toJson(MAPPER));
        }

        return output;
    }

    private ArrayNode printMap(Cell[][] map) {
        ArrayNode output = MAPPER.createArrayNode();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Cell cell = map[j][i];
                ObjectNode node = MAPPER.createObjectNode();

                ArrayNode section =  MAPPER.createArrayNode();
                section.add(j);
                section.add(i);
                node.set("section", section);

                int totalObj = 0;
                if (cell.getPlant() != null) totalObj++;
                if (cell.getAnimal() != null) totalObj++;
                if (cell.getWater() != null) totalObj++;
                node.put("totalNrOfObjects", totalObj);

                node.put("airQuality", cell.getAir().scoreInterpreter(cell.getAir()));
                node.put("soilQuality", cell.getSoil().scoreInterpreter(cell.getSoil()));

                output.add(node);
            }
        }

        return output;
    }

    public ArrayNode run(InputLoader inputLoader) {
        ArrayNode output = MAPPER.createArrayNode();

        ArrayList<SimulationInput> sims = inputLoader.getSimulations();
        ArrayList<CommandInput> commands = inputLoader.getCommands();

        SimulationInput sim = sims.get(0);
        Cell[][] territory = null;

        boolean simulationStartFlag = false;

        for (CommandInput command : commands) {
            ObjectNode commandNode = MAPPER.createObjectNode();
            commandNode.put("command", command.command);

            switch (command.command) {
                case "startSimulation":
                    commandNode.put("message", "Simulation has started.");
                    territory = generateMap(sim);
                    simulationStartFlag = true;
                    break;

                case "printEnvConditions":
                    if (!simulationStartFlag) {
                        commandNode.put("message", "ERROR: Simulation not started. Cannot perform action");
                        break;
                    }
                    if (territory != null) {
                        commandNode.set("output", printEnvConditions(territory[0][0]));
                    }
                    break;

                case "printMap":
                    if (!simulationStartFlag) {
                        commandNode.put("message", "ERROR: Simulation not started. Cannot perform action");
                        break;
                    }
                    if (territory != null) {
                        commandNode.set("output", printMap(territory));
                    }
                    break;

                case "endSimulation":
                    simulationStartFlag = false;
                    commandNode.put("message", "Simulation has ended.");
                    break;

                default:
                    System.out.println("Unknown command: " + command.command);
                    break;
            }

            commandNode.put("timestamp", command.timestamp);
            output.add(commandNode);
        }

        return output;
    }
}
