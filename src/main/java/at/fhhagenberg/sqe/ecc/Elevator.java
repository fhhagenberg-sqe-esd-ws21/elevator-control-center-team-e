package at.fhhagenberg.sqe.ecc;

import sqelevator.IElevator;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a elevator within a building. Stores elevator information used in ECC.
 * @author Markus Lindner - s2010567018
 */
public class Elevator {

    private int committedDirection;
    private int elevatorAccel;
    private int elevatorDoorStatus;
    private int elevatorFloor;
    private int elevatorPosition;
    private int elevatorSpeed;
    private int elevatorWeight;
    private int elevatorCapacity;
    private int target;

    private List<Floor> floors;

    /**
     * Constructor of class Elevator.
     * @param numberOfFloors Represents the number of floors the elevator services.
     */
    public Elevator(int numberOfFloors) {
        floors = new ArrayList<>();
        for(int i = 0; i < numberOfFloors; ++i) {
            floors.add(new Floor(false, false, false, true));
        }
    }

    /**
     * Sets the status of a floor request button on a specified elevator (on/off).
     * @param floor Number of the floor requesting.
     * @param value Set button to on or off.
     * @throws IllegalArgumentException thrown if floor does not exist
     */
    public void setElevatorButton(int floor, boolean value) throws IllegalArgumentException {
        if(floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        floors.get(floor).setFloorRequestButton(value);
    }

    /**
     * Returns value of commited direction (up/down/uncomitted).
     * @return Value of commited direction.
     */
    public int getCommittedDirection() {
        return committedDirection;
    }

    /**
     * Returns value of elevator acceleration.
     * @return The actual acceleration value, given in feet per second**2.
     */
    public int getElevatorAccel() {
        return elevatorAccel;
    }

    /**
     * Returns the status of elevator doors.
     * @return Open, Opening, Closing, Closed.
     */
    public int getElevatorDoorStatus() {
        return elevatorDoorStatus;
    }

    /**
     * Returns the current location of the elevator.
     * @return Current location to the nearest floor.
     */
    public int getElevatorFloor() {
        return elevatorFloor;
    }

    /**
     * Returns the current position of the elevator.
     * @return Current position in feet from the bottom of the building.
     */
    public int getElevatorPosition() {
        return elevatorPosition;
    }

    /**
     * Returns the current speed of the elevator.
     * @return Current speed of elevator in feet per second.
     */
    public int getElevatorSpeed() {
        return elevatorSpeed;
    }

    /**
     * Returns the current weight of the elevator.
     * @return Current weight of passengers using the elevator.
     */
    public int getElevatorWeight() {
        return elevatorWeight;
    }

    /**
     * Returns the capacity of the elevator.
     * @return Capacity of the elevator, indicates how many people can be transported at the same time.
     */
    public int getElevatorCapacity() {
        return elevatorCapacity;
    }

    /**
     * Returns current target of the elevator.
     * @return Target floor of the elevator.
     */
    public int getTarget() {
        return target;
    }

    /**
     * Provides the status of a floor request button on the elevator.
     * @param floor The number of the floor from which the button status is requested.
     * @return True if button is activated, false otherwise.
     * @throws IllegalArgumentException thrown if floor does not exist
     */
    public boolean getElevatorButton(int floor) throws IllegalArgumentException {
        if(floor >= 0 && floor < floors.size())
            return floors.get(floor).isFloorRequestButton();
        else
            throw new IllegalArgumentException();
    }

    /**
     * Provides information if the elevator services the specified floor.
     * @param floor The number of the floor to be checked if it is serviced by the elevator.
     * @return True if the floor is serviced, false otherwise.
     * @throws IllegalArgumentException thrown if floor does not exist
     */
    public boolean getServicesFloors(int floor) throws IllegalArgumentException {
        if(floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        return floors.get(floor).isServiced();
    }

    /**
     * Setter for committed direction.
     * @param committedDirection The value to be set.
     * @throws IllegalArgumentException thrown if the commited direction is invalid (neither up/down nor uncommited)
     */
    public void setCommittedDirection(int committedDirection) throws IllegalArgumentException {
        if (committedDirection < IElevator.ELEVATOR_DIRECTION_UP || committedDirection > IElevator.ELEVATOR_DIRECTION_UNCOMMITTED) {
            throw new IllegalArgumentException("commited direction is invalid");
        }
        this.committedDirection = committedDirection;
    }

    /**
     * Setter for elevator acceleration.
     * @param elevatorAccel The value to be set, given in feet per second**2.
     */
    public void setElevatorAccel(int elevatorAccel) {
        this.elevatorAccel = elevatorAccel;
    }

    /**
     * Setter for elevator door status.
     * @param elevatorDoorStatus The value to be set, open, opening, closing or closed.
     */
    public void setElevatorDoorStatus(int elevatorDoorStatus) {
        this.elevatorDoorStatus = elevatorDoorStatus;
    }

    /**
     * Setter for elveator floor.
     * @param elevatorFloor The value to be set, location to the nearest floor.
     */
    public void setElevatorFloor(int elevatorFloor) {
        this.elevatorFloor = elevatorFloor;
    }

    /**
     * Setter for elevator position.
     * @param elevatorPosition The value to be set, given in feet from the bottom of the building.
     */
    public void setElevatorPosition(int elevatorPosition) {
        this.elevatorPosition = elevatorPosition;
    }

    /**
     * Setter for elevator speed.
     * @param elevatorSpeed The value to be set, given in feet per second.
     */
    public void setElevatorSpeed(int elevatorSpeed) {
        this.elevatorSpeed = elevatorSpeed;
    }

    /**
     * Setter for elevator weight.
     * @param elevatorWeight The value to be set, representing the weight of passengers.
     */
    public void setElevatorWeight(int elevatorWeight) {
        this.elevatorWeight = elevatorWeight;
    }

    /**
     * Setter for elevator capacity.
     * @param elevatorCapacity The value to be set, elevator can carry given number of people.
     */
    public void setElevatorCapacity(int elevatorCapacity) {
        this.elevatorCapacity = elevatorCapacity;
    }

    /**
     * Setter for target.
     * @param elevatorTargetFloor The target floor the elevator is traveling to.
     */
    public void setTarget(int elevatorTargetFloor) {
        this.target = elevatorTargetFloor;
    }

    /**
     * Setter to specify which floors are serviced by the elevator.
     * @param floor The number of the floor.
     * @param service If true, elevator will start to service given floor. If false, elevator will stop servicing the given floor.
     * @throws IllegalArgumentException thrown if floor does not exist
     */
    public void setServicesFloors(int floor, boolean service) throws IllegalArgumentException {
        if(floor >= 0 && floor < floors.size())
            floors.get(floor).setServiced(service);
        else
            throw new IllegalArgumentException();
    }
}
