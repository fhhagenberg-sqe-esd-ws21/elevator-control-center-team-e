package at.fhhagenberg.sqe.ecc;

import sqelevator.IElevator;
import java.rmi.RemoteException;

/**
 * Class is implementing IElevatorHardwareManager. Use this class for communication with ecc-simulator or
 * real world systems.
 * @author Bernhard Wandl - s2010567008
 */
public class ElevatorHardwareManager implements IElevatorHardwareManager {

    private IElevator controller;
    private boolean isConnected;

    /**
     * Constructor of the class. Establishes a connection to low level elevator system.
     * @param controller Interface to low-level elevator system. Connection must be already established.
     * @throws IllegalArgumentException Throws exception if there is no valid connection to low-level elevator system.
     */
    public ElevatorHardwareManager(IElevator controller) throws IllegalArgumentException {
        if(controller == null)
            throw new IllegalArgumentException("Please establish connection to low-level elevator system.");

        this.controller = controller;
        this.isConnected = true;
    }

    @Override
    public boolean getIsConnected() {
        return isConnected;
    }

    @Override
    public void setIsConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    public void reconnect() throws Exception {
        if(!isConnected) {
            controller = ElevatorConnectionManager.ElevatorConnectionSetup();
            isConnected = true;
        }
    }

    @Override
    public int getCommittedDirection(int elevatorNumber) throws RemoteException {
        return controller.getCommittedDirection(elevatorNumber);
    }

    @Override
    public int getElevatorAccel(int elevatorNumber) throws RemoteException {
        return controller.getElevatorAccel(elevatorNumber);
    }

    @Override
    public boolean getElevatorButton(int elevatorNumber, int floor) throws RemoteException {
        return controller.getElevatorButton(elevatorNumber, floor);
    }

    @Override
    public int getElevatorDoorStatus(int elevatorNumber) throws RemoteException {
        return controller.getElevatorDoorStatus(elevatorNumber);
    }

    @Override
    public int getElevatorFloor(int elevatorNumber) throws RemoteException {
        return controller.getElevatorFloor(elevatorNumber);
    }

    @Override
    public int getElevatorNum() throws RemoteException {
        return controller.getElevatorNum();
    }

    @Override
    public int getElevatorPosition(int elevatorNumber) throws RemoteException {
        return controller.getElevatorPosition(elevatorNumber);
    }

    @Override
    public int getElevatorSpeed(int elevatorNumber) throws RemoteException {
        return controller.getElevatorSpeed(elevatorNumber);
    }

    @Override
    public int getElevatorWeight(int elevatorNumber) throws RemoteException {
        return controller.getElevatorWeight(elevatorNumber);
    }

    @Override
    public int getElevatorCapacity(int elevatorNumber) throws RemoteException {
        return controller.getElevatorCapacity(elevatorNumber);
    }

    @Override
    public boolean getFloorButtonDown(int floor) throws RemoteException {
        return controller.getFloorButtonDown(floor);
    }

    @Override
    public boolean getFloorButtonUp(int floor) throws RemoteException {
        return controller.getFloorButtonUp(floor);
    }

    @Override
    public int getFloorHeight() throws RemoteException {
        return controller.getFloorHeight();
    }

    @Override
    public int getFloorNum() throws RemoteException {
        return controller.getFloorNum();
    }

    @Override
    public boolean getServicesFloors(int elevatorNumber, int floor) throws RemoteException {
        return controller.getServicesFloors(elevatorNumber, floor);
    }

    @Override
    public int getTarget(int elevatorNumber) throws RemoteException {
        return controller.getTarget(elevatorNumber);
    }

    @Override
    public void setCommittedDirection(int elevatorNumber, int direction) throws RemoteException {
        controller.setCommittedDirection(elevatorNumber, direction);
    }

    @Override
    public void setServicesFloors(int elevatorNumber, int floor, boolean service) throws RemoteException {
        controller.setServicesFloors(elevatorNumber, floor, service);
    }

    @Override
    public void setTarget(int elevatorNumber, int target) throws RemoteException {
        controller.setTarget(elevatorNumber, target);
    }

    @Override
    public long getClockTick() throws RemoteException {
        return controller.getClockTick();
    }
}
