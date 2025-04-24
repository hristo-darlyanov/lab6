package common.managers;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import common.model.*;
/**
 * File manager
 * Manages file operations
 */
public class FileManager {
    final static String defaultProjectPath = getProjectRootDirectory();
    final static String defaultCommandScriptPath = defaultProjectPath + "/commandScripts/";

    /**
     * Get project root directory
     * @return Project root directory
     */
    public static String getProjectRootDirectory() {
        // Saves to resources folder
        return System.getProperty("user.dir");
    }
    
    /**
     * Load collection from file
     * @param filePath Path to file
     * @return Loaded collection
     */
    public static ArrayDeque<Movie> loadCollection(String filePath) {
        File file = new File(defaultProjectPath + "/" + filePath);
        ArrayDeque<Movie> loadedMovies = new ArrayDeque<>();
        if (!file.exists()) {
            System.out.println("File not found: " + file.getAbsolutePath());
            return loadedMovies;
        }
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            JAXBContext context = JAXBContext.newInstance(
                MoviesWrapper.class, Movie.class, Coordinates.class, Person.class, Location.class
            );
            Unmarshaller unmarshaller = context.createUnmarshaller();
            MoviesWrapper wrapper = (MoviesWrapper) unmarshaller.unmarshal(bis);
            if (wrapper != null && wrapper.getMovies() != null) {
                loadedMovies.addAll(wrapper.getMovies());
            }
        } catch (Exception e) {
            System.out.println("Error loading collection: " + e.getMessage());
            e.printStackTrace();
        }
        return loadedMovies;
    }

    /**
     * Save collection to file
     * @param filePath Path to file
     * @param movies Collection to save
     */
    public static void saveCollection(String filePath, ArrayDeque<Movie> movies) {
        File file = new File(filePath);
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file))) {
            JAXBContext context = JAXBContext.newInstance(
                MoviesWrapper.class, Movie.class, Coordinates.class, Person.class, Location.class
            );
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            List<Movie> moviesList = new ArrayList<>(movies);
            MoviesWrapper wrapper = new MoviesWrapper();
            wrapper.setMovies(moviesList);

            StringWriter sw = new StringWriter();
            marshaller.marshal(wrapper, sw);

            writer.write(sw.toString());
        } catch (Exception e) {
            System.out.println("Error saving collection: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Load command script from file
     * @param filePath Path to file
     * @return Loaded command script
     */
    public static Map<String, String> loadCommandScript(String filePath) {
        filePath = defaultCommandScriptPath + "/" + filePath;
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("File not found: " + file.getAbsolutePath());
            return null;
        }

        Map<String, String> commands = new LinkedHashMap<>();

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
             BufferedReader reader = new BufferedReader(new InputStreamReader(bis))) {

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] tokens = line.split("\\s+", 2);
                String commandName = tokens[0];
                String args = tokens.length > 1 ? tokens[1].trim() : "";

                commands.put(commandName, args);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }

        return commands;
    }
}
