package at.fhhagenberg.sqe.ecc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sqelevator.IElevator;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ElevatorHardwareManagerTest {

    @Mock
    IElevator controller = mock(IElevator.class);

    @Test
    public void testElevatorHardwareManager_faultyParameter() {
        assertThrows(IllegalArgumentException.class, () -> new ElevatorHardwareManager(null));
    }

    @Test
    public void testElevatorHardwareManager_correctParameter() {
        assertDoesNotThrow(() -> new ElevatorHardwareManager(controller));
    }

    @Test
    public void testGetCommitedDirection() throws Exception {
        ElevatorHardwareManager hwManager = new ElevatorHardwareManager(controller);

        when(controller.getCommittedDirection(4711)).thenReturn(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED);
        int result = hwManager.getCommittedDirection(4711);

        verify(controller, times(1)).getCommittedDirection(4711);
        assertEquals(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED, result);
    }

    @Test
    public void testGetElevatorAccel() throws Exception {
        ElevatorHardwareManager hwManager = new ElevatorHardwareManager(controller);

        when(controller.getElevatorAccel(4712)).thenReturn(5555);
        int result = hwManager.getElevatorAccel(4712);

        verify(controller, times(1)).getElevatorAccel(4712);
        assertEquals(5555, result);
    }

    @Test
    public void testGetElevatorButton() throws Exception {
        ElevatorHardwareManager hwManager = new ElevatorHardwareManager(controller);

        when(controller.getElevatorButton(4713, 5711)).thenReturn(true);
        boolean result = hwManager.getElevatorButton(4713, 5711);

        verify(controller, times(1)).getElevatorButton(4713,5711);
        assertTrue(result);
    }

    @Test
    public void testGetElevatorDoorStatus() throws Exception {
        ElevatorHardwareManager hwManager = new ElevatorHardwareManager(controller);

        when(controller.getElevatorDoorStatus(4714)).thenReturn(IElevator.ELEVATOR_DOORS_OPENING);
        int result = hwManager.getElevatorDoorStatus(4714);

        verify(controller, times(1)).getElevatorDoorStatus(4714);
        assertEquals(IElevator.ELEVATOR_DOORS_OPENING, result);
    }

    @Test
    public void testGetElevatorFloor() throws Exception {
        ElevatorHardwareManager hwManager = new ElevatorHardwareManager(controller);

        when(controller.getElevatorFloor(4715)).thenReturn(1234);
        int result = hwManager.getElevatorFloor(4715);

        verify(controller, times(1)).getElevatorFloor(4715);
        assertEquals(1234, result);
    }

    @Test
    public void testGetElevatorNum() throws Exception {
        ElevatorHardwareManager hwManager = new ElevatorHardwareManager(controller);

        when(controller.getElevatorNum()).thenReturn(778899);
        int result = hwManager.getElevatorNum();

        verify(controller, times(1)).getElevatorNum();
        assertEquals(778899, result);
    }

    @Test
    public void testGetElevatorPosition() throws Exception {
        ElevatorHardwareManager hwManager = new ElevatorHardwareManager(controller);

        when(controller.getElevatorPosition(4717)).thenReturn(4455);
        int result = hwManager.getElevatorPosition(4717);

        verify(controller, times(1)).getElevatorPosition(4717);
        assertEquals(4455, result);
    }

    @Test
    public void testGetElevatorSpeed() throws Exception {
        ElevatorHardwareManager hwManager = new ElevatorHardwareManager(controller);

        when(controller.getElevatorSpeed(4718)).thenReturn(3);
        int result = hwManager.getElevatorSpeed(4718);

        verify(controller, times(1)).getElevatorSpeed(4718);
        assertEquals(3, result);
    }

    @Test
    public void testGetElevatorWeight() throws Exception {
        ElevatorHardwareManager hwManager = new ElevatorHardwareManager(controller);

        when(controller.getElevatorWeight(4719)).thenReturn(5);
        int result = hwManager.getElevatorWeight(4719);

        verify(controller, times(1)).getElevatorWeight(4719);
        assertEquals(5, result);
    }

    @Test
    public void testGetElevatorCapacity() throws Exception {
        ElevatorHardwareManager hwManager = new ElevatorHardwareManager(controller);

        when(controller.getElevatorCapacity(4720)).thenReturn(8);
        int result = hwManager.getElevatorCapacity(4720);

        verify(controller, times(1)).getElevatorCapacity(4720);
        assertEquals(8, result);
    }

    @Test
    public void testGetFloorButtonDown() throws Exception {
        ElevatorHardwareManager hwManager = new ElevatorHardwareManager(controller);

        when(controller.getFloorButtonDown(4721)).thenReturn(false);
        boolean result = hwManager.getFloorButtonDown(4721);

        verify(controller, times(1)).getFloorButtonDown(4721);
        assertFalse(result);
    }

    @Test
    public void testGetFloorButtonUp() throws Exception {
        ElevatorHardwareManager hwManager = new ElevatorHardwareManager(controller);

        when(controller.getFloorButtonUp(4722)).thenReturn(true);
        boolean result = hwManager.getFloorButtonUp(4722);

        verify(controller, times(1)).getFloorButtonUp(4722);
        assertTrue(result);
    }

    @Test
    public void testGetFloorHeight() throws Exception {
        ElevatorHardwareManager hwManager = new ElevatorHardwareManager(controller);

        when(controller.getFloorHeight()).thenReturn(252);
        int result = hwManager.getFloorHeight();

        verify(controller, times(1)).getFloorHeight();
        assertEquals(252, result);
    }

    @Test
    public void testGetFloorNum() throws Exception {
        ElevatorHardwareManager hwManager = new ElevatorHardwareManager(controller);

        when(controller.getFloorNum()).thenReturn(27);
        int result = hwManager.getFloorNum();

        verify(controller, times(1)).getFloorNum();
        assertEquals(27, result);
    }

    @Test
    public void testGetServicesFloors() throws Exception {
        ElevatorHardwareManager hwManager = new ElevatorHardwareManager(controller);

        when(controller.getServicesFloors(4723, 5725)).thenReturn(true);
        boolean result = hwManager.getServicesFloors(4723, 5725);

        verify(controller, times(1)).getServicesFloors(4723, 5725);
        assertTrue(result);
    }

    @Test
    public void testGetTarget() throws Exception {
        ElevatorHardwareManager hwManager = new ElevatorHardwareManager(controller);

        when(controller.getTarget(4724)).thenReturn(66);
        int result = hwManager.getTarget(4724);

        verify(controller, times(1)).getTarget(4724);
        assertEquals(66, result);
    }

    @Test
    public void testSetCommittedDirection() throws Exception {
        ElevatorHardwareManager hwManager = new ElevatorHardwareManager(controller);
        assertDoesNotThrow(() -> hwManager.setCommittedDirection(4725, IElevator.ELEVATOR_DIRECTION_UNCOMMITTED));
    }

    @Test
    public void testSetServicesFloors() throws Exception {
        ElevatorHardwareManager hwManager = new ElevatorHardwareManager(controller);
        assertDoesNotThrow(() -> hwManager.setServicesFloors(4726, 4444, true));
    }

    @Test
    public void testSetTarget() throws Exception {
        ElevatorHardwareManager hwManager = new ElevatorHardwareManager(controller);
        assertDoesNotThrow(() -> hwManager.setTarget(4727, 9));
    }

    @Test
    public void testGetClockTick() throws Exception {
        ElevatorHardwareManager hwManager = new ElevatorHardwareManager(controller);

        when(controller.getClockTick()).thenReturn(Long.valueOf(1122334455));
        long result = hwManager.getClockTick();

        verify(controller, times(1)).getClockTick();
        assertEquals(Long.valueOf(1122334455), result);
    }

    @Test
    void testGetIsConnected() {
        ElevatorHardwareManager hwManager = new ElevatorHardwareManager(controller);
        assertTrue(hwManager.getIsConnected());
    }

    @Test
    void testSetIsConnected() {
        ElevatorHardwareManager hwManager = new ElevatorHardwareManager(controller);

        hwManager.setIsConnected(false);
        assertFalse(hwManager.getIsConnected());
    }

    @Test
    void testReconnect() {
        ElevatorHardwareManager hwManager = new ElevatorHardwareManager(controller);

        hwManager.setIsConnected(false);

        assertThrows(RemoteException.class, () -> hwManager.reconnect());
        assertFalse(hwManager.getIsConnected());
    }
}
