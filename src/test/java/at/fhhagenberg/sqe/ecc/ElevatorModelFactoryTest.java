package at.fhhagenberg.sqe.ecc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 *
 * @author Alexander Kemptner - S2010567016
 */
@ExtendWith(MockitoExtension.class)
public class ElevatorModelFactoryTest {

    @Mock IElevatorHardwareManager hwManager;

    @Test
    void testElevatorModelGeneration() throws RemoteException {
        ElevatorModelFactory factory = new ElevatorModelFactory(hwManager);
        ElevatorModel model = factory.CreateElevatorControlCenter();
        assertNotNull(model);
    }
}
