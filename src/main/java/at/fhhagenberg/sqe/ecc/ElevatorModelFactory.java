package at.fhhagenberg.sqe.ecc;

import sqelevator.IElevator;
import java.rmi.RemoteException;

/**
 * Provides methods to create a new elevator model.
 * @author Alexander Kemptner - s2010567016
 */
public class ElevatorModelFactory {

    /**
     * The hardware manager used by the model.
     */
    IElevator hwManager;

    /**
     * Creates a new factory for elevator models with the given hardware manager.
     * @param hwManager The hardware manager to use.
     */
    public ElevatorModelFactory(IElevator hwManager) {
        this.hwManager = hwManager;
    }

    /**
     * Creates a new ECC model.
     * @return The new elevator model.
     * @throws RemoteException If query of system variables in the hardware manager was not successful.
     */
    public ElevatorModel CreateElevatorControlCenter() throws RemoteException {
        return new ElevatorModel(hwManager.getElevatorNum(), hwManager.getFloorNum(), hwManager.getFloorHeight());
    }
}
