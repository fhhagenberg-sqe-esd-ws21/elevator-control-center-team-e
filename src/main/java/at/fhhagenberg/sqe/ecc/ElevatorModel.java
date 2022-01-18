package at.fhhagenberg.sqe.ecc;

import java.util.ArrayList;
import java.util.List;

/**
 * Model that holds the elevator status.
 * @author Berhard Wandl - s2010567008
 */
public class ElevatorModel {

    private List<Elevator> elevators;
    private List<Floor> floors;

    private long clockTick;
    private int floorHeight;
    private int loggingLevel;

    private ElevatorGuiUpdater guiUpdater;

    private String errorMessage = "";

    /**
     * Creates a new elevator model with the given specifications.
     * @param numberOfElevators Number of elevators in the system.
     * @param numberOfFloors Number of floors in the system.
     * @param floorHeight Height of a single floor.
     */
    public ElevatorModel(int numberOfElevators, int numberOfFloors, int floorHeight) {
        elevators = new ArrayList<>();
        for(int i = 0; i < numberOfElevators; ++i) {
            elevators.add(new Elevator(numberOfFloors));
        }
        floors = new ArrayList<>(numberOfFloors);
        for(int i = 0; i < numberOfFloors; ++i) {
            floors.add(new Floor(false, false, false, true));
        }

        this.floorHeight = floorHeight;
        clockTick = 0;
        loggingLevel = 0;
    }

    /**
     * Gets the number of elevators.
     * @return The number of elevators.
     */
    public int getNumOfElevators() {
        return elevators.size();
    }

    /**
     * Gets the number of floors.
     * @return The number of floors.
     */
    public int getNumOfFloors() {
        return floors.size();
    }

