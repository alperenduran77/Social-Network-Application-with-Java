import java.util.*;

/**
 * This class represents a social network graph where each person is a node and
 * friendships are edges.
 */
public class SocialNetworkGraph {
    Map<String, Person> people = new HashMap<>();
    Map<Person, List<Person>> friendships = new HashMap<>();

    /**
     * Adds a person to the social network.
     *
     * @param name    the name of the person
     * @param age     the age of the person
     * @param hobbies the hobbies of the person
     */
    public void addPerson(String name, int age, List<String> hobbies) {
        Person newPerson = new Person(name, age, hobbies);
        String key = name;

        // Check for duplicate entry
        Iterator<Person> iterator = people.values().iterator();
        while (iterator.hasNext()) {
            Person person = iterator.next();
            if (person.equals(newPerson)) {
                System.out.println("Person already exists: " + person);
                return;
            }
        }

        // Add the new person to the network
        people.put(key, newPerson);
        friendships.put(newPerson, new ArrayList<>());
        System.out.println("Person added: " + newPerson);
    }

    /**
     * Removes a person from the social network.
     *
     * @param name the name of the person to be removed
     * 
     */
    public void removePerson(String name) {
        Person person = people.remove(name);
        if (person != null) {
            friendships.remove(person);
            Iterator<List<Person>> iterator = friendships.values().iterator();
            while (iterator.hasNext()) {
                List<Person> friends = iterator.next();
                friends.remove(person);
            }
            System.out.println("Person removed: " + person);
        } else {
            System.out.println("Person not found.");
        }
    }

    /**
     * Adds a friendship between two people in the social network.
     *
     * @param name1 the name of the first person
     * 
     * @param name2 the name of the second person
     * 
     */
    public void addFriendship(String name1, String name2) {
        Person person1 = people.get(name1);
        Person person2 = people.get(name2);
        if (person1 != null && person2 != null) {
            List<Person> person1Friends = friendships.get(person1);
            List<Person> person2Friends = friendships.get(person2);
            if (!person1Friends.contains(person2) && !person2Friends.contains(person1)) {
                person1Friends.add(person2);
                person2Friends.add(person1);
                System.out.println("Friendship added between " + person1.getName() + " and " + person2.getName());
            } else {
                System.out.println("Persons are already friends.");
            }
        } else {
            System.out.println("One or both persons not found in the network.");
        }
    }
    

    /**
     * Removes a friendship between two people in the social network.
     *
     * @param name1 the name of the first person
     * 
     * @param name2 the name of the second person
     * 
     */
    public void removeFriendship(String name1, String name2) {
        Person person1 = people.get(name1);
        Person person2 = people.get(name2);
        if (person1 != null && person2 != null) {
            List<Person> friends1 = friendships.get(person1);
            List<Person> friends2 = friendships.get(person2);
            if (friends1 != null && friends2 != null && friends1.contains(person2) && friends2.contains(person1)) {
                friends1.remove(person2);
                friends2.remove(person1);
                System.out.println("Friendship removed between " + person1.getName() + " and " + person2.getName());
            } else {
                System.out.println("Friendship does not exist between " + person1.getName() + " and " + person2.getName());
            }
        } else {
            System.out.println("One or both persons not found in the network.");
        }
    }

    /**
     * Performs a breadth-first search (BFS) to find all people connected to the
     * start person.
     *
     * @param start   the starting person
     * @param visited the set of visited people
     * @param cluster the list of people in the current cluster
     */
    private void bfs(Person start, Set<Person> visited, List<Person> cluster) {
        // Initialize the queue with the start person
        Queue<Person> queue = new LinkedList<>();
        queue.add(start);

        // Mark the start person as visited
        visited.add(start);

        // Continue the BFS until the queue is empty
        while (!queue.isEmpty()) {
            // Dequeue the next person in the queue
            Person current = queue.poll();

            // Add the current person to the cluster
            cluster.add(current);

            // Get the list of neighbors (friends) for the current person
            List<Person> neighbors = friendships.get(current);

            // Iterate through the neighbors
            int i = 0;
            while (i < neighbors.size()) {
                Person neighbor = neighbors.get(i);

                // If the neighbor has not been visited, mark it as visited and enqueue it
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
                i++;
            }
        }
    }

