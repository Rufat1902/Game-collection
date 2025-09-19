package rufatvaliyev.entities;

public class VideoGames extends Game {
    public enum Genre {
        ACTION, RPG, STRATEGY, ADVENTURE, SPORTS
    }

    private String platform;
    private int durationHours;
    private Genre genre;

    public VideoGames(String id, String title, int year, double price, String platform, int durationHours, Genre genre) {
        super(id, title, year, price);
        this.platform = platform;
        this.durationHours = durationHours;
        this.genre = genre;
    }

    public String getPlatform() { return platform; }
    public int getDurationHours() { return durationHours; }
    public Genre getGenre() { return genre; }

    @Override
    public String toString() {
        return super.toString() + ", Platform: " + platform + ", Time: " + durationHours + " hours, Genre: " + genre;
    }
}
