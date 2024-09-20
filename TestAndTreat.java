
/**
 * The `TestAndTreat` class simulates a scenario where each arriving person is first tested 
 * for how likely they are being infected with the microbe. Based on the test result, they are 
 * either placed into a priority treatment line or marked as safe. This class extends the 
 * `TreatAll` class.
 */
class TestAndTreat extends TreatAll {
    /**
     * The time required to administer a test.
     */
    private final int testingTime;

    /**
     * The line for testing, where people wait to be tested.
     */
    private Line<Person> testingLine = new Line<>();

    /**
     * The time remaining for the current test in minutes.
     */
    private int currentTestingTime = 0;

    /**
     * Constructs a TestAndTreat scenario with the specified testing and treatment times.
     * 
     * @param testingTime the time required to administer a test
     * @param treatmentTime the time required to administer a treatment regimen
     */
    public TestAndTreat(int testingTime, int treatmentTime) {
        super(treatmentTime);
        this.testingTime = testingTime;
    }

    /**
     * Returns the testing line for the GUI.
     *
     * @return the line of people waiting for testing
     */
    public Line<Person> getTestingLine() {
        return testingLine;
    }

    /**
     * Adds a person to the testing line. If they are the only person in line,
     * they will start being tested immediately.
     * 
     * @param p the person to add to the testing line
     */
    @Override
    public void addPerson(Person p) {
        // Add a person to the back of the testing line
        testingLine.add(p, testingLine.getSize());

        // If they are the only person in the testing line, start testing
        if (testingLine.getSize() == 1) {
            currentTestingTime = testingTime;
        }
    }

    /**
     * Advances the simulation by one minute. This method handles the testing process,
     * moves people to the treatment line based on their test results, and manages 
     * the treatment process inherited from TreatAll.
     */
    @Override
    public void tick() {
        
        if (testingLine.getSize() > 0) {
            currentTestingTime--;

            
            if (currentTestingTime <= 0 && testingLine.getSize() > 0) {
                Person testedPerson = testingLine.remove(0);

                // Determine if they need treatment
                if (testedPerson.getTestResult() >= 0.4) {
                    // Add them to the treatment line based on priority
                    addToTreatmentLine(testedPerson);
                } else {
                    numSafe++;
                }

                // If there are more people in the testing line, start testing the next person
                if (testingLine.getSize() > 0) {
                    currentTestingTime = testingTime;
                }
            }
        }

        for (int i = 0; i < testingLine.getSize(); i++) {
            Person person = testingLine.get(i);
            person.tick(); // Reduce their time left
            if (person.isDead()) {
                // Remove the person if they die while in the testing line
                testingLine.remove(i);
                numDeaths++;
                i--;
            }
        }

        super.tick();
    }

    /**
     * Adds the person to the treatment line based on their test result.
     * Higher test results are given higher priority.
     *
     * @param person the person to add to the treatment line
     */
    private void addToTreatmentLine(Person person) {
        int insertIndex = 0;
        // Find the correct position to insert based on the test result (priority queue)
        for (int i = 0; i < treatmentLine.getSize(); i++) {
            if (treatmentLine.get(i).getTestResult() < person.getTestResult()) {
                break;
            } else {
                insertIndex++;
            }
        }
        // Insert the person into the treatment line at the calculated position
        treatmentLine.add(person, insertIndex);
        
        // If this person is the only one in the treatment line or moves to the front of the line, start treatment
        if (insertIndex == 0 && treatmentLine.getSize() == 1) {
            currentTreatmentTime = treatmentTime;
        }
    }

    /**
     * Returns a string representation of the scenario, indicating the time until the 
     * next test is available and the number of minutes until the doctor is free.
     * 
     * @return a string showing the time until the next test and the time until the doctor is free
     */
    @Override
    public String toString() {
        return "Scenario 2: Test available in " + (currentTestingTime > 0 ? currentTestingTime : "0") + 
               " minute(s). Doctor Free in " + (currentTreatmentTime > 0 ? currentTreatmentTime : "0") + " minute(s).";
    }
}