    /**
     * Finds the shortest path between two people using BFS.
     *
     * @param startName the name of the starting person
     * 
     * @param endName   the name of the ending person
     * 
     */
    public void findShortestPath(String startName, String endName) {
        // Retrieve the starting and ending persons from the people map
        Person start = people.get(startName);
        Person end = people.get(endName);

        // Check if both persons exist in the network
        if (start == null || end == null) {
            System.out.println("One or both persons not found in the network.");
            return;
        }

        // Initialize the queue for BFS and add the start person to it
        Queue<Person> queue = new LinkedList<>();
        queue.add(start);

        // Initialize the map to track the previous person in the path
        Map<Person, Person> prev = new HashMap<>();

        // Initialize the set of visited persons and mark the start person as visited
        Set<Person> visited = new HashSet<>();
        visited.add(start);

        // Continue the BFS until the queue is empty
        while (!queue.isEmpty()) {
            // Dequeue the next person in the queue
            Person current = queue.poll();

            // Check if the current person is the end person
            if (current.equals(end)) {
                // Print the shortest path from start to end
                printPath(start, end, prev);
                return;
            }

            // Get the list of neighbors (friends) for the current person
            List<Person> neighbors = friendships.get(current);

            // Iterate through the neighbors
            int i = 0;
            while (i < neighbors.size()) {
                Person neighbor = neighbors.get(i);

                // If the neighbor has not been visited, mark it as visited, enqueue it, and
                // update the previous map
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    prev.put(neighbor, current);
                }
                i++;
            }
        }

