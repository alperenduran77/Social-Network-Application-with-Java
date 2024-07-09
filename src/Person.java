import java.util.*;

/**
 * Represents a person in the social network.
 */
public class Person {
    String name;
    int age;
    List<String> hobbies;
    Date timestamp;

    /**
     * Constructs a new Person object.
     *
     * @param name      the name of the person
     * @param age       the age of the person
     * @param hobbies   a list of hobbies of the person
    
     */
    public Person(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        this.hobbies = new ArrayList<>(hobbies);
        
    }

    /**
     * Returns a string representation of the person.
     *
     * @return a string representation of the person
     */
    @Override
    public String toString() {
        return name + " (Age: " + age + ", Hobbies: " + hobbies + ")";
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Person)) {
            return false;
        }
        Person other = (Person) obj;
        return name.equals(other.name) /* && age == other.age && hobbies.equals(other.hobbies) && timestamp.equals(other.timestamp)*/;
    }

    /**
     * Returns the name of the person.
     *
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the age of the person.
     *
     * @return the age of the person
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the list of hobbies of the person.
     *
     * @return the list of hobbies of the person
     */
    public List<String> getHobbies() {
        return hobbies;
    }

}
