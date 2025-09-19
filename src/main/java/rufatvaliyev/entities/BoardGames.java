package rufatvaliyev.entities;

public class BoardGames extends Game {
    private int numPlayers;
    private int durationMinutes;

    public BoardGames(String id, String title, int year, double price, int numPlayers, int durationMinutes) {
        super(id, title, year, price);
        if (numPlayers < 2 || numPlayers > 10) {
            throw new IllegalArgumentException("Number of players must be between 2 and 10!");
        }
        this.numPlayers = numPlayers;
        this.durationMinutes = durationMinutes;
    }

    public BoardGames(String id, String title, int year, double price) {
        super(id, title, year, price);
    }

    public int getNumPlayers() { return numPlayers; }
    public int getDurationMinutes() { return durationMinutes; }

    @Override
    public String toString() {
        return super.toString() + ", Players: " + numPlayers + ", Time: " + durationMinutes + " min.";
    }
}
