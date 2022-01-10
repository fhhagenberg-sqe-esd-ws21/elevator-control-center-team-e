package at.fhhagenberg.sqe.ecc;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ElevatorConnectionSetup {

    public static ElevatorHardwareManager ElevatorConnectionSetup() throws Exception {
            IElevator controller = (IElevator) Naming.lookup("rmi://host.docker.internal/ElevatorSim");
            return new ElevatorHardwareManager(controller);
    }
}
