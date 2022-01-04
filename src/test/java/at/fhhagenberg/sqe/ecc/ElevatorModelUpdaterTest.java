package at.fhhagenberg.sqe.ecc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests class of ElevatorModelUpdater.
 * @author Markus Lindner - s2010567018
 */
@ExtendWith(MockitoExtension.class)
public class ElevatorModelUpdaterTest {

    @Mock
    private IElevator mockedInterface = mock(IElevator.class);

    @Test
    public void testUpdateModel_committedDirection_pass() throws RemoteException {
        int elevatorNumber = 0;
        ElevatorModel model = new ElevatorModel(1, 10, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);

        when(mockedInterface.getCommittedDirection(elevatorNumber)).thenReturn(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED);
        updater.UpdateModel();

        verify(mockedInterface, times(1)).getCommittedDirection(elevatorNumber);
        assertEquals(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED, model.getCommittedDirection(elevatorNumber));
    }

    @Test
    public void testUpdateModel_committedDirection_throwsRemoteException() throws RemoteException {
        int elevatorNumber = 0;
        ElevatorModel model = new ElevatorModel(1, 10, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);

        when(mockedInterface.getCommittedDirection(elevatorNumber)).thenThrow(RemoteException.class);
        updater.UpdateModel();

        verify(mockedInterface, times(1)).getCommittedDirection(elevatorNumber);
        assertEquals(IElevator.ELEVATOR_DIRECTION_UP, model.getCommittedDirection(elevatorNumber));
    }

    @Test
    public void testUpdateModel_getElevatorAccel_pass() throws RemoteException {
        int elevatorNumber = 0;
        ElevatorModel model = new ElevatorModel(1, 10, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);

        when(mockedInterface.getElevatorAccel(elevatorNumber)).thenReturn(4711);
        updater.UpdateModel();

        verify(mockedInterface, times(1)).getElevatorAccel(elevatorNumber);
        assertEquals(4711, model.getElevatorAccel(elevatorNumber));
    }

    @Test
    public void testUpdateModel_getElevatorAccel_throwsRemoteException() throws RemoteException {
        int elevatorNumber = 0;
        ElevatorModel model = new ElevatorModel(1, 10, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);
        model.setElevatorAccel(elevatorNumber, 12345);

        when(mockedInterface.getElevatorAccel(elevatorNumber)).thenThrow(RemoteException.class);
        updater.UpdateModel();

        verify(mockedInterface, times(1)).getElevatorAccel(elevatorNumber);
        assertEquals(12345, model.getElevatorAccel(elevatorNumber));
    }

    @Test
    public void testUpdateModel_getElevatorDoorStatus_pass() throws RemoteException {
        int elevatorNumber = 0;
        ElevatorModel model = new ElevatorModel(1, 10, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);

        when(mockedInterface.getElevatorDoorStatus(elevatorNumber)).thenReturn(IElevator.ELEVATOR_DOORS_OPENING);
        updater.UpdateModel();

        verify(mockedInterface, times(1)).getElevatorDoorStatus(elevatorNumber);
        assertEquals(IElevator.ELEVATOR_DOORS_OPENING, model.getElevatorDoorStatus(elevatorNumber));
    }

    @Test
    public void testUpdateModel_getElevatorDoorStatus_throwsRemoteException() throws RemoteException {
        int elevatorNumber = 0;
        ElevatorModel model = new ElevatorModel(1, 10, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);
        model.setElevatorDoorStatus(elevatorNumber, IElevator.ELEVATOR_DOORS_CLOSED);

        when(mockedInterface.getElevatorDoorStatus(elevatorNumber)).thenThrow(RemoteException.class);
        updater.UpdateModel();

        verify(mockedInterface, times(1)).getElevatorDoorStatus(elevatorNumber);
        assertEquals(IElevator.ELEVATOR_DOORS_CLOSED, model.getElevatorDoorStatus(elevatorNumber));
    }

