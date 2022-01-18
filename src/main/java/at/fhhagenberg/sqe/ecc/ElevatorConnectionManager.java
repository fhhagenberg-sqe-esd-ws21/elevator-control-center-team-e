package at.fhhagenberg.sqe.ecc;

import sqelevator.IElevator;
import java.rmi.Naming;

/**
 * Provides an RMI connection to the (simulation) elevator.
 */
public class ElevatorConnectionManager {

    /**
     * Sets up the connection to the elevator simulator.
     * @return The elevator interface connection to the RMI service.
     * @throws Exception If connection is not successful. ConnectException if simulator is not running.
     */
    public static IElevator ElevatorConnectionSetup() throws Exception {
        return (IElevator) Naming.lookup("rmi://localhost/ElevatorSim");
    }
}
