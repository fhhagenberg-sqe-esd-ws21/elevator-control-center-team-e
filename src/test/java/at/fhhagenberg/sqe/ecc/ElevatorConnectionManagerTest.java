package at.fhhagenberg.sqe.ecc;

import org.junit.jupiter.api.Test;
import sqelevator.IElevator;

import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

public class ElevatorConnectionManagerTest {
    @Test
    public void testElevatorConnectionUnreachable()
    {
        assertThrows(ConnectException.class, () -> ElevatorConnectionManager.ElevatorConnectionSetup());
    }

    @Test
    public void testGenerateElevatorConnectionManager()
    {
        ElevatorConnectionManager manager = new ElevatorConnectionManager();
        assertNotNull(manager);
    }
}