    @Test
    public void testUpdateModel_getElevatorFloor_pass() throws RemoteException {
        int elevatorNumber = 0;
        ElevatorModel model = new ElevatorModel(1, 10, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);

        when(mockedInterface.getElevatorFloor(elevatorNumber)).thenReturn(9);
        updater.UpdateModel();

        verify(mockedInterface, times(1)).getElevatorFloor(elevatorNumber);
        assertEquals(9, model.getElevatorFloor(elevatorNumber));
    }

    @Test
    public void testUpdateModel_getElevatorFloor_throwsRemoteException() throws RemoteException {
        int elevatorNumber = 0;
        ElevatorModel model = new ElevatorModel(1, 10, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);
        model.setElevatorFloor(elevatorNumber, 9);

        when(mockedInterface.getElevatorFloor(elevatorNumber)).thenThrow(RemoteException.class);
        updater.UpdateModel();

        verify(mockedInterface, times(1)).getElevatorFloor(elevatorNumber);
        assertEquals(9, model.getElevatorFloor(elevatorNumber));
    }

    @Test
    public void testUpdateModel_getElevatorPosition_pass() throws RemoteException {
        int elevatorNumber = 0;
        ElevatorModel model = new ElevatorModel(1, 10, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);

        when(mockedInterface.getElevatorPosition(elevatorNumber)).thenReturn(4712);
        updater.UpdateModel();

        verify(mockedInterface, times(1)).getElevatorPosition(elevatorNumber);
        assertEquals(4712, model.getElevatorPosition(elevatorNumber));
    }

    @Test
    public void testUpdateModel_getElevatorPosition_throwsRemoteException() throws RemoteException {
        int elevatorNumber = 0;
        ElevatorModel model = new ElevatorModel(1, 10, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);
        model.setElevatorPosition(elevatorNumber, 4712);

        when(mockedInterface.getElevatorPosition(elevatorNumber)).thenThrow(RemoteException.class);
        updater.UpdateModel();

        verify(mockedInterface, times(1)).getElevatorPosition(elevatorNumber);
        assertEquals(4712, model.getElevatorPosition(elevatorNumber));
    }

    @Test
    public void testUpdateModel_getElevatorSpeed_pass() throws RemoteException {
        int elevatorNumber = 0;
        ElevatorModel model = new ElevatorModel(1, 10, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);

        when(mockedInterface.getElevatorSpeed(elevatorNumber)).thenReturn(987);
        updater.UpdateModel();

        verify(mockedInterface, times(1)).getElevatorSpeed(elevatorNumber);
        assertEquals(987, model.getElevatorSpeed(elevatorNumber));
    }

    @Test
    public void testUpdateModel_getElevatorSpeed_throwsRemoteException() throws RemoteException {
        int elevatorNumber = 0;
        ElevatorModel model = new ElevatorModel(1, 10, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);
        model.setElevatorSpeed(elevatorNumber, 987);

        when(mockedInterface.getElevatorSpeed(elevatorNumber)).thenThrow(RemoteException.class);
        updater.UpdateModel();

        verify(mockedInterface, times(1)).getElevatorSpeed(elevatorNumber);
        assertEquals(987, model.getElevatorSpeed(elevatorNumber));
    }

    @Test
    public void testUpdateModel_getElevatorWeight_pass() throws RemoteException {
        int elevatorNumber = 0;
        ElevatorModel model = new ElevatorModel(1, 10, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);

        when(mockedInterface.getElevatorWeight(elevatorNumber)).thenReturn(1234);
        updater.UpdateModel();

        verify(mockedInterface, times(1)).getElevatorWeight(elevatorNumber);
        assertEquals(1234, model.getElevatorWeight(elevatorNumber));
    }