    /**
     * Sets the commited direction of an elevator.
     * @param elevatorNumber The elevator to set the value for.
     * @param direction The direction to set according to the enum.
     * @throws IllegalArgumentException If direction value if out of bounds.
     */
    public void setCommittedDirection(int elevatorNumber, int direction) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setCommittedDirection(direction);
        guiUpdater.updateCommittedDirection(elevatorNumber);
    }

    /**
     * Gets the commited direction of an elevator.
     * @param elevatorNumber The elevator to get the value for.
     * @return The committed direction according to the enum.
     * @throws IllegalArgumentException if the elevator number is out of bounds.
     */
    public int getCommittedDirection(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getCommittedDirection();
    }

    /**
     * Gets the serviced floor status for a specific floor and elevator.
     * @param elevatorNumber The elevator to query.
     * @param floor The floor to query for.
     * @return True if the floor is serviced, false otherwise.
     * @throws IllegalArgumentException if the elevator number is out of bounds.
     */
    public boolean getServicesFloors(int elevatorNumber, int floor) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size() || floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getServicesFloors(floor);
    }

    /**
     * Sets the serviced status for a given floor and elevator.
     * @param elevatorNumber The elevator to set the value for.
     * @param floor The floor to set the value for.
     * @param service true if serviced, false otherwise.
     * @throws IllegalArgumentException if the elevator of floor is out of bounds.
     */
    public void setServicesFloors(int elevatorNumber, int floor, boolean service) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size() || floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setServicesFloors(floor, service);
        guiUpdater.updateServicedFloors(elevatorNumber, floor);
    }

    /**
     * Gets the current target of an elevator.
     * @param elevatorNumber The elevator to query.
     * @return The current target floor.
     * @throws IllegalArgumentException if the elevator number is out of bounds.
     */
    public int getTarget(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getTarget();
    }

    /**
     * Sets the current target for an elevator.
     * @param elevatorNumber The elevator to set the value for.
     * @param target The target floor.
     * @throws IllegalArgumentException If the elevator or target is out of bounds.
     */
    public void setTarget(int elevatorNumber, int target) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size() || target < 0 || target >= floors.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setTarget(target);
        guiUpdater.updateTargetFloor(elevatorNumber);
    }

    /**
     * Gets the current acceleration.
     * @param elevatorNumber The elevator to query.
     * @return The current acceleration.
     * @throws IllegalArgumentException If the elevator number is out of bounds.
     */
    public int getElevatorAccel(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber >= elevators.size() || elevatorNumber < 0)
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getElevatorAccel();
    }

    /**
     * Sets a new acceleration value.
     * @param elevatorNumber The elevator to set the value for.
     * @param value The acceleration value to set.
     * @throws IllegalArgumentException If the elevator number is out of bounds.
     */
    public void setElevatorAccel(int elevatorNumber, int value) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setElevatorAccel(value);
        guiUpdater.updateElevatorAccel(elevatorNumber);
    }

    /**
     * Gets the button status.
     * @param elevatorNumber The elevator to query.
     * @param floor The floor to query.
     * @return True if the button is active, false otherwise.
     * @throws IllegalArgumentException If elevator of floor number are out of bounds.
     */
    public boolean getElevatorButton(int elevatorNumber, int floor) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size() || floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getElevatorButton(floor);
    }

    /**
     * Sets the button status.
     * @param elevatorNumber The elevator to set the button for.
     * @param floor The floor to set.
     * @param value True if active, false otherwise.
     * @throws IllegalArgumentException If floor or elevator are out of bounds.
     */
    public void setElevatorButton(int elevatorNumber, int floor, boolean value) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size() || floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setElevatorButton(floor, value);
        guiUpdater.updateElevatorButton(elevatorNumber);
    }

    /**
     * Gets the door status.
     * @param elevatorNumber The elevator to query.
     * @return Get current door status according to the enum.
     * @throws IllegalArgumentException If the elevator is out of bounds.
     */
    public int getElevatorDoorStatus(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getElevatorDoorStatus();
    }

    /**
     * Sets the door status.
     * @param elevatorNumber The elevator to set the value for.
     * @param doorStatus The door status according to the enum.
     * @throws IllegalArgumentException If elevator number is out of bounds.
     */
    public void setElevatorDoorStatus(int elevatorNumber, int doorStatus) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setElevatorDoorStatus(doorStatus);
        guiUpdater.updateElevatorDoorStatus(elevatorNumber);
    }

    /**
     * Gets the current floor.
     * @param elevatorNumber The elevator to query.
     * @return The current floor of the elevator.
     * @throws IllegalArgumentException If the elevator number is out of bounds.
     */
    public int getElevatorFloor(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getElevatorFloor();
    }

    /**
     * Sets the current floor.
     * @param elevatorNumber The elevator to set the value for.
     * @param floor The floor to set.
     * @throws IllegalArgumentException If the elevator or floor number is out of bounds.
     */
    public void setElevatorFloor(int elevatorNumber, int floor) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size() || floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setElevatorFloor(floor);
        guiUpdater.updateElevatorFloor(elevatorNumber);
    }

    /**
     * Gets the current elevator position.
     * @param elevatorNumber The elevator to query.
     * @return The current position.
     * @throws IllegalArgumentException If the elevator number is out of bounds.
     */
    public int getElevatorPosition(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber >= elevators.size() || elevatorNumber < 0)
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getElevatorPosition();
    }

    /**
     * Sets the current elevator position.
     * @param elevatorNumber The elevator to set the value for.
     * @param position The current position.
     * @throws IllegalArgumentException If the elevator number is out of bounds.
     */
    public void setElevatorPosition(int elevatorNumber, int position) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setElevatorPosition(position);
        guiUpdater.updateElevatorPosition(elevatorNumber);
    }

    /**
     * Gets the current speed.
     * @param elevatorNumber The elevator to query.
     * @return The current speed.
     * @throws IllegalArgumentException If the elevator number is out of bounds.
     */
    public int getElevatorSpeed(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getElevatorSpeed();
    }

    /**
     * Sets the current speed.
     * @param elevatorNumber The elevator to set the value for.
     * @param speed The current speed to set.
     * @throws IllegalArgumentException If the elevator number is out of bounds.
     */
    public void setElevatorSpeed(int elevatorNumber, int speed) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setElevatorSpeed(speed);
        guiUpdater.updateElevatorSpeed(elevatorNumber);
    }

    /**
     * Gets the current weight.
     * @param elevatorNumber The elevator to query.
     * @return The current weight.
     * @throws IllegalArgumentException If the elevator number is out of bounds.
     */
    public int getElevatorWeight(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getElevatorWeight();
    }

    /**
     * Sets the elevator weight.
     * @param elevatorNumber The elevator to set the value for.
     * @param weight The current weight to set.
     * @throws IllegalArgumentException If the elevator number is out of bounds.
     */
    public void setElevatorWeight(int elevatorNumber, int weight) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setElevatorWeight(weight);
        guiUpdater.updateElevatorWeight(elevatorNumber);
    }

    /**
     * Gets the capacity.
     * @param elevatorNumber The elevator to query.
     * @return The capacity.
     * @throws IllegalArgumentException If the elevator number is out of bounds.
     */
    public int getElevatorCapacity(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber >= elevators.size() || elevatorNumber < 0)
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getElevatorCapacity();
    }

    /**
     * Sets the elevator capacity.
     * @param elevatorNumber The elevator to set the value for.
     * @param capacity The capacity to set.
     * @throws IllegalArgumentException If the elevator number is out of bounds.
     */
    public void setElevatorCapacity(int elevatorNumber, int capacity) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setElevatorCapacity(capacity);
        guiUpdater.updateElevatorCapacity(elevatorNumber);
    }

    /**
     * Gets the floor button down status.
     * @param floor The floor to query the status for.
     * @return True if button is active, false otherwise.
     * @throws IllegalArgumentException If the floor number is out of bounds.
     */
    public boolean getFloorButtonDown(int floor) throws IllegalArgumentException {
        if(floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        return floors.get(floor).isFloorButtonDown();
    }

    /**
     * Sets the floor button down status.
     * @param floor The floor to set the status for.
     * @param value True if down is active, false otherwise.
     * @throws IllegalArgumentException If the floor number is out of bounds.
     */
    public void setFloorButtonDown(int floor, boolean value) throws IllegalArgumentException {
        if(floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        floors.get(floor).setFloorButtonDown(value);
        guiUpdater.updateFloorButtonDown(floor);
    }

    /**
     * Gets the floor button up status.
     * @param floor The floor to query the status for.
     * @return True if button is active, false otherwise.
     * @throws IllegalArgumentException If the floor number is out of bounds.
     */
    public boolean getFloorButtonUp(int floor) throws IllegalArgumentException {
        if(floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        return floors.get(floor).isFloorButtonUp();
    }

    /**
     * Sets the floor button up status.
     * @param floor The floor to set the status for.
     * @param value True if down is active, false otherwise.
     * @throws IllegalArgumentException If the floor number is out of bounds.
     */
    public void setFloorButtonUp(int floor, boolean value) throws IllegalArgumentException {
        if(floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        floors.get(floor).setFloorButtonUp(value);
        guiUpdater.updateFloorButtonUp(floor);
    }

    /**
     * Gets the current logging level.
     * @return The logging level.
     */
    public int getLogging() {
        return this.loggingLevel;
    }

    /**
     * Sets the current logging level.
     * @param loggingLevel The logging level.
     */
    public void setLogging(int loggingLevel) {
        this.loggingLevel = loggingLevel;
    }

    /**
     * Gets the current clock tick.
     * @return The clock tick.
     */
    public long getClockTick() {
        return this.clockTick;
    }

    /**
     * Sets the current clock tick.
     * @param clockTick The current clock tick.
     */
    public void setClockTick(long clockTick) {
        this.clockTick = clockTick;
    }

    /**
     * Gets the current floor height.
     * @return The floor height.
     */
    public int getFloorHeight() {
        return this.floorHeight;
    }

    /**
     * Sets the gui updater instance to a new updater.
     * @param guiUpdater The new updater to use.
     */
    public void setGuiUpdater(ElevatorGuiUpdater guiUpdater) {
        this.guiUpdater = guiUpdater;
    }

    /**
     * Initializes all gui elements via the gui updater
     * when the application is first opened.
     */
    public void initialGuiUpdate() {
        for(int i = 0; i < elevators.size(); i++) {
            guiUpdater.updateCommittedDirection(i);
            guiUpdater.updateTargetFloor(i);
            guiUpdater.updateElevatorAccel(i);
            guiUpdater.updateElevatorDoorStatus(i);
            guiUpdater.updateElevatorFloor(i);
            guiUpdater.updateElevatorPosition(i);
            guiUpdater.updateElevatorSpeed(i);
            guiUpdater.updateElevatorWeight(i);
            guiUpdater.updateElevatorCapacity(i);
            guiUpdater.updateElevatorButton(i);

            for(int j = 0; j < floors.size(); j++) {
                guiUpdater.updateServicedFloors(i, j);
            }
        }

        for(int i = 0; i < floors.size(); i++) {
            guiUpdater.updateFloorButtonUp(i);
            guiUpdater.updateFloorButtonDown(i);
        }
    }

    /**
     * Sets the error message.
     * @param errorMessage The error message to show.
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        guiUpdater.updateErrorMessage();
    }

    /**
     * Gets the current error message.
     * @return The error message.
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}
