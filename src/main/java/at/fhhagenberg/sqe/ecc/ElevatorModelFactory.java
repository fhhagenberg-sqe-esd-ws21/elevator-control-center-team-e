package at.fhhagenberg.sqe.ecc;

import java.rmi.RemoteException;
import java.util.EventListener;

/**
 *
 * @author
 */
public class ElevatorModelFactory {

    IElevatorHardwareManager hwManager;

    public ElevatorModelFactory(IElevatorHardwareManager hwManager) {
        this.hwManager = hwManager;
    }

    public ElevatorModel CreateElevatorControlCenter() throws RemoteException {
        return new ElevatorModel(hwManager.getElevatorNum(), hwManager.getFloorNum(), hwManager.getFloorHeight());
    }
}
