package at.fhhagenberg.sqe.ecc;

import javafx.application.Platform;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
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
        guiUpdater.updateCommittedDirection(elevatorNumber);
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
        guiUpdater.updateServicedFloors(elevatorNumber, floor);
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
        guiUpdater.updateTargetFloor(elevatorNumber);
    }
    
    public int getElevatorAccel(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber >= elevators.size() || elevatorNumber < 0)
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getElevatorAccel();
    }

    public void setElevatorAccel(int elevatorNumber, int value) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setElevatorAccel(value);
        guiUpdater.updateElevatorAccel(elevatorNumber);
    }
    
    public boolean getElevatorButton(int elevatorNumber, int floor) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size() || floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getElevatorButton(floor);
    }

    public void setElevatorButton(int elevatorNumber, int floor, boolean value) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size() || floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setElevatorButton(floor, value);
        guiUpdater.updateElevatorButton(elevatorNumber);
    }
    
    public int getElevatorDoorStatus(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getElevatorDoorStatus();
    }

    public void setElevatorDoorStatus(int elevatorNumber, int doorStatus) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setElevatorDoorStatus(doorStatus);
        guiUpdater.updateElevatorDoorStatus(elevatorNumber);
    }
    
    public int getElevatorFloor(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getElevatorFloor();
    }

    public void setElevatorFloor(int elevatorNumber, int floor) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size() || floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setElevatorFloor(floor);
        guiUpdater.updateElevatorFloor(elevatorNumber);
    }
    
    public int getElevatorPosition(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber >= elevators.size() || elevatorNumber < 0)
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getElevatorPosition();
    }

    public void setElevatorPosition(int elevatorNumber, int position) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setElevatorPosition(position);
        guiUpdater.updateElevatorPosition(elevatorNumber);
    }
    
    public int getElevatorSpeed(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getElevatorSpeed();
    }

    public void setElevatorSpeed(int elevatorNumber, int speed) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setElevatorSpeed(speed);
        guiUpdater.updateElevatorSpeed(elevatorNumber);
    }
    
    public int getElevatorWeight(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getElevatorWeight();
    }

    public void setElevatorWeight(int elevatorNumber, int weight) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setElevatorWeight(weight);
        guiUpdater.updateElevatorWeight(elevatorNumber);
    }
    
    public int getElevatorCapacity(int elevatorNumber) throws IllegalArgumentException {
        if(elevatorNumber >= elevators.size() || elevatorNumber < 0)
            throw new IllegalArgumentException();

        return elevators.get(elevatorNumber).getElevatorCapacity();
    }

    public void setElevatorCapacity(int elevatorNumber, int capacity) throws IllegalArgumentException {
        if(elevatorNumber < 0 || elevatorNumber >= elevators.size())
            throw new IllegalArgumentException();

        elevators.get(elevatorNumber).setElevatorCapacity(capacity);
        guiUpdater.updateElevatorCapacity(elevatorNumber);
    }
    
    public boolean getFloorButtonDown(int floor) throws IllegalArgumentException {
        if(floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        return floors.get(floor).isFloorButtonDown();
    }

    public void setFloorButtonDown(int floor, boolean value) throws IllegalArgumentException {
        if(floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        floors.get(floor).setFloorButtonDown(value);
        guiUpdater.updateFloorButtonDown(floor);
    }
    
    public boolean getFloorButtonUp(int floor) throws IllegalArgumentException {
        if(floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        return floors.get(floor).isFloorButtonUp();
    }

    public void setFloorButtonUp(int floor, boolean value) throws IllegalArgumentException {
        if(floor < 0 || floor >= floors.size())
            throw new IllegalArgumentException();

        floors.get(floor).setFloorButtonUp(value);
        guiUpdater.updateFloorButtonUp(floor);
    }
    
    public int getLogging() {
        return this.loggingLevel;
    }

    public void setLogging(int loggingLevel) {
        this.loggingLevel = loggingLevel;
    }
    
    public long getClockTick() {
        return this.clockTick;
    }

    public void setClockTick(long clockTick) {
        this.clockTick = clockTick;
    }
    
    public int getFloorHeight() {
        return this.floorHeight;
    }

    public void setGuiUpdater(ElevatorGuiUpdater guiUpdater) {
        this.guiUpdater = guiUpdater;
    }

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

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        guiUpdater.updateErrorMessage();
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
