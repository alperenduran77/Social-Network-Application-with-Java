import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Main class to demonstrate the Social Network Analysis.
 */
public class Main {

    /**
     * Main method to run the Social Network Analysis application.
     *
     * @param args command line arguments
     * @throws ParseException if the date parsing fails
     */
    public static void main(String[] args) throws ParseException {
        SocialNetworkGraph network = new SocialNetworkGraph();
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int selection = -1;

        // Adding some people for demonstration
        network.addPerson("John Doe", 25, Arrays.asList("reading", "hiking", "cooking"));
        network.addPerson("Jane Smith", 22, Arrays.asList("swimming", "cooking"));
        network.addPerson("Alice Johnson", 27, Arrays.asList("hiking", "painting"));
        network.addPerson("Bob Brown", 30, Arrays.asList("reading", "swimming"));
        network.addPerson("Emily Davis", 28, Arrays.asList("running", "swimming"));
        network.addPerson("Frank Wilson", 26, Arrays.asList("reading", "hiking"));

        // Adding more people for comprehensive test
        network.addPerson("George Martin", 29, Arrays.asList("cycling", "cooking"));
        network.addPerson("Hannah White", 24, Arrays.asList("reading", "running"));
        network.addPerson("Ian Clark", 31, Arrays.asList("swimming", "painting"));
        network.addPerson("Julia Adams", 23, Arrays.asList("hiking", "running"));
        network.addPerson("Kyle Baker", 32, Arrays.asList("reading", "cycling"));
        network.addPerson("Laura Scott", 26, Arrays.asList("swimming", "hiking"));
        network.addPerson("Mike Turner", 28, Arrays.asList("running", "painting"));
        network.addPerson("Nina Evans", 30, Arrays.asList("cooking", "cycling"));
        network.addPerson("Oscar Young", 27, Arrays.asList("reading", "swimming"));
        network.addPerson("Paul Walker", 25, Arrays.asList("hiking", "running"));
        network.addPerson("Rachel Hall", 29, Arrays.asList("painting", "cycling"));

        // Adding friendships for demonstration
        network.addFriendship("John Doe", "Jane Smith");
        network.addFriendship("John Doe", "Alice Johnson");
        network.addFriendship("Jane Smith", "Bob Brown");
        network.addFriendship("Emily Davis", "Frank Wilson");

        // Adding more friendships for comprehensive test
        network.addFriendship("George Martin", "Hannah White");
        network.addFriendship("Ian Clark", "Julia Adams");
        network.addFriendship("Kyle Baker", "Laura Scott");
        network.addFriendship("Mike Turner", "Nina Evans");
        network.addFriendship("Oscar Young", "Paul Walker");
        network.addFriendship("Rachel Hall", "George Martin");
        network.addFriendship("Alice Johnson", "Hannah White");
        network.addFriendship("Frank Wilson", "Kyle Baker");
        network.addFriendship("Emily Davis", "Julia Adams");
        network.addFriendship("Mike Turner", "Oscar Young");

        // Finding shortest path for demonstration
        network.findShortestPath("John Doe", "Bob Brown");
        network.findShortestPath("Emily Davis", "Laura Scott");
        network.findShortestPath("Mike Turner", "Paul Walker");
        

        // Counting clusters for demonstration
        network.countClusters();

        do {
            System.out.println("===== Social Network Analysis Menu =====");
            System.out.println("1. Add person");
            System.out.println("2. Remove person");
            System.out.println("3. Add friendship");
            System.out.println("4. Remove friendship");
            System.out.println("5. Find shortest path");
            System.out.println("6. Suggest friends");
            System.out.println("7. Count clusters");
            System.out.println("8. Exit");
            System.out.print("Please select an option: ");

            try {
                selection = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (selection == 1) {
                    String name;
                    int age;
                    List<String> hobbies;
                    String timestampStr;
                    @SuppressWarnings("unused")
                    Date timestamp;
                    System.out.print("Enter name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter age: ");
                    age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter hobbies (comma-separated): ");
                    hobbies = Arrays.asList(scanner.nextLine().split(",\\s*"));
                    System.out.print("Enter timestamp (yyyy-MM-dd HH:mm:ss): ");
                    timestampStr = scanner.nextLine();
                    try {
                        timestamp = sdf.parse(timestampStr);
                    } catch (Exception e) {
                        System.out.println("Invalid timestamp format.");
                        continue;
                    }
                    network.addPerson(name, age, hobbies);
                } else if (selection == 2) {
                    String name;
                    String timestampStr;
                    @SuppressWarnings("unused")
                    Date timestamp;
                    System.out.print("Enter name of the person to remove: ");
                    name = scanner.nextLine();
                    System.out.print("Enter timestamp (yyyy-MM-dd HH:mm:ss): ");
                    timestampStr = scanner.nextLine();
                    try {
                        timestamp = sdf.parse(timestampStr);
                    } catch (Exception e) {
                        System.out.println("Invalid timestamp format.");
                        continue;
                    }
                    network.removePerson(name);
                } else if (selection == 3) {
                    String name1, name2;
                    String timestampStr1, timestampStr2;
                    @SuppressWarnings("unused")
                    Date timestamp1, timestamp2;
                    System.out.print("Enter the name of the first person: ");
                    name1 = scanner.nextLine();
                    System.out.print("Enter the timestamp of the first person (yyyy-MM-dd HH:mm:ss): ");
                    timestampStr1 = scanner.nextLine();
                    System.out.print("Enter the name of the second person: ");
                    name2 = scanner.nextLine();
                    System.out.print("Enter the timestamp of the second person (yyyy-MM-dd HH:mm:ss): ");
                    timestampStr2 = scanner.nextLine();  
                    try {
                        timestamp1 = sdf.parse(timestampStr1);
                        timestamp2 = sdf.parse(timestampStr2);
                    } catch (Exception e) {
                        System.out.println("Invalid timestamp format.");
                        continue;
                    }
                    network.addFriendship(name1, name2);
                } else if (selection == 4) {
                    String name1, name2;
                    String timestampStr1, timestampStr2;
                    @SuppressWarnings("unused")
                    Date timestamp1, timestamp2;
                    System.out.print("Enter the name of the first person: ");
                    name1 = scanner.nextLine();
                    System.out.print("Enter the timestamp of the first person (yyyy-MM-dd HH:mm:ss): ");
                    timestampStr1 = scanner.nextLine();
                    System.out.print("Enter the name of the second person: ");
                    name2 = scanner.nextLine();
                    System.out.print("Enter the timestamp of the second person (yyyy-MM-dd HH:mm:ss): ");
                    timestampStr2 = scanner.nextLine();
                    
                    try {
                        timestamp1 = sdf.parse(timestampStr1);
                        timestamp2 = sdf.parse(timestampStr2);
                    } catch (Exception e) {
                        System.out.println("Invalid timestamp format.");
                        continue;
                    }
                    network.removeFriendship(name1, name2);
                } else if (selection == 5) {
                    String startName, endName;
                    String startTimestampStr, endTimestampStr;
                    @SuppressWarnings("unused")
                    Date startTimestamp, endTimestamp;
                    System.out.print("Enter the name of the starting person: ");
                    startName = scanner.nextLine();
                    System.out.print("Enter the timestamp of the starting person (yyyy-MM-dd HH:mm:ss): ");
                    startTimestampStr = scanner.nextLine();
                    System.out.print("Enter the name of the ending person: ");
                    endName = scanner.nextLine();
                    System.out.print("Enter the timestamp of the ending person (yyyy-MM-dd HH:mm:ss): ");
                    endTimestampStr = scanner.nextLine();
                    
                    try {
                        startTimestamp = sdf.parse(startTimestampStr);
                        endTimestamp = sdf.parse(endTimestampStr);
                    } catch (Exception e) {
                        System.out.println("Invalid timestamp format.");
                        continue;
                    }
                    network.findShortestPath(startName, endName);
                } else if (selection == 6) {
                    String name, timestampStr;
                    int maxSuggestions;
                    @SuppressWarnings("unused")
                    Date timestamp;
                    System.out.print("Enter the name of the person: ");
                    name = scanner.nextLine();
                    System.out.print("Enter the timestamp (yyyy-MM-dd HH:mm:ss): ");
                    timestampStr = scanner.nextLine();
                    System.out.print("Enter maximum number of friends to suggest: ");
                    maxSuggestions = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    try {
                        timestamp = sdf.parse(timestampStr);
                    } catch (Exception e) {
                        System.out.println("Invalid timestamp format.");
                        continue;
                    }
                    network.suggestFriends(name,  maxSuggestions);
                } else if (selection == 7) {
                    network.countClusters();
                } else if (selection == 8) {
                    System.out.println("Exiting...");
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 8.");
                scanner.nextLine(); // Consume invalid input
            }
        } while (selection != 8);

        scanner.close();
    }
}
