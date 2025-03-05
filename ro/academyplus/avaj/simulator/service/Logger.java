package ro.academyplus.avaj.simulator.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Logger {
    private static PrintWriter writer;
    private static Path filepath = Paths.get("simulation.txt");
    
    static {
        try {
            Files.deleteIfExists(filepath);
            writer = new PrintWriter(new BufferedWriter(new FileWriter("simulation.txt", true)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteLog() {
        writer.close();
        try {
            Files.deleteIfExists(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logXnl(String message) {
        writer.print(message);
        writer.flush();
    }

    public static void log(String message) {
        writer.println(message);
        writer.flush();
    }

    public static void close() {
        writer.close();
    }
}