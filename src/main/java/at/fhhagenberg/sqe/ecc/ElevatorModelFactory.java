package at.fhhagenberg.sqe.ecc;

import sqelevator.IElevator;
import java.rmi.RemoteException;

/**
 *
 * @author Alexander Kemptner - s2010567016
 */
public class ElevatorModelFactory {

    IElevator hwManager;

    public ElevatorModelFactory(IElevator hwManager) {
        this.hwManager = hwManager;
    }

    public ElevatorModel CreateElevatorControlCenter() throws RemoteException {
        return new ElevatorModel(hwManager.getElevatorNum(), hwManager.getFloorNum(), hwManager.getFloorHeight());
    }
}
