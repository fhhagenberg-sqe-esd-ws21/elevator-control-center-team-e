package at.fhhagenberg.sqe.ecc;

import sqelevator.IElevator;

import java.rmi.Naming;

public class ElevatorConnectionManager {

    public static IElevator ElevatorConnectionSetup() throws Exception {
        return (IElevator) Naming.lookup("rmi://localhost/ElevatorSim");
    }
}
