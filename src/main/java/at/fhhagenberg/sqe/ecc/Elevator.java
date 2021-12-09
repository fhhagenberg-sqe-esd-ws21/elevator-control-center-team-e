package at.fhhagenberg.sqe.ecc;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public class Elevator {

    private int committedDirection;
    private int elevatorAccel;
    private int elevatorDoorStatus;
    private int elevatorFloor;
    private int elevatorPosition;
    private int elevatorSpeed;
    private int elevatorWeight;
    private int elevatorCapacity;
    private int elevatorTargetFloor;

    private Map<Integer, Floor> servicedFloors;

    public Elevator(int committedDirection, int elevatorAccel, int elevatorDoorStatus, int elevatorFloor, int elevatorPosition, int elevatorSpeed, int elevatorWeight, int elevatorCapacity, Map<Integer, Floor> servicedFloors, int elevatorTargetFloor) {
        this.committedDirection = committedDirection;
        this.elevatorAccel = elevatorAccel;
        this.elevatorDoorStatus = elevatorDoorStatus;
        this.elevatorFloor = elevatorFloor;
        this.elevatorPosition = elevatorPosition;
        this.elevatorSpeed = elevatorSpeed;
        this.elevatorWeight = elevatorWeight;
        this.elevatorCapacity = elevatorCapacity;
        this.servicedFloors = servicedFloors;
        this.elevatorTargetFloor = elevatorTargetFloor;
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

    public int getElevatorTargetFloor() {
        return elevatorTargetFloor;
    }

    public boolean getElevatorButton(int floor) {
        if(!servicedFloors.containsKey(floor))
            return false; //TODO: exception?

        return servicedFloors.get(floor).isFloorRequestButton();
    }

    public boolean getServicesFloors(int floor) {
        return servicedFloors.containsKey(floor);
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

    public void setElevatorTargetFloor(int elevatorTargetFloor) {
        this.elevatorTargetFloor = elevatorTargetFloor;
    }

    public void setServicesFloor(int floorNum, boolean service, Floor floor) {
        if(service && !servicedFloors.containsKey(floor)) {
            servicedFloors.put(floorNum, floor);
        }
        else if(!service && servicedFloors.containsKey(floor)) {
            servicedFloors.remove(floorNum);
        }
    }
}
