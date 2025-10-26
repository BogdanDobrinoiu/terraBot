package main.processing;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fileio.InputLoader;
import fileio.SimulationInput;
import fileio.CommandInput;
import fileio.TerritorySectionParamsInput;
import java.util.ArrayList;
import main.entities.Entities;
import main.entities.Cell;

public class Processing {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public ArrayNode run(InputLoader inputLoader) {
        ArrayNode output = MAPPER.createArrayNode();

        ArrayList<SimulationInput> sims = inputLoader.getSimulations();
        ArrayList<CommandInput> commands = inputLoader.getCommands();

        SimulationInput sim = sims.get(0);

        for (CommandInput command : commands) {
            ObjectNode commandNode = MAPPER.createObjectNode();
            commandNode.put("command", command.command);
            if (command.command.equals("startSimulation")) {
                commandNode.put("message", "Simulation has started.");

                String[] parts = sim.territoryDim.split("x");
                int n = Integer.parseInt(parts[1]);
                int m = Integer.parseInt(parts[2]);
                Cell[][] territory = new Cell[n][m];


            } else if(command.command.equals("endSimulation")) {
                commandNode.put("message", "Simulation has ended.");
            }
            commandNode.put("timestamp", command.timestamp);
            output.add(commandNode);
        }

        return output;
    }
}
