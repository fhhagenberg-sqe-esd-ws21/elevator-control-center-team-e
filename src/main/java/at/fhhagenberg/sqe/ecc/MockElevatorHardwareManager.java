package at.fhhagenberg.sqe.ecc;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class MockElevatorHardwareManager implements IElevatorHardwareManager {

    private List<List<Boolean>> servicesFloors;
    private List<Integer> commitedDirecton;
    private List<Integer> targetFloor;

    private static final int constantNumberOfElevators = 4;
    private static final int constantNumberOfFloors = 10;

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
    }

    @Override
    public int getCommittedDirection(int elevatorNumber) throws RemoteException {
        return commitedDirecton.get(elevatorNumber);
    }

    @Override
    public int getElevatorAccel(int elevatorNumber) throws RemoteException {
        return 536 + elevatorNumber;
    }

    @Override
    public boolean getElevatorButton(int elevatorNumber, int floor) throws RemoteException {
        return true;
    }

    @Override
    public int getElevatorDoorStatus(int elevatorNumber) throws RemoteException {
        return 3;
    }

    @Override
    public int getElevatorFloor(int elevatorNumber) throws RemoteException {
        return 2;
    }

    @Override
    public int getElevatorNum() throws RemoteException {
        return constantNumberOfElevators;
    }

    @Override
    public int getElevatorPosition(int elevatorNumber) throws RemoteException {
        return 3;
    }

    @Override
    public int getElevatorSpeed(int elevatorNumber) throws RemoteException {
        return 789 + elevatorNumber;
    }

    @Override
    public int getElevatorWeight(int elevatorNumber) throws RemoteException {
        return 444 + elevatorNumber;
    }

    @Override
    public int getElevatorCapacity(int elevatorNumber) throws RemoteException {
        return 1234 + elevatorNumber;
    }

    @Override
    public boolean getFloorButtonDown(int floor) throws RemoteException {
        return false;
    }

    @Override
    public boolean getFloorButtonUp(int floor) throws RemoteException {
        return false;
    }

    @Override
    public int getFloorHeight() throws RemoteException {
        return 4711;
    }

    @Override
    public int getFloorNum() throws RemoteException {
        return constantNumberOfFloors;
    }

    @Override
    public boolean getServicesFloors(int elevatorNumber, int floor) throws RemoteException {

        if(elevatorNumber == 0 && floor == 0) {
            System.out.print("Serviced: ");
            System.out.println(servicesFloors.get(elevatorNumber).get(floor));
        }

        return servicesFloors.get(elevatorNumber).get(floor);
    }

    @Override
    public int getTarget(int elevatorNumber) throws RemoteException {
        return targetFloor.get(elevatorNumber);
    }

    @Override
    public void setCommittedDirection(int elevatorNumber, int direction) throws RemoteException {
        commitedDirecton.set(elevatorNumber, direction);
    }

    @Override
    public void setServicesFloors(int elevatorNumber, int floor, boolean service) throws RemoteException {
        servicesFloors.get(elevatorNumber).set(floor, service);
    }

    @Override
    public void setTarget(int elevatorNumber, int target) throws RemoteException {
        targetFloor.set(elevatorNumber, target);
    }

    @Override
    public long getClockTick() throws RemoteException {
        return 123456789;
    }

    public void wrappedSetServicesFloors(int elevatorNumber, int floor, boolean service) {
        try {
            setServicesFloors(elevatorNumber, floor, service);
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }

        if(elevatorNumber == 0 && floor == 0) {
            System.out.print("Serviced: ");
            System.out.println(servicesFloors.get(elevatorNumber).get(floor));
        }
    }

    public void wrappedSetTarget(int elevatorNumber, int target) {
        try {
            setTarget(elevatorNumber, target);
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }
    }

    public void wrappedSetCommittedDirection(int elevatorNumber, int direction) {
        try {
            setCommittedDirection(elevatorNumber, direction);
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }
    }
}