        // If no path is found, print a message indicating so
        System.out.println("No path found between " + startName + " and " + endName);
    }

    /**
     * Prints the shortest path from start to end person.
     *
     * @param start the starting person
     * @param end   the ending person
     * @param prev  the map of previous nodes in the path
     */
    private void printPath(Person start, Person end, Map<Person, Person> prev) {
        // Create a list to store the path and a StringBuilder for the path string
        List<Person> path = new ArrayList<>();
        StringBuilder pathStr = new StringBuilder();

        // Trace back from the end person to the start person using the prev map
        for (Person at = end; at != null; at = prev.get(at)) {
            path.add(at);
        }

        // Reverse the path list to get the correct order from start to end
        Collections.reverse(path);

        // Create an iterator to traverse the path list
        Iterator<Person> iterator = path.iterator();
        while (iterator.hasNext()) {
            Person person = iterator.next();

            // Add a separator " -> " if this is not the first person in the path
            if (pathStr.length() > 0) {
                pathStr.append(" -> ");
            }

            // Append the person's name to the path string
            pathStr.append(person.getName());
        }

        // Print the shortest path
        System.out.println("Shortest path: " + pathStr.toString());
    }

    /**
     * Calculates the friendship score between two people.
     *
     * @param person          the person
     * @param potentialFriend the potential friend
     * @return the friendship score
     */
    private double calculateFriendshipScore(Person person, Person potentialFriend) {
        int commonFriends = 0; // Initialize count of mutual friends
        int commonHobbies = 0; // Initialize count of common hobbies

        // Get the list of friends of the person
        List<Person> friends = friendships.get(person);
        int i = 0;

        // Iterate through the list of friends to count mutual friends
        while (i < friends.size()) {
            Person friend = friends.get(i);
            if (friendships.get(potentialFriend).contains(friend)) {
                commonFriends++; // Increment mutual friends count if the potential friend also has this friend
            }
            i++;
        }

        // Get the list of hobbies of the person
        List<String> hobbies = person.getHobbies();
        i = 0;

        // Iterate through the list of hobbies to count common hobbies
        while (i < hobbies.size()) {
            String hobby = hobbies.get(i);
            if (potentialFriend.getHobbies().contains(hobby)) {
                commonHobbies++; // Increment common hobbies count if the potential friend also has this hobby
            }
            i++;
        }

        // Calculate the friendship score as the sum of mutual friends and half the
        // number of common hobbies
        return commonFriends + 0.5 * commonHobbies;
    }

    /**
     * Counts the mutual friends between two people.
     *
     * @param person          the person
     * @param potentialFriend the potential friend
     * @return the number of mutual friends
     */
    private int countCommonFriends(Person person, Person potentialFriend) {
        int commonFriends = 0;
        List<Person> friends = friendships.get(person);
        int i = 0;
        while (i < friends.size()) {
            Person friend = friends.get(i);
            if (friendships.get(potentialFriend).contains(friend)) {
                commonFriends++;
            }
            i++;
        }
        return commonFriends;
    }

    /**
     * Counts the common hobbies between two people.
     *
     * @param person          the person
     * @param potentialFriend the potential friend
     * @return the number of common hobbies
     */
    private int countCommonHobbies(Person person, Person potentialFriend) {
        int commonHobbies = 0;
        List<String> hobbies = person.getHobbies();
        int i = 0;
        while (i < hobbies.size()) {
            String hobby = hobbies.get(i);
            if (potentialFriend.getHobbies().contains(hobby)) {
                commonHobbies++;
            }
            i++;
        }
        return commonHobbies;
    }

    /**
     * Counts the number of clusters in the social network.
     */
    public void countClusters() {
        int clusters = 0; // Counter for the number of clusters
        Set<Person> visited = new HashSet<>(); // Set to keep track of visited persons   
        Map<Integer, List<Person>> clusterMap = new HashMap<>(); // Map to store clusters

        // Iterator to go through all persons in the network
        Iterator<Person> iterator = people.values().iterator();
        while (iterator.hasNext()) {
            Person person = iterator.next();
            if (!visited.contains(person)) { // If the person has not been visited
                List<Person> cluster = new ArrayList<>(); // Create a new cluster
                bfs(person, visited, cluster); // Perform BFS to find all persons in the cluster
                clusterMap.put(clusters++, cluster); // Add the cluster to the map and increment the cluster count
            }
        }

        // Print the number of clusters found
        System.out.println("Number of clusters found: " + clusters);

        // Iterator to go through all clusters in the clusterMap
        Iterator<Map.Entry<Integer, List<Person>>> clusterIterator = clusterMap.entrySet().iterator();
        while (clusterIterator.hasNext()) {
            Map.Entry<Integer, List<Person>> entry = clusterIterator.next();
            System.out.println("Cluster " + (entry.getKey() + 1) + ":"); // Print the cluster number

            // Iterator to go through all persons in the current cluster
            Iterator<Person> personIterator = entry.getValue().iterator();
            while (personIterator.hasNext()) {
                System.out.println(personIterator.next().getName()); // Print the name of each person in the cluster
            }
        }
    }

    /**
     * Suggests friends for a given person based on mutual friends and common
     * hobbies.
     *
     * @param name           the name of the person
     * 
     * @param maxSuggestions the maximum number of friend suggestions
     */
    public void suggestFriends(String name, int maxSuggestions) {
        Person person = people.get(name); // Retrieve the person based on name and timestamp
        // Maps to store suggestion scores, mutual friends, and common hobbies
        Map<Person, Double> suggestionScores = new HashMap<>();
        Map<Person, Integer> commonFriendsMap = new HashMap<>();
        Map<Person, Integer> commonHobbiesMap = new HashMap<>();

        if (person == null) { // Check if the person exists in the network
            System.out.println("Person not found.");
            return;
        }

        // Get the list of friends for the person
        List<Person> friends = friendships.get(person);
        int i = 0;
        // Iterate through the person's friends
        while (i < friends.size()) {
            Person friend = friends.get(i);
            // Get the list of potential friends (friends of friends)
            List<Person> potentialFriends = friendships.get(friend);
            int j = 0;
            // Iterate through the potential friends
            while (j < potentialFriends.size()) {
                Person potentialFriend = potentialFriends.get(j);
                // Check if the potential friend is not the person and is not already a friend
                if (!potentialFriend.equals(person) && !friendships.get(person).contains(potentialFriend)) {
                    double score = calculateFriendshipScore(person, potentialFriend); // Calculate the friendship score
                    suggestionScores.put(potentialFriend, score);

                    // Track mutual friends and common hobbies
                    commonFriendsMap.put(potentialFriend, countCommonFriends(person, potentialFriend));
                    commonHobbiesMap.put(potentialFriend, countCommonHobbies(person, potentialFriend));
                }
                j++;
            }
            i++;
        }

        // Include people with common hobbies even if they have no mutual friends
        Iterator<Person> iterator = people.values().iterator();
        while (iterator.hasNext()) {
            Person potentialFriend = iterator.next();
            if (!potentialFriend.equals(person) && !friendships.get(person).contains(potentialFriend)) {
                if (!suggestionScores.containsKey(potentialFriend)) {
                    int commonHobbies = countCommonHobbies(person, potentialFriend);
                    if (commonHobbies > 0) {
                        suggestionScores.put(potentialFriend, 0.5 * commonHobbies);
                        commonFriendsMap.put(potentialFriend, 0);
                        commonHobbiesMap.put(potentialFriend, commonHobbies);
                    }
                }
            }
        }

        // Convert the suggestions map to a list and sort it based on the scores
        List<Map.Entry<Person, Double>> suggestions = new ArrayList<>(suggestionScores.entrySet());
        suggestions.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        // Print the suggested friends
        System.out.println("Suggested friends for " + name + ":");
        i = 0;
        while (i < Math.min(maxSuggestions, suggestions.size())) {
            Person suggestedFriend = suggestions.get(i).getKey();
            double score = suggestions.get(i).getValue();
            int commonFriends = commonFriendsMap.get(suggestedFriend);
            int commonHobbies = commonHobbiesMap.get(suggestedFriend);
            System.out.println(suggestedFriend.getName() + " (Score: " + score + ", " + commonFriends
                    + " mutual friends, " + commonHobbies + " common hobbies)");
            i++;
        }
        if(i == 0)
            System.out.println("No suggestions found.");
    }
}
