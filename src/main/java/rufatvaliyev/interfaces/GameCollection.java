package rufatvaliyev.interfaces;

import rufatvaliyev.entities.BoardGames;
import rufatvaliyev.entities.Game;

import java.util.List;

public interface GameCollection {
    void addGame(Game game);
    Game findById(String id);
    List<Game> findByPrice(double price);
    List<BoardGames> findByNumPlayers(int numPlayers);
    void removeById(String id);
    void updateGame(String id, Game newGame);
    void printStatistics();
    void printAllGames();
}
