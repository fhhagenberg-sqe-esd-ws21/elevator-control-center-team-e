package at.fhhagenberg.sqe.ecc;

import java.rmi.RemoteException;
import java.util.EventListener;

public class ElevatorModelFactory {

    IElevator elevatorInterface;

    public ElevatorModelFactory(IElevator elevatorInterface) {
        this.elevatorInterface = elevatorInterface;
    }

    public ElevatorModel CreateElevatorControlCenter() throws RemoteException {
        return new ElevatorModel(elevatorInterface.getElevatorNum(), elevatorInterface.getFloorNum(), elevatorInterface.getFloorHeight());
    }
}
