package at.fhhagenberg.sqe.ecc;

import sqelevator.IElevator;

import java.rmi.Naming;

public class ElevatorConnectionSetup {

    public static ElevatorHardwareManager ElevatorConnectionSetup() throws Exception {
            IElevator controller = (IElevator) Naming.lookup("rmi://localhost/ElevatorSim");
            return new ElevatorHardwareManager(controller);
    }
}
