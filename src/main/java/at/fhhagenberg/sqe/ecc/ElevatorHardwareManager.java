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

    /**
     * Gets the current connection status.
     * @return true if connected, false otherwise.
     */
    @Override
    public boolean getIsConnected() {
        return isConnected;
    }

    /**
     * Sets the current connection status.
     * @param isConnected If the connection is established or not.
     */
    @Override
    public void setIsConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    /**
     * Reconnect a lost connection.
     * @throws Exception If reconnection is not successful, e.g. ConnectException if service is not active.
     */
    public void reconnect() throws Exception {
        if(!isConnected) {
            controller = ElevatorConnectionManager.ElevatorConnectionSetup();
            isConnected = true;
        }
    }

    /**
     * Gets the current commited direction.
     * @param elevatorNumber - elevator number whose committed direction is being retrieved
     * @return the commited direction according to the enum.
     * @throws RemoteException If query in the backend was not successful.
     */
    @Override
    public int getCommittedDirection(int elevatorNumber) throws RemoteException {
        return controller.getCommittedDirection(elevatorNumber);
    }

    /**
     * Gets the current acceleration.
     * @param elevatorNumber - elevator number whose acceleration is being retrieved
     * @return The acceleration of the elevator.
     * @throws RemoteException If query in the backend was not successful.
     */
    @Override
    public int getElevatorAccel(int elevatorNumber) throws RemoteException {
        return controller.getElevatorAccel(elevatorNumber);
    }

    /**
     * Gets the current status of a specific elevator button
     * @param elevatorNumber - elevator number whose button status is being retrieved
     * @param floor          - floor number button being checked on the selected elevator
     * @return True if button is active, false otherwise.
     * @throws RemoteException If query in the backend was not successful.
     */
    @Override
    public boolean getElevatorButton(int elevatorNumber, int floor) throws RemoteException {
        return controller.getElevatorButton(elevatorNumber, floor);
    }

    /**
     * Gets the current door status.
     * @param elevatorNumber - elevator number whose door status is being retrieved
     * @return The door status.
     * @throws RemoteException If query in the backend was not successful.
     */
    @Override
    public int getElevatorDoorStatus(int elevatorNumber) throws RemoteException {
        return controller.getElevatorDoorStatus(elevatorNumber);
    }

    /**
     * Gets the current floor of the elevator.
     * @param elevatorNumber - elevator number whose location is being retrieved
     * @return The current floor.
     * @throws RemoteException If query in the backend was not successful.
     */
    @Override
    public int getElevatorFloor(int elevatorNumber) throws RemoteException {
        return controller.getElevatorFloor(elevatorNumber);
    }

    /**
     * Gets the number of elevators.
     * @return The number of elevators in the system.
     * @throws RemoteException If query in the backend was not successful.
     */
    @Override
    public int getElevatorNum() throws RemoteException {
        return controller.getElevatorNum();
    }

    /**
     * Gets the current position.
     * @param elevatorNumber - elevator number whose location is being retrieved
     * @return The current position of the elevator.
     * @throws RemoteException If query in the backend was not successful.
     */
    @Override
    public int getElevatorPosition(int elevatorNumber) throws RemoteException {
        return controller.getElevatorPosition(elevatorNumber);
    }

    /**
     * Gets the current speed of an elevator.
     * @param elevatorNumber - elevator number whose speed is being retrieved
     * @return The current speed of the elevator.
     * @throws RemoteException If query in the backend was not successful.
     */
    @Override
    public int getElevatorSpeed(int elevatorNumber) throws RemoteException {
        return controller.getElevatorSpeed(elevatorNumber);
    }

    /**
     * Gets the current weight.
     * @param elevatorNumber - elevator number whose service is being retrieved
     * @return The current weight of the elevator.
     * @throws RemoteException If query in the backend was not successful.
     */
    @Override
    public int getElevatorWeight(int elevatorNumber) throws RemoteException {
        return controller.getElevatorWeight(elevatorNumber);
    }

    /**
     * Gets the current capacity.
     * @param elevatorNumber - elevator number whose service is being retrieved
     * @return The current capacity of the elevator.
     * @throws RemoteException If query in the backend was not successful.
     */
    @Override
    public int getElevatorCapacity(int elevatorNumber) throws RemoteException {
        return controller.getElevatorCapacity(elevatorNumber);
    }

    /**
     * Gets the current floor button down status.
     * @param floor - floor number whose Down button status is being retrieved
     * @return True if down is selected, false otherwise.
     * @throws RemoteException If query in the backend was not successful.
     */
    @Override
    public boolean getFloorButtonDown(int floor) throws RemoteException {
        return controller.getFloorButtonDown(floor);
    }

    /**
     * Gets the current floor button up status.
     * @param floor - floor number whose up button status is being retrieved
     * @return True if up is selected, false otherwise.
     * @throws RemoteException If query in the backend was not successful.
     */
    @Override
    public boolean getFloorButtonUp(int floor) throws RemoteException {
        return controller.getFloorButtonUp(floor);
    }

    /**
     * Gets the floor height.
     * @return The floor height of the system.
     * @throws RemoteException If query in the backend was not successful.
     */
    @Override
    public int getFloorHeight() throws RemoteException {
        return controller.getFloorHeight();
    }

    /**
     * Gets the number of floors.
     * @return The number of floors in the system.
     * @throws RemoteException If query in the backend was not successful.
     */
    @Override
    public int getFloorNum() throws RemoteException {
        return controller.getFloorNum();
    }

    /**
     * Gets the floor serviced status.
     * @param elevatorNumber elevator number whose service is being retrieved
     * @param floor          floor whose service status by the specified elevator is being retrieved
     * @return True if floor is serviced, false otherwise.
     * @throws RemoteException If query in the backend was not successful.
     */
    @Override
    public boolean getServicesFloors(int elevatorNumber, int floor) throws RemoteException {
        return controller.getServicesFloors(elevatorNumber, floor);
    }

    /**
     * Gets the current target.
     * @param elevatorNumber elevator number whose target floor is being retrieved
     * @return The current target floor of the elevator.
     * @throws RemoteException If query in the backend was not successful.
     */
    @Override
    public int getTarget(int elevatorNumber) throws RemoteException {
        return controller.getTarget(elevatorNumber);
    }

    /**
     * Sets the commited direction.
     * @param elevatorNumber elevator number whose committed direction is being set
     * @param direction      direction being set where up=0, down=1 and uncommitted=2
     * @throws RemoteException If query in the backend was not successful.
     */
    @Override
    public void setCommittedDirection(int elevatorNumber, int direction) throws RemoteException {
        controller.setCommittedDirection(elevatorNumber, direction);
    }

    /**
     * Sets the serviced status for a floor.
     * @param elevatorNumber elevator number whose service is being defined
     * @param floor          floor whose service by the specified elevator is being set
     * @param service        indicates whether the floor is serviced by the specified elevator (yes=true,no=false)
     * @throws RemoteException If query in the backend was not successful.
     */
    @Override
    public void setServicesFloors(int elevatorNumber, int floor, boolean service) throws RemoteException {
        controller.setServicesFloors(elevatorNumber, floor, service);
    }

    /**
     * Sets the target.
     * @param elevatorNumber elevator number whose target floor is being set
     * @param target         floor number which the specified elevator is to target
     * @throws RemoteException If query in the backend was not successful.
     */
    @Override
    public void setTarget(int elevatorNumber, int target) throws RemoteException {
        controller.setTarget(elevatorNumber, target);
    }

    /**
     * Gets the current clock tick of the system.
     * @return The current clock tick.
     * @throws RemoteException If query in the backend was not successful.
     */
    @Override
    public long getClockTick() throws RemoteException {
        return controller.getClockTick();
    }
}