    @Test
    public void testUpdateModel_getElevatorWeight_throwsRemoteException() throws RemoteException {
        int elevatorNumber = 0;
        ElevatorModel model = new ElevatorModel(1, 10, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);
        model.setElevatorWeight(elevatorNumber, 1234);

        when(mockedInterface.getElevatorWeight(elevatorNumber)).thenThrow(RemoteException.class);
        updater.UpdateModel();

        verify(mockedInterface, times(1)).getElevatorWeight(elevatorNumber);
        assertEquals(1234, model.getElevatorWeight(elevatorNumber));
    }

    @Test
    public void testUpdateModel_getElevatorCapacity_pass() throws RemoteException {
        int elevatorNumber = 0;
        ElevatorModel model = new ElevatorModel(1, 10, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);

        when(mockedInterface.getElevatorCapacity(elevatorNumber)).thenReturn(1024);
        updater.UpdateModel();

        verify(mockedInterface, times(1)).getElevatorCapacity(elevatorNumber);
        assertEquals(1024, model.getElevatorCapacity(elevatorNumber));
    }

    @Test
    public void testUpdateModel_getElevatorCapacity_throwsRemoteException() throws RemoteException {
        int elevatorNumber = 0;
        ElevatorModel model = new ElevatorModel(1, 10, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);
        model.setElevatorCapacity(elevatorNumber, 1025);

        when(mockedInterface.getElevatorCapacity(elevatorNumber)).thenThrow(RemoteException.class);
        updater.UpdateModel();

        verify(mockedInterface, times(1)).getElevatorCapacity(elevatorNumber);
        assertEquals(1025, model.getElevatorCapacity(elevatorNumber));
    }

    @Test
    public void testUpdateModel_getTarget_pass() throws RemoteException {
        int elevatorNumber = 0;
        ElevatorModel model = new ElevatorModel(1, 10, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);

        when(mockedInterface.getTarget(elevatorNumber)).thenReturn(3);
        updater.UpdateModel();

        verify(mockedInterface, times(1)).getTarget(elevatorNumber);
        assertEquals(3, model.getTarget(elevatorNumber));
    }

    @Test
    public void testUpdateModel_getTarget_throwsRemoteException() throws RemoteException {
        int elevatorNumber = 0;
        ElevatorModel model = new ElevatorModel(1, 10, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);
        model.setTarget(elevatorNumber, 4);

        when(mockedInterface.getTarget(elevatorNumber)).thenThrow(RemoteException.class);
        updater.UpdateModel();

        verify(mockedInterface, times(1)).getTarget(elevatorNumber);
        assertEquals(4, model.getTarget(elevatorNumber));
    }

    @Test
    public void testUpdateModel_getFloorButtonDown_pass() throws RemoteException {
        ElevatorModel model = new ElevatorModel(1, 2, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);

        when(mockedInterface.getFloorButtonDown(0)).thenReturn(true);
        when(mockedInterface.getFloorButtonDown(1)).thenReturn(false);

        updater.UpdateModel();

        verify(mockedInterface, times(1)).getFloorButtonDown(0);
        verify(mockedInterface, times(1)).getFloorButtonDown(1);
        assertTrue(model.getFloorButtonDown(0));
        assertFalse(model.getFloorButtonDown(1));
    }

    @Test
    public void testUpdateModel_getFloorButtonDown_throwsRemoteException() throws RemoteException {
        ElevatorModel model = new ElevatorModel(1, 2, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);
        model.setFloorButtonDown(0, true);

        when(mockedInterface.getFloorButtonDown(0)).thenThrow(RemoteException.class);

        updater.UpdateModel();

        verify(mockedInterface, times(1)).getFloorButtonDown(0);
        assertTrue(model.getFloorButtonDown(0));
    }

    @Test
    public void testUpdateModel_getFloorButtonUp_pass() throws RemoteException {
        ElevatorModel model = new ElevatorModel(1, 2, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);

        when(mockedInterface.getFloorButtonUp(0)).thenReturn(true);
        when(mockedInterface.getFloorButtonUp(1)).thenReturn(false);

        updater.UpdateModel();

        verify(mockedInterface, times(1)).getFloorButtonUp(0);
        verify(mockedInterface, times(1)).getFloorButtonUp(1);
        assertTrue(model.getFloorButtonUp(0));
        assertFalse(model.getFloorButtonUp(1));
    }

