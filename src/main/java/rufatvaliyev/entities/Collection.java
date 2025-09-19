package rufatvaliyev.entities;

import rufatvaliyev.interfaces.GameCollection;

import java.util.*;
import java.util.stream.Collectors;

public class Collection implements GameCollection {
    private List<Game> games = new ArrayList<>();

    // Add a new game to the collection (must have a unique ID)
    @Override
    public void addGame(Game game) {
        if (games.stream().anyMatch(g -> g.getId().equals(game.getId()))) {
            throw new IllegalArgumentException("A game with this ID already exists!");
        }
        games.add(game);
    }

    // Search a game by its ID
    @Override
    public Game findById(String id) {
        return games.stream()
                .filter(g -> g.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No game found with this ID!"));
    }

    // Search for all games cheaper than the given price
    @Override
    public List<Game> findByPrice(double price) {
        return games.stream()
                .filter(g -> g.getPrice() < price)
                .collect(Collectors.toList());
    }

    // Search board games by number of players
    @Override
    public List<BoardGames> findByNumPlayers(int numPlayers) {
        return games.stream()
                .filter(g -> g instanceof BoardGames)
                .map(g -> (BoardGames) g)
                .filter(g -> g.getNumPlayers() == numPlayers)
                .collect(Collectors.toList());
    }

    // Remove a game by its ID
    @Override
    public void removeById(String id) {
        boolean removed = games.removeIf(g -> g.getId().equals(id));
        if (!removed) {
            throw new NoSuchElementException("No game found with this ID!");
        }
    }

    // Update an existing game by replacing it with a new one
    @Override
    public void updateGame(String id, Game newGame) {
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getId().equals(id)) {
                games.set(i, newGame);
                return;
            }
        }
        throw new NoSuchElementException("No game found with this ID!");
    }

    // Print statistics: number of each type, max price, average price
    @Override
    public void printStatistics() {
        long videoCount = games.stream().filter(g -> g instanceof VideoGames).count();
        long boardCount = games.stream().filter(g -> g instanceof BoardGames).count();
        Optional<Game> maxPriceGame = games.stream().max(Comparator.comparingDouble(Game::getPrice));
        double avgPrice = games.stream().mapToDouble(Game::getPrice).average().orElse(0);

        System.out.println("Number of video games: " + videoCount);
        System.out.println("Number of board games: " + boardCount);
        maxPriceGame.ifPresent(game -> System.out.println("Most expensive game: " + game));
        System.out.println("Average price: " + avgPrice);
    }

    // Print all games in the collection
    @Override
    public void printAllGames() {
        games.forEach(System.out::println);
    }
}
