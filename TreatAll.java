
/**
 * The `TreatAll` class simulates a scenario where all arriving people are treated
 * in turn as they arrive. It extends the abstract `Scenario` class.
 */
class TreatAll extends Scenario {
    //******************************************************
    //*******  DO NOT EDIT ANYTHING IN THIS SECTION  *******
    //******************************************************
    
    /**
     * The time required to administer a treatment regimen.
     */
    protected final int treatmentTime;
    
    /**
     * The line for treatment, where people wait to be treated.
     */
    protected Line<Person> treatmentLine = new Line<>();
    
    /**
     * The time remaining for the current treatment in minutes.
     */
    protected int currentTreatmentTime = 0;

    /**
     * Constructs a TreatAll scenario with the specified treatment time.
     * 
     * @param treatmentTime the time required to administer a treatment regimen
     */
    public TreatAll(int treatmentTime) {
        this.treatmentTime = treatmentTime;
    }
    
    /**
     * Provides access to the treatment line for the GUI.
     * 
     * @return the line of people waiting for treatment
     */
    public Line<Person> getTreatmentLine() {
        return treatmentLine;
    }

    //******************************************************
    //*******         MAKE YOUR CHANGES BELOW        *******
    //******************************************************
    
    /**
     * Returns the number of people currently waiting in the treatment line.
     * 
     * @return the number of people in the treatment line
     */
    @Override
    public int getPending() {
        return treatmentLine.getSize();
    }
    
    /**
     * Adds a person to the treatment line. If they are the only person in line,
     * they will start being treated immediately.
     * 
     * @param p the person to add to the treatment line
     */
    @Override
    public void addPerson(Person p) {
        // Add a person to the back of the treatment line
        treatmentLine.add(p, treatmentLine.getSize());
        
        // If they are the only person in line, they should start being treated
        if (treatmentLine.getSize() == 1) {
            currentTreatmentTime = treatmentTime;
        }
    }
    
    /**
     * Advances the simulation by one minute. This method handles the treatment process,
     * manages deaths in the line, and moves people through the treatment line.
     */
    @Override
    public void tick() {
        // Handle deaths in the line
        for (int i = 0; i < treatmentLine.getSize(); i++) {
            Person person = treatmentLine.get(i);
            person.tick(); // Reduce their time left using the Person's tick method
            if (person.isDead()) {
                treatmentLine.remove(i);
                numDeaths++;
                i--;
            }
        }

        // Handle treatment being administered
        if (treatmentLine.getSize() > 0) {
            currentTreatmentTime--;
            if (currentTreatmentTime <= 0) {
                treatmentLine.remove(0);
                numSafe++;
                
                // Reset treatment time if there are more people in line
                if (treatmentLine.getSize() > 0) {
                    currentTreatmentTime = treatmentTime;
                }
            }
        }
    }
    
    /**
     * Returns a string representation of the scenario, indicating the 
     * number of minutes until the doctor is free to treat the next person.
     * 
     * @return a string showing the time until the doctor is free
     */
    @Override
    public String toString() {
        return "Scenario 1: Doctor Free in " + (currentTreatmentTime > 0 ? currentTreatmentTime : "0") + " minute(s).";
    }
}