    @Test
    public void testUpdateModel_getFloorButtonUp_throwsRemoteException() throws RemoteException {
        ElevatorModel model = new ElevatorModel(1, 2, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);
        model.setFloorButtonUp(0, true);

        when(mockedInterface.getFloorButtonUp(0)).thenThrow(RemoteException.class);

        updater.UpdateModel();

        verify(mockedInterface, times(1)).getFloorButtonUp(0);
        assertTrue(model.getFloorButtonUp(0));
    }

    @Test
    public void testUpdateModel_getClockTick_pass() throws RemoteException {
        ElevatorModel model = new ElevatorModel(1, 2, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);

        when(mockedInterface.getClockTick()).thenReturn(Integer.toUnsignedLong(47123));
        updater.UpdateModel();

        verify(mockedInterface, times(1)).getClockTick();
        assertEquals(47123, model.getClockTick());
    }

    @Test
    public void testUpdateModel_getClockTick_throwsRemoteException() throws RemoteException {
        ElevatorModel model = new ElevatorModel(1, 2, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);
        model.setClockTick(47132);

        when(mockedInterface.getClockTick()).thenThrow(RemoteException.class);
        updater.UpdateModel();

        verify(mockedInterface, times(1)).getClockTick();
        assertEquals(47132, model.getClockTick());
    }

    @Test
    public void testUpdateModel_getServicesFloors_pass() throws RemoteException {
        ElevatorModel model = new ElevatorModel(1, 2, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);

        when(mockedInterface.getServicesFloors(0,0)).thenReturn(true);
        when(mockedInterface.getServicesFloors(0,1)).thenReturn(false);

        updater.UpdateModel();

        verify(mockedInterface, times(1)).getServicesFloors(0,0);
        verify(mockedInterface, times(1)).getServicesFloors(0,1);
        assertTrue(model.getServicesFloors(0,0));
        assertFalse(model.getServicesFloors(0,1));
    }

    @Test
    public void testUpdateModel_getServicesFloors_throwsRemoteException() throws RemoteException {
        ElevatorModel model = new ElevatorModel(1, 2, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);
        model.setServicesFloors(0,0, false);
        model.setServicesFloors(0,1, true);

        when(mockedInterface.getServicesFloors(0,0)).thenThrow(RemoteException.class);

        updater.UpdateModel();

        verify(mockedInterface, times(1)).getServicesFloors(0,0);
        assertFalse(model.getServicesFloors(0,0));
    }

    @Test
    public void testUpdateModel_getElevatorButton_pass() throws RemoteException {
        ElevatorModel model = new ElevatorModel(1, 2, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);

        when(mockedInterface.getElevatorButton(0,0)).thenReturn(true);
        when(mockedInterface.getElevatorButton(0,1)).thenReturn(false);

        updater.UpdateModel();

        verify(mockedInterface, times(1)).getElevatorButton(0,0);
        verify(mockedInterface, times(1)).getElevatorButton(0,1);
        assertTrue(model.getElevatorButton(0,0));
        assertFalse(model.getElevatorButton(0,1));
    }

    @Test
    public void testUpdateModel_getElevatorButton_throwsRemoteException() throws RemoteException {
        ElevatorModel model = new ElevatorModel(1, 2, 2500);
        ElevatorModelUpdater updater = new ElevatorModelUpdater(mockedInterface, model);
        model.setElevatorButton(0,0, false);

        when(mockedInterface.getElevatorButton(0,0)).thenThrow(RemoteException.class);

        updater.UpdateModel();

        verify(mockedInterface, times(1)).getElevatorButton(0,0);
        assertFalse(model.getElevatorButton(0,0));
    }
}
