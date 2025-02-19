package ro.academyplus.avaj.simulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Simulator {
        private static void readFile(String fileName) throws IOException {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr); // FileReader wrapper
            String line;
            while ((line = br.readLine()) != null) {

                System.out.println(line);
            }
            br.close();
            fr.close();
        }

        public static void main(String[] args) {
            if (args.length != 1) {
                System.err.println("Invalid input, one file is required!");
                System.exit(0);
            }
            String fileName = args[0];
            if (!fileName.endsWith(".txt")) {
                System.err.println("Invalid file type, program only accept .txt file!");
                System.exit(0);
            }
            try {
                readFile(fileName);

            } catch (IOException e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }
            
        }
}
