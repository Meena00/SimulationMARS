# MARS Simulation Project

## Overview

This project simulates the process of treating tourists returning from Mars who may be infected by a deadly microbe. The simulation compares two scenarios:

1. **Scenario 1 (Treat-All)**: Tourists are treated sequentially as they arrive, regardless of whether or not they are infected.
2. **Scenario 2 (Test-and-Treat)**: Tourists are first tested to determine the likelihood of infection. Based on the test result, they are either prioritized for treatment or marked as safe.

The goal of this project is to determine which approach minimizes fatalities while efficiently utilizing the available resources.

## Project Structure

The project consists of the following key classes:

- **`Simulation.java`**: The main simulation class that orchestrates the scenarios. It generates tourists with varying infection likelihood, manages their flow through the scenarios, and tracks safe individuals and fatalities.

- **`Scenario.java`**: An abstract class representing general simulation scenarios, providing common methods:
  - `getPending()`: Returns the number of tourists waiting for treatment.
  - `addPerson(Person p)`: Adds a tourist to the scenario.
  - `tick()`: Advances the simulation by one minute.

- **`TreatAll.java`**: A scenario where all tourists are treated sequentially in the order of arrival, regardless of infection likelihood. Tourists may die while waiting if their survival time runs out.

- **`TestAndTreat.java`**: A scenario where tourists are first tested for infection. Based on their test results, they either join a priority treatment queue or are marked safe.

- **`Person.java`**: Represents a tourist. Each tourist has a unique ID, a test result indicating the likelihood of infection, and a countdown timer before death if untreated.

- **`Line<T>`**: A dynamic array-based queue class, used to manage queues in both scenarios, supporting adding, removing, and retrieving elements.

## How the Simulation Works

The simulation progresses minute by minute. Each minute, the following occurs:

1. **Tourist Arrival**: With a probability of `arrivalProb`, a new tourist arrives from Mars. They are assigned a test result (infection likelihood) and a survival time (if infected).
2. **Scenario 1 (Treat-All)**: Tourists are treated in order of arrival. The doctor treats one person at a time, and treatment takes a fixed time. Tourists die if their survival time reaches zero before treatment.
3. **Scenario 2 (Test-and-Treat)**: Tourists are first tested. If their test result indicates a likelihood of infection ≥ 40%, they are prioritized for treatment. If their result is < 40%, they are marked safe.

The simulation tracks how many tourists are successfully treated, how many die, and how many are marked safe.

## Parameters

The simulation uses the following customizable parameters:

- **Arrival Probability**: The probability that a tourist returns from Mars each minute. Values: `[0, 1]`, Default: `0.4`.
- **Infected Probability**: The probability that a tourist is infected. Values: `[0, 1]`, Default: `0.4`.
- **Survival Time**: Average time a tourist has left to live if infected. Positive integers, Default: `10`.
- **Survival Standard Deviation**: Standard deviation of the survival time. Positive integers, Default: `5`.
- **Testing Time**: Time required to administer the test. Positive integers, Default: `2`.
- **Treatment Time**: Time required to treat a tourist. Positive integers, Default: `5`.
- **Seed**: A random number generator seed for repeatable simulation runs.

## How to Run

### Running the Simulation

1. **Compile the Java Files**:
   ```bash
   javac *.java
