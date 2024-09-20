// TO DO: Nothing required here.

//******************************************************
//*******  DO NOT EDIT ANYTHING BELOW THIS LINE  *******
//******************************************************

/**
 * The abstract parent class of all scenarios for the simulator.
 */
abstract class Scenario {
    /**
     * The number of people who have been determined to be safe from the microbe 
     * in this scenario.
     */
    protected int numSafe = 0;

    /**
     * The number of people who have died due to the microbe in this scenario.
     */
    protected int numDeaths = 0;

    /**
     * Returns the number of safe people in the scenario.
     * 
     * @return the number of safe people
     */
    public int getSafe() {
        return numSafe;
    }

    /**
     * Returns the number of deaths in the scenario.
     * 
     * @return the number of deaths
     */
    public int getDeaths() {
        return numDeaths;
    }

    /**
     * Returns the number of people still being processed in this scenario.
     * 
     * @return the number of pending people
     */
    public abstract int getPending();

    /**
     * Adds a person to the scenario when they arrive from Mars.
     * 
     * @param p the person arriving from Mars
     */
    public abstract void addPerson(Person p);

    /**
     * Moves time forward by one minute, updating the state of the scenario.
     */
    public abstract void tick();
}
