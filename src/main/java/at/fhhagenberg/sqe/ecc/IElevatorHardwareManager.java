package at.fhhagenberg.sqe.ecc;

import sqelevator.IElevator;

/**
 * Extending given Elevator Interface to wrap exception throwing setters.
 * @author Markus Lindner - s2010567018
 */
public interface IElevatorHardwareManager extends IElevator {

    /**
     * Tries to reestablish a lost connection to the low-level elevator system.
     */
    public void reconnect() throws Exception;

    /**
     * Returns if there is a stable connection or not.
     * @return True if there is a stable connection to the low-level elevator system, false otherwise.
     */
    public boolean getIsConnected();

    /**
     * Setter to manipulate the state of the connection to the low-level elevator system.
     * @param isConnected If the connection is established or not.
     */
    public void setIsConnected(boolean isConnected);
}
