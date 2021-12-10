package at.fhhagenberg.sqe.ecc;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElevatorModel {

    private List<Elevator> elevators;
    private List<Floor> floors;

    private long clockTick;
    private int floorHeight;
    private int loggingLevel;

    public ElevatorModel(int numberOfElevators, int numberOfFloors, int floorHeight) {
        elevators = new ArrayList<>(numberOfElevators);
        floors = new ArrayList<>(numberOfFloors);
        Map<Integer, Floor> servicedFloors = new HashMap<>();
        this.floorHeight = floorHeight;
        clockTick = 0;
        loggingLevel = 0;
    }

    public int getNumOfElevators() {
        return elevators.size();
    }

    public int getNumOfFloors() {
        return floors.size();
    }

    public void setCommittedDirection(int elevatorNumber, int direction) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setCommittedDirection(direction);
    }

    public int getCommittedDirection(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getCommittedDirection();
    }

    public boolean getServicesFloors(int elevatorNumber, int floor) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size() || floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getServicesFloors(floor);
    }

    public void setServicesFloors(int elevatorNumber, int floor, boolean service) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size() || floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setServicesFloors(floor, service);
    }

    public int getTarget(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getTarget();
    }

    public void setTarget(int elevatorNumber, int target) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size() || target < 0 || target >= floors.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setTarget(target);
    }

    public void setElevatorAccel(int elevatorNumber, int value) throws IllegalArgumentException {
        if(elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setElevatorAccel(value);
    }

    public void setElevatorButton(int elevatorNumber, int floor, boolean value) throws IllegalArgumentException {
        if(elevatorNumber >= elevators.size() || floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setElevatorButton(floor, value);
    }

    public void setElevatorDoorStatus(int elevatorNumber, int doorStatus) throws IllegalArgumentException {
        if(elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setElevatorDoorStatus(doorStatus);
    }

    public void setElevatorFloor(int elevatorNumber, int floor) throws IllegalArgumentException {
        if(elevatorNumber >= elevators.size() || floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setElevatorFloor(floor);
    }

    public void setElevatorPosition(int elevatorNumber, int position) throws IllegalArgumentException {
        if(elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setElevatorPosition(position);
    }

    public void setElevatorSpeed(int elevatorNumber, int speed) throws IllegalArgumentException {
        if(elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setElevatorSpeed(speed);
    }

    public void setElevatorWeight(int elevatorNumber, int weight) throws IllegalArgumentException {
        if(elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setElevatorWeight(weight);
    }

    public void setElevatorCapacity(int elevatorNumber, int capacity) throws IllegalArgumentException {
        if(elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setElevatorCapacity(capacity);
    }

    public void setFloorButtonDown(int floor, boolean value) throws IllegalArgumentException {
        if(floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        floors.get(floor).setFloorButtonDown(value);
    }

    public void setFloorButtonUp(int floor, boolean value) throws IllegalArgumentException {
        if(floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        floors.get(floor).setFloorButtonUp(value);
    }

    public void setFloorHeight(int floorHeight) {
        this.floorHeight = floorHeight;
    }

    public void setLogging(int loggingLevel) {
        this.loggingLevel = loggingLevel;
    }

    public void setClockTick(long clockTick) {
        this.clockTick = clockTick;
    }
}
