package rufatvaliyev;

import com.github.javafaker.Faker;
import rufatvaliyev.entities.BoardGames;
import rufatvaliyev.entities.Collection;
import rufatvaliyev.entities.VideoGames;
import rufatvaliyev.interfaces.GameCollection;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameCollection collection = new Collection();
        Scanner sc = new Scanner(System.in);
        Faker faker = new Faker();
        Random random = new Random();

        while (true) {
            // Menu options
            System.out.println("\n--- Game Collection Menu ---");
            System.out.println("1. Add a new game");
            System.out.println("2. Search by ID");
            System.out.println("3. Search by price");
            System.out.println("4. Search by number of players");
            System.out.println("5. Remove a game by ID");
            System.out.println("6. Update a game by ID");
            System.out.println("7. Show statistics");
            System.out.println("8. Show all games");
            System.out.println("9. Generate random games (Faker)");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1: // Add game manually
                        System.out.print("For Video Game enter 1, for Board Game enter 2: ");
                        int type = sc.nextInt();
                        sc.nextLine();

                        System.out.print("ID: ");
                        String id = sc.nextLine();
                        System.out.print("Title: ");
                        String title = sc.nextLine();
                        System.out.print("Year: ");
                        int year = sc.nextInt();
                        System.out.print("Price: ");
                        double price = sc.nextDouble();
                        sc.nextLine(); // consume newline

                        if (type == 1) {
                            System.out.print("Platform: ");
                            String platform = sc.nextLine();
                            System.out.print("Duration (hours): ");
                            int duration = sc.nextInt();
                            System.out.print("Genre (ACTION, RPG, STRATEGY, ADVENTURE, SPORTS): ");
                            String genreStr = sc.next().toUpperCase();
                            VideoGames.Genre genre = VideoGames.Genre.valueOf(genreStr);

                            collection.addGame(new VideoGames(id, title, year, price, platform, duration, genre));
                        } else {
                            System.out.print("Number of players: ");
                            int players = sc.nextInt();
                            System.out.print("Duration (minutes): ");
                            int mins = sc.nextInt();

                            collection.addGame(new BoardGames(id, title, year, price, players, mins));
                        }
                        break;

                    case 2: // Search by ID
                        System.out.print("Enter ID: ");
                        String searchId = sc.nextLine();
                        System.out.println(collection.findById(searchId));
                        break;

                    case 3: // Search by price
                        System.out.print("Enter max price: ");
                        double maxPrice = sc.nextDouble();
                        collection.findByPrice(maxPrice).forEach(System.out::println);
                        break;

                    case 4: // Search by number of players
                        System.out.print("Enter number of players: ");
                        int numPlayers = sc.nextInt();
                        collection.findByNumPlayers(numPlayers).forEach(System.out::println);
                        break;

                    case 5: // Remove a game
                        System.out.print("Enter ID to remove: ");
                        String delId = sc.nextLine();
                        collection.removeById(delId);
                        System.out.println("Game removed successfully.");
                        break;

                    case 6: // Update a game
                        System.out.print("Enter ID to update: ");
                        String updId = sc.nextLine();
                        collection.removeById(updId);

                        System.out.println("Enter new game details:");
                        System.out.print("For Video Game enter 1, for Board Game enter 2: ");
                        int newType = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Title: ");
                        String newTitle = sc.nextLine();
                        System.out.print("Year: ");
                        int newYear = sc.nextInt();
                        System.out.print("Price: ");
                        double newPrice = sc.nextDouble();
                        sc.nextLine();

                        if (newType == 1) {
                            System.out.print("Platform: ");
                            String newPlatform = sc.nextLine();
                            System.out.print("Duration (hours): ");
                            int newDuration = sc.nextInt();
                            System.out.print("Genre: ");
                            String newGenreStr = sc.next().toUpperCase();
                            VideoGames.Genre newGenre = VideoGames.Genre.valueOf(newGenreStr);

                            collection.addGame(new VideoGames(updId, newTitle, newYear, newPrice, newPlatform, newDuration, newGenre));
                        } else {
                            System.out.print("Number of players: ");
                            int newPlayers = sc.nextInt();
                            System.out.print("Duration (minutes): ");
                            int newMins = sc.nextInt();
                            collection.addGame(new BoardGames(updId, newTitle, newYear, newPrice, newPlayers, newMins));
                        }
                        System.out.println("Game updated successfully.");
                        break;

                    case 7: // Show statistics
                        collection.printStatistics();
                        break;

                    case 8: // Show all games
                        collection.printAllGames();
                        break;

                    case 9: { // Generate random games using Faker
                        for (int i = 1; i <= 5; i++) {
                            String vidId = "VID" + i;
                            String randomTitle = faker.esports().game();
                            int randomYear = 2000 + random.nextInt(25);
                            double randomPrice = 10 + random.nextInt(50);
                            String platform = faker.options().option("PC", "PS5", "XBOX", "Switch");
                            int duration = 5 + random.nextInt(50);
                            VideoGames.Genre genre = faker.options().option(VideoGames.Genre.values());

                            collection.addGame(new VideoGames(vidId, randomTitle, randomYear, randomPrice, platform, duration, genre));
                        }

                        for (int i = 1; i <= 5; i++) {
                            String boardId = "BOARD" + i;
                            String randomBoardTitle = faker.book().title();
                            int randomBoardYear = 1990 + random.nextInt(35);
                            double randomBoardPrice = 5 + random.nextInt(40);
                            int players = 2 + random.nextInt(9);
                            int duration = 15 + random.nextInt(120);

                            collection.addGame(new BoardGames(boardId, randomBoardTitle, randomBoardYear, randomBoardPrice, players, duration));
                        }

                        System.out.println("10 random games generated successfully!");
                        System.out.println("--- Generated Games ---");
                        collection.printAllGames();
                        sc.nextLine(); // consume any leftover newline
                        break;
                    }

                    case 0: // Exit
                        System.out.println("Exiting the program...");
                        return;

                    default:
                        System.out.println("Invalid option, please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
