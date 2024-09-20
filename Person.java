/**
 * The {@code Person} class represents a person in the simulation.
 * Each person has a unique ID, a test result, a countdown timer for
 * their time left before they "die" (or not infected).
 */
class Person implements Comparable<Person> {
    /**
     * Static counter to assign unique IDs to each person.
     */
    private static int numPeople = 0;

    /**
     * The test result for the person.
     * A double representing the likelihood of infection.
     */
    private double testResult;

    /**
     * The time left for this person before they are considered "dead."
     */
    private int timeLeft;

    /**
     * A boolean flag to indicate if the person is dead.
     */
    private boolean isDead;

    /**
     * The unique ID for this person.
     */
    private final int id;

    /**
     * Constructs a {@code Person} with a given test result and time left.
     * Each person gets a unique ID upon creation.
     *
     * @param testResult the test result for the person
     * @param timeLeft the time left before the person dies
     */
    public Person(double testResult, int timeLeft) {
        this.testResult = testResult;
        this.timeLeft = timeLeft;
        this.isDead = false; // Initialize isDead to false
        this.id = numPeople++;
    }

    /**
     * Constructs a {@code Person} with a given test result, time left,
     * and a specific ID. This is used for cloning purposes.
     *
     * @param testResult the test result for the person
     * @param timeLeft the time left before the person dies
     * @param id the specific ID to assign to the person
     */
    private Person(double testResult, int timeLeft, int id) {
        this.testResult = testResult;
        this.timeLeft = timeLeft;
        this.isDead = false; // Initialize isDead to false
        this.id = id;
    }

    /**
     * Decrements the person's time left if they are not "immortal"
     * This simulates the passing of time.
     */
    public void tick() {
        if (timeLeft != Integer.MAX_VALUE) {
            timeLeft--;
            // Update the isDead field based on timeLeft
            if (timeLeft <= 0) {
                isDead = true;
            }
        }
    }

    /**
     * Checks if the person is dead.
     *
     * @return {@code true} if the person is dead, otherwise {@code false}
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Returns the test result for this person. The test result should only be accessed
     * after the person has gone through testing in the simulation scenario.
     *
     * @return the test result for the person
     */
    public double getTestResult() {
        return testResult;
    }

    /**
     * Returns the unique ID assigned to this person.
     *
     * @return the person's unique ID
     */
    public int getId() {
        return id;
    }

    /**
     * Creates and returns a copy (clone) of this {@code Person}, preserving the
     * test result, time left, and ID.
     *
     * @return a clone of this person
     */
    public Person clone() {
        return new Person(testResult, timeLeft, id);
    }

    /**
     * Returns a string representation of the person, displaying their ID and
     * either their time left (in minutes) or a message indicating they are not infected.
     *
     * @return a string representation of the person
     */
    public String toString() {
        return "ID: " + id + ", " + ((timeLeft == Integer.MAX_VALUE) ? "Not Infected" : "Time Left: " + timeLeft + " minutes");
    }

    /**
     * Compares this person with another person based on their test result.
     * This method is used to prioritize persons in the treatment line.
     * 
     * @param other the other person to compare against
     * @return a negative integer, zero, or a positive integer as this person
     *         is less than, equal to, or greater than the specified person
     */
    @Override
    public int compareTo(Person other) {
        // Higher testResult indicates a higher priority for treatment
        // If two persons have the same test result, we can use ID to break ties
        if (this.testResult > other.testResult) {
            return -1; // Higher test result should come first in priority
        } else if (this.testResult < other.testResult) {
            return 1; // Lower test result comes later in priority
        } else {
            return Integer.compare(this.id, other.id); // Use ID to break ties
        }
    }
}
