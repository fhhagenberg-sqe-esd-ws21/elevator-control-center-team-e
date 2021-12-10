package at.fhhagenberg.sqe.ecc;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

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

    public Elevator(int numberOfFloors) {
        floors = new ArrayList<>(numberOfFloors);
    }

    public void setElevatorButton(int floor, boolean value) throws IllegalArgumentException {
        if(floor >= floors.size())
            throw new IllegalArgumentException();

        floors.get(floor).setFloorRequestButton(value);
    }

    public int getCommittedDirection() {
        return committedDirection;
    }

    public int getElevatorAccel() {
        return elevatorAccel;
    }

    public int getElevatorDoorStatus() {
        return elevatorDoorStatus;
    }

    public int getElevatorFloor() {
        return elevatorFloor;
    }

    public int getElevatorPosition() {
        return elevatorPosition;
    }

    public int getElevatorSpeed() {
        return elevatorSpeed;
    }

    public int getElevatorWeight() {
        return elevatorWeight;
    }

    public int getElevatorCapacity() {
        return elevatorCapacity;
    }

    public int getTarget() {
        return target;
    }

    public boolean getElevatorButton(int floor) throws IllegalArgumentException {
        if(floor >= 0 && floor < floors.size() && floors.get(floor).isServiced())
            return floors.get(floor).isFloorRequestButton();
        else
            throw new IllegalArgumentException();
    }

    public boolean getServicesFloors(int floor) throws IllegalArgumentException {
        if(floor < 0 && floor >= floors.size())
            throw new IllegalArgumentException();

        return floors.get(floor).isServiced();
    }

    public void setCommittedDirection(int committedDirection) {
        this.committedDirection = committedDirection;
    }

    public void setElevatorAccel(int elevatorAccel) {
        this.elevatorAccel = elevatorAccel;
    }

    public void setElevatorDoorStatus(int elevatorDoorStatus) {
        this.elevatorDoorStatus = elevatorDoorStatus;
    }

    public void setElevatorFloor(int elevatorFloor) {
        this.elevatorFloor = elevatorFloor;
    }

    public void setElevatorPosition(int elevatorPosition) {
        this.elevatorPosition = elevatorPosition;
    }

    public void setElevatorSpeed(int elevatorSpeed) {
        this.elevatorSpeed = elevatorSpeed;
    }

    public void setElevatorWeight(int elevatorWeight) {
        this.elevatorWeight = elevatorWeight;
    }

    public void setElevatorCapacity(int elevatorCapacity) {
        this.elevatorCapacity = elevatorCapacity;
    }

    public void setTarget(int elevatorTargetFloor) {
        this.target = elevatorTargetFloor;
    }

    public void setServicesFloors(int floor, boolean service) throws IllegalArgumentException {
        if(floor >= 0 && floor < floors.size() && floors.get(floor).isServiced())
            floors.get(floor).setServiced(service);
        else
            throw new IllegalArgumentException();
    }
}
