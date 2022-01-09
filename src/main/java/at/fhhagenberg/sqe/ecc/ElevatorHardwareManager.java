package at.fhhagenberg.sqe.ecc;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ElevatorHardwareManager implements IElevator {

    private IElevator controller;

    public ElevatorHardwareManager() {
//        try {
//            controller = (IElevator) Naming.lookup("rmi://host.docker.internal/ElevatorSim");
//        }
//        catch (MalformedURLException e) {
//            System.out.println(e.getMessage());
//        } catch (NotBoundException e) {
//            System.out.println(e.getMessage());
//        } catch (RemoteException e) {
//            System.out.println(e.getMessage());
//        }
    }

    @Override
    public int getCommittedDirection(int elevatorNumber) throws RemoteException {

        //return controller.getCommittedDirection(elevatorNumber);
        return 2;
    }

    @Override
    public int getElevatorAccel(int elevatorNumber) throws RemoteException {
        //return controller.getElevatorAccel(elevatorNumber);
        return 536 + elevatorNumber;
    }

    @Override
    public boolean getElevatorButton(int elevatorNumber, int floor) throws RemoteException {
        //return controller.getElevatorButton(elevatorNumber, floor);
        return true;
    }

    @Override
    public int getElevatorDoorStatus(int elevatorNumber) throws RemoteException {
        //return controller.getElevatorDoorStatus(elevatorNumber);
        return 3;
    }

    @Override
    public int getElevatorFloor(int elevatorNumber) throws RemoteException {
        //return controller.getElevatorFloor(elevatorNumber);
        return 2;
    }

    @Override
    public int getElevatorNum() throws RemoteException {
        //return controller.getElevatorNum();
        return 7;
    }

    @Override
    public int getElevatorPosition(int elevatorNumber) throws RemoteException {
        //return controller.getElevatorPosition(elevatorNumber);
        return 3;
    }

    @Override
    public int getElevatorSpeed(int elevatorNumber) throws RemoteException {
        //return controller.getElevatorSpeed(elevatorNumber);
        return 789 + elevatorNumber;
    }

    @Override
    public int getElevatorWeight(int elevatorNumber) throws RemoteException {
        //return controller.getElevatorWeight(elevatorNumber);
        return 444 + elevatorNumber;
    }

    @Override
    public int getElevatorCapacity(int elevatorNumber) throws RemoteException {
        //return controller.getElevatorCapacity(elevatorNumber);
        return 1234 + elevatorNumber;
    }

    @Override
    public boolean getFloorButtonDown(int floor) throws RemoteException {
        //return controller.getFloorButtonDown(floor);
        return false;
    }

    @Override
    public boolean getFloorButtonUp(int floor) throws RemoteException {
        //return controller.getFloorButtonUp(floor);
        return false;
    }

    @Override
    public int getFloorHeight() throws RemoteException {
        //return controller.getFloorHeight();
        return 4711;
    }

    @Override
    public int getFloorNum() throws RemoteException {
        //return controller.getFloorNum();
        return 10;
    }

    @Override
    public boolean getServicesFloors(int elevatorNumber, int floor) throws RemoteException {
        //return controller.getServicesFloors(elevatorNumber, floor);
        return true;
    }

    @Override
    public int getTarget(int elevatorNumber) throws RemoteException {
        //return controller.getTarget(elevatorNumber);
        return 5;
    }

    @Override
    public void setCommittedDirection(int elevatorNumber, int direction) throws RemoteException {
        //controller.setCommittedDirection(elevatorNumber, direction);
    }

    @Override
    public void setServicesFloors(int elevatorNumber, int floor, boolean service) throws RemoteException {
        //controller.setServicesFloors(elevatorNumber, floor, service);
    }

    @Override
    public void setTarget(int elevatorNumber, int target) throws RemoteException {
        //controller.setTarget(elevatorNumber, target);
    }

    @Override
    public long getClockTick() throws RemoteException {
        //return controller.getClockTick();
        return 123456789;
    }

    public void wrappedSetServicesFloors(int elevatorNumber, int floor, boolean service) {
        try {
            setServicesFloors(elevatorNumber, floor, service);
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void wrappedSetTarget(int elevatorNumber, int target) {
        try {
            setTarget(elevatorNumber, target);
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void wrappedSetCommittedDirection(int elevatorNumber, int direction) {
        try {
            setCommittedDirection(elevatorNumber, direction);
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
    }
}
