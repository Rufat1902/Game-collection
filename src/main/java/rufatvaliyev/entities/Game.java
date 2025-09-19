package rufatvaliyev.entities;

public abstract class Game {
    private String id;
    private String title;
    private int year;
    private double price;

    public Game(String id, String title, int year, double price) {
        if (price < 0) {
            throw new IllegalArgumentException("The price cannot be negative!");
        }
        this.id = id;
        this.title = title;
        this.year = year;
        this.price = price;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public int getYear() { return year; }
    public double getPrice() { return price; }

    public void setTitle(String title) { this.title = title; }
    public void setYear(int year) { this.year = year; }
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("The price cannot be negative!");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Heading: " + title + ", Year: " + year + ", Price: " + price + " " + "euro";
    }
}
