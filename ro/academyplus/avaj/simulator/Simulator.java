package ro.academyplus.avaj.simulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ro.academyplus.avaj.simulator.customexceptions.NotTextFileException;
import ro.academyplus.avaj.simulator.customexceptions.InvalidCoordinatesException;
import ro.academyplus.avaj.simulator.customexceptions.InvalidInputException;
import ro.academyplus.avaj.simulator.customexceptions.InvalidLineFormatException;
import ro.academyplus.avaj.simulator.customexceptions.NegativeRunTimeException;
import ro.academyplus.avaj.simulator.flyable.Coordinates;
import ro.academyplus.avaj.simulator.flyable.Flyable;
import ro.academyplus.avaj.simulator.service.AircraftFactory;
import ro.academyplus.avaj.simulator.service.Logger;
import ro.academyplus.avaj.simulator.tower.WeatherTower;

public class Simulator {
    private static AircraftFactory factory = AircraftFactory.getInstance();
    private static WeatherTower towerSouth = new WeatherTower();
    private static List<Flyable> flyables = new ArrayList<>();
    private static int numberRun;

    
    private static void isValidInput(String[] args) {
        if (args.length != 1)
            throw new InvalidInputException("Invalid input: One file is required!");
    }
    
    private static void isTextFile(String fileName) {
        if (!fileName.endsWith(".txt"))
            throw new NotTextFileException("Invalid file type: Program only accept .txt file!");
    }

    private static void isPositiveRunTime(int nb) {
        if (nb <= 0)
            throw new NegativeRunTimeException("Invalid simulation run times: Must be positive!");
    }

    private static void isValidCoordinates(int longitude, int latitude, int height) {
        if (longitude < 0 || latitude < 0 || height < 0)
            throw new InvalidCoordinatesException("longitude/latitude/height must be positive numbers !");
        if (height > 100)
            throw new InvalidCoordinatesException("Invalid height: Range: 0 - 100 only!");
    }

    private static void isValidFormat(String[] inputLine) {
        if (inputLine.length != 5)
            throw new InvalidLineFormatException("Invalid format: Use: TYPE NAME LONGITUDE LATITUDE HEIGHT !");
    }

    private static void parse(String line) {
        String[] words = line.split("\\s+");
        
        int longitude = 0,latitude = 0, height = 0;
        try {
            isValidFormat(words);
            longitude = Integer.parseInt(words[2]);
            latitude = Integer.parseInt(words[3]);
            height = Integer.parseInt(words[4]);
            isValidCoordinates(longitude, latitude, height);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid data type for longitude/latitude/height !");
            Logger.deleteLog();
            System.exit(0);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
            Logger.deleteLog();
            System.exit(0);
        }

        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        Flyable newFlyable = factory.newAircraft(words[0], words[1], coordinates);
        if (newFlyable == null) {
            Logger.log("Tower says: Invalid Aircraft " + words[0] + "#" + words[1] + " not registered to weather tower!");
            return ;
        }
        flyables.add(newFlyable);
        newFlyable.registerTower(towerSouth);
    }

    private static void readFile(String fileName) throws IOException {
        // Method level exception

        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr); // FileReader wrapper
        String line;
        int lineCounter = 0;
        
        while ((line = br.readLine()) != null) {
            if (lineCounter == 0) {
                try {
                    numberRun = Integer.parseInt(line);
                    isPositiveRunTime(numberRun);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Simulation run times must be a valid integer!");
                    Logger.deleteLog();
                    System.exit(0);
                } catch (RuntimeException e) {
                    System.out.println("Error: " + e.getMessage());
                    Logger.deleteLog();
                    System.exit(0);                }
            } else {
                parse(line);
            }
            lineCounter++;
        }
        br.close();
        fr.close();
        if (lineCounter <= 1)
            Logger.log("Error: No input found!");
    }

    private static void simulate() {
        while (numberRun != 0) {
            towerSouth.changeWeather();
            numberRun--;
        }
    }

    public static void main(String[] args) {
        String fileName = args[0];
        try {
            isValidInput(args);
            isTextFile(fileName);
            readFile(fileName);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        simulate();
        Logger.close();
    }
}