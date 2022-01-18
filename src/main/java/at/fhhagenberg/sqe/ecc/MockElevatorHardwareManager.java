package at.fhhagenberg.sqe.ecc;

import sqelevator.IElevator;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Impelements IElevatorHardwareManager Interface. This class is only used for test purposes. Most of the
 * Getter-Methods returning simple dummy values for testing purposes.
 * WARNING: DO NOT USE THIS CLASS IN PRODUCTION CODE.
 * @author Alexander Kemptner - s2010567016
 */
public class MockElevatorHardwareManager implements IElevatorHardwareManager {

    private boolean isConnected;
    private boolean isElevatorReachable;
    private List<List<Boolean>> servicesFloors;
    private List<Integer> commitedDirecton;
    private List<Integer> targetFloor;

    private static final int constantNumberOfElevators = 4;
    private static final int constantNumberOfFloors = 10;

    /**
     * Constructor of the class. Initializes some dummy data needed for testing purposes.
     */
    public MockElevatorHardwareManager() {
        servicesFloors = new ArrayList<>();
        commitedDirecton = new ArrayList<>();
        targetFloor = new ArrayList<>();

        for (int i = 0; i < constantNumberOfElevators; i++) {
            List<Boolean> tmp = new ArrayList<>();
            for(int j = 0; j < constantNumberOfFloors; j++) {
                tmp.add(false);
            }
            servicesFloors.add(tmp);
            commitedDirecton.add(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED);
            targetFloor.add(0);
        }

        isConnected = true;
        this.isElevatorReachable = true;
    }
    
    /**
     * Gets the current state of the Elevator connection.
     * @param value - value if elevator is reachable or not
     */
    public void setIsElevatorReachable(boolean value) {
    	this.isElevatorReachable = value;
    }

    /**
     * Reconnect a lost connection.
     * Only prints a message to stdout.
     */
    @Override
    public void reconnect() {
    	if (this.isElevatorReachable) {
    		this.isConnected = true;
    	}
    }

    /**
     * Gets the current connection status.
     * @return Always true.
     */
    @Override
    public boolean getIsConnected() {
        return this.isConnected;
    }

    /**
     * Sets the current connection status.
     * @param isConnected Always true.
     */
    @Override
    public void setIsConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    /**
     * Gets the current commited direction.
     * @param elevatorNumber - elevator number whose committed direction is being retrieved
     * @return Always uncommitted.
     * @throws RemoteException Not used.
     */
    @Override
    public int getCommittedDirection(int elevatorNumber) throws RemoteException {
    	if (!this.isConnected) {
    		throw new RemoteException();
    	}
        return commitedDirecton.get(elevatorNumber);
    }

    /**
     * Gets the current acceleration.
     * @param elevatorNumber - elevator number whose acceleration is being retrieved
     * @return A fixed value varied by elevator number.
     */
    @Override
    public int getElevatorAccel(int elevatorNumber) throws RemoteException {
    	if (!this.isConnected) {
    		throw new RemoteException();
    	}
        return 536 + elevatorNumber;
    }

    /**
     * Gets the current status of a specific elevator button
     * @param elevatorNumber - elevator number whose button status is being retrieved
     * @param floor          - floor number button being checked on the selected elevator
     * @return Always true.
     * @throws RemoteException Not used.
     */
    @Override
    public boolean getElevatorButton(int elevatorNumber, int floor) throws RemoteException {
    	if (!this.isConnected) {
    		throw new RemoteException();
    	}
        return true;
    }

    /**
     * Gets the current door status.
     * @param elevatorNumber - elevator number whose door status is being retrieved
     * @return Always 3.
     * @throws RemoteException Not used.
     */
    @Override
    public int getElevatorDoorStatus(int elevatorNumber) throws RemoteException {
    	if (!this.isConnected) {
    		throw new RemoteException();
    	}
        return 3;
    }

    /**
     * Gets the current floor of the elevator.
     * @param elevatorNumber - elevator number whose location is being retrieved
     * @return Always 2.
     * @throws RemoteException Not used.
     */
    @Override
    public int getElevatorFloor(int elevatorNumber) throws RemoteException {
    	if (!this.isConnected) {
    		throw new RemoteException();
    	}
        return 2;
    }

    /**
     * Gets the number of elevators.
     * @return A fixed value.
     * @throws RemoteException Not used.
     */
    @Override
    public int getElevatorNum() throws RemoteException {
    	if (!this.isConnected) {
    		throw new RemoteException();
    	}
        return constantNumberOfElevators;
    }

    /**
     * Gets the current position.
     * @param elevatorNumber - elevator number whose location is being retrieved
     * @return Always 3.
     * @throws RemoteException Not used.
     */
    @Override
    public int getElevatorPosition(int elevatorNumber) throws RemoteException {
    	if (!this.isConnected) {
    		throw new RemoteException();
    	}
        return 3;
    }

    /**
     * Gets the current speed of an elevator.
     * @param elevatorNumber - elevator number whose speed is being retrieved
     * @return A fixed value varied by elevator number.
     * @throws RemoteException Not used.
     */
    @Override
    public int getElevatorSpeed(int elevatorNumber) throws RemoteException {
    	if (!this.isConnected) {
    		throw new RemoteException();
    	}
        return 789 + elevatorNumber;
    }

    /**
     * Gets the current weight.
     * @param elevatorNumber - elevator number whose service is being retrieved
     * @return A fixed value varied by elevator number.
     * @throws RemoteException Not used.
     */
    @Override
    public int getElevatorWeight(int elevatorNumber) throws RemoteException {
    	if (!this.isConnected) {
    		throw new RemoteException();
    	}
        return 444 + elevatorNumber;
    }

    /**
     * Gets the current capacity.
     * @param elevatorNumber - elevator number whose service is being retrieved
     * @return A fixed value varied by elevator number.
     * @throws RemoteException Not used.
     */
    @Override
    public int getElevatorCapacity(int elevatorNumber) throws RemoteException {
    	if (!this.isConnected) {
    		throw new RemoteException();
    	}
        return 1234 + elevatorNumber;
    }

    /**
     * Gets the current floor button down status.
     * @param floor - floor number whose Down button status is being retrieved
     * @return Always false.
     * @throws RemoteException Not used.
     */
    @Override
    public boolean getFloorButtonDown(int floor) throws RemoteException {
    	if (!this.isConnected) {
    		throw new RemoteException();
    	}
        return false;
    }

    /**
     * Gets the current floor button up status.
     * @param floor - floor number whose up button status is being retrieved
     * @return Always false.
     * @throws RemoteException Not used.
     */
    @Override
    public boolean getFloorButtonUp(int floor) throws RemoteException {
    	if (!this.isConnected) {
    		throw new RemoteException();
    	}
        return false;
    }

    /**
     * Gets the floor height.
     * @return A fixed value.
     * @throws RemoteException Not used.
     */
    @Override
    public int getFloorHeight() throws RemoteException {
    	if (!this.isConnected) {
    		throw new RemoteException();
    	}
        return 4711;
    }

    /**
     * Gets the number of floors.
     * @return A fixed value.
     * @throws RemoteException Not used.
     */
    @Override
    public int getFloorNum() throws RemoteException {
    	if (!this.isConnected) {
    		throw new RemoteException();
    	}
        return constantNumberOfFloors;
    }

    /**
     * Gets the floor serviced status.
     * @param elevatorNumber elevator number whose service is being retrieved
     * @param floor          floor whose service status by the specified elevator is being retrieved
     * @return Always false.
     * @throws RemoteException Not used.
     */
    @Override
    public boolean getServicesFloors(int elevatorNumber, int floor) throws RemoteException {
    	if (!this.isConnected) {
    		throw new RemoteException();
    	}
        return servicesFloors.get(elevatorNumber).get(floor);
    }

    /**
     * Gets the current target.
     * @param elevatorNumber elevator number whose target floor is being retrieved
     * @return Always zero.
     * @throws RemoteException Not used.
     */
    @Override
    public int getTarget(int elevatorNumber) throws RemoteException {
    	if (!this.isConnected) {
    		throw new RemoteException();
    	}
        return targetFloor.get(elevatorNumber);
    }

    /**
     * Sets the commited direction.
     * @param elevatorNumber elevator number whose committed direction is being set
     * @param direction      direction being set where up=0, down=1 and uncommitted=2
     * @throws RemoteException Not used.
     */
    @Override
    public void setCommittedDirection(int elevatorNumber, int direction) throws RemoteException {
    	if (!this.isConnected) {
    		throw new RemoteException();
    	}
        commitedDirecton.set(elevatorNumber, direction);
    }

    /**
     * Sets the serviced status for a floor.
     * @param elevatorNumber elevator number whose service is being defined
     * @param floor          floor whose service by the specified elevator is being set
     * @param service        indicates whether the floor is serviced by the specified elevator (yes=true,no=false)
     * @throws RemoteException Not used.
     */
    @Override
    public void setServicesFloors(int elevatorNumber, int floor, boolean service) throws RemoteException {
    	if (!this.isConnected) {
    		throw new RemoteException();
    	}
        servicesFloors.get(elevatorNumber).set(floor, service);
    }

    /**
     * Sets the target.
     * @param elevatorNumber elevator number whose target floor is being set
     * @param target         floor number which the specified elevator is to target
     * @throws RemoteException Not used.
     */
    @Override
    public void setTarget(int elevatorNumber, int target) throws RemoteException {
    	if (!this.isConnected) {
    		throw new RemoteException();
    	}
        targetFloor.set(elevatorNumber, target);
    }

    /**
     * Gets the current clock tick of the system.
     * @return A fixed value of 123456789.
     * @throws RemoteException Not used.
     */
    @Override
    public long getClockTick() throws RemoteException {
    	if (!this.isConnected) {
    		throw new RemoteException();
    	}
        return 123456789;
    }
}
