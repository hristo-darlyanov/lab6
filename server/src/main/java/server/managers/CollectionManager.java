package server.managers;

import common.managers.FileManager;
import common.model.Movie;
import common.utilities.ProjectInfo;

import java.time.LocalDateTime;
import java.util.ArrayDeque;

/**
 * Collection manager
 * Manages the collection
 */
public class CollectionManager {
    private final LocalDateTime initializationDate;
    private ArrayDeque<Movie> collection;

    public CollectionManager() {
        this.collection = new ArrayDeque<>();
        this.initializationDate = LocalDateTime.now();
    }

    public void addMoviesFromFile(String fileName) {
        ArrayDeque<Movie> loadedCollection = FileManager.loadCollection("/" + fileName);

        // Iterate through the collections
        for (Movie movie : loadedCollection) {
            // Check if id for current movie is unique by comparing to other movies
            for (Movie otherMovie : loadedCollection) {
                if (movie != otherMovie && movie.getId().equals(otherMovie.getId())) {
                    movie.generateID();
                    break;
                }
            }

            Movie.validateMovie(movie);
            collection.add(movie);
        }
    }

    /**
     * @return Collection
     */
    public ArrayDeque<Movie> getCollection() {
        // First sort the return
        collection = new ArrayDeque<>(collection.stream().sorted().toList());
        return collection;
    }

    /**
     * @return Initialization date
     */
    public LocalDateTime getInitializationDate() {
        return initializationDate;
    }

    /**
     * Adds a movie to the collection
     *
     * @param movie Movie to add
     */
    public void add(Movie movie) {
        collection.add(movie);
        collection = new ArrayDeque<>(collection.stream().sorted().toList());
        save();
    }

    public void save() {
        collection = new ArrayDeque<>(collection.stream().sorted().toList());
        try {
            FileManager.saveCollection(ProjectInfo.SAVE_FILE, collection);
        } catch (Exception e) {
            System.out.println("Failed to save collection: " + e.getMessage());
        }
    }
}
