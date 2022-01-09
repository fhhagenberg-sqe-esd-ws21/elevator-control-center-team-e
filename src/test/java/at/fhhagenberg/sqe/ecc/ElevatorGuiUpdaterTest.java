package at.fhhagenberg.sqe.ecc;

import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.mockito.Mockito.*;

/**
 *
 * @author
 */
@ExtendWith(MockitoExtension.class)
public class ElevatorGuiUpdaterTest extends ApplicationTest {

    @Mock
    private ElevatorGui guiMock = mock(ElevatorGui.class);

    @Test
    public void testUpdateCommittedDirection() {
        ElevatorGuiUpdater guiUpdater = new ElevatorGuiUpdater(guiMock);

        guiUpdater.updateCommittedDirection(123);
        WaitForAsyncUtils.waitForFxEvents();

        verify(guiMock,times(1)).setCommitedDirection(123);
    }

    @Test
    public void testUpdateTargetFloor() {
        ElevatorGuiUpdater guiUpdater = new ElevatorGuiUpdater(guiMock);

        guiUpdater.updateTargetFloor(4710);
        guiUpdater.updateTargetFloor(4711);
        WaitForAsyncUtils.waitForFxEvents();

        verify(guiMock,times(1)).setTargetFloor(4710);
        verify(guiMock,times(1)).setTargetFloor(4711);
    }

    @Test
    public void testUpdateElevatorAccel() {
        ElevatorGuiUpdater guiUpdater = new ElevatorGuiUpdater(guiMock);

        guiUpdater.updateElevatorAccel(4712);
        guiUpdater.updateElevatorAccel(4713);
        WaitForAsyncUtils.waitForFxEvents();

        verify(guiMock,times(1)).setElevatorAccel(4712);
        verify(guiMock,times(1)).setElevatorAccel(4713);
    }

    @Test
    public void testUpdateElevatorDoorStatus() {
        ElevatorGuiUpdater guiUpdater = new ElevatorGuiUpdater(guiMock);

        guiUpdater.updateElevatorDoorStatus(55);
        guiUpdater.updateElevatorDoorStatus(65);
        WaitForAsyncUtils.waitForFxEvents();

        verify(guiMock,times(1)).setElevatorDoorStatus(55);
        verify(guiMock,times(1)).setElevatorDoorStatus(65);
    }

    @Test
    public void testUpdateElevatorFloor() {
        ElevatorGuiUpdater guiUpdater = new ElevatorGuiUpdater(guiMock);

        guiUpdater.updateElevatorFloor(321);
        WaitForAsyncUtils.waitForFxEvents();

        verify(guiMock,times(1)).setElevatorFloor(321);
    }

    @Test
    public void testUpdateElevatorPosition() {
        ElevatorGuiUpdater guiUpdater = new ElevatorGuiUpdater(guiMock);

        guiUpdater.updateElevatorPosition(4);
        WaitForAsyncUtils.waitForFxEvents();

        verify(guiMock,times(1)).setElevatorPosition(4);
    }

    @Test
    public void testUpdateElevatorSpeed() {
        ElevatorGuiUpdater guiUpdater = new ElevatorGuiUpdater(guiMock);

        guiUpdater.updateElevatorSpeed(65536);
        WaitForAsyncUtils.waitForFxEvents();

        verify(guiMock,times(1)).setElevatorSpeed(65536);
    }

    @Test
    public void testUpdateElevatorWeight() {
        ElevatorGuiUpdater guiUpdater = new ElevatorGuiUpdater(guiMock);

        guiUpdater.updateElevatorWeight(22);
        WaitForAsyncUtils.waitForFxEvents();

        verify(guiMock,times(1)).setElevatorWeight(22);
    }

    @Test
    public void testUpdateElevatorCapacity() {
        ElevatorGuiUpdater guiUpdater = new ElevatorGuiUpdater(guiMock);

        guiUpdater.updateElevatorCapacity(789);
        WaitForAsyncUtils.waitForFxEvents();

        verify(guiMock,times(1)).setElevatorCapacity(789);
    }

    @Test
    public void testUpdateServicedFloors() {
        ElevatorGuiUpdater guiUpdater = new ElevatorGuiUpdater(guiMock);

        guiUpdater.updateServicedFloors(37, 99);
        WaitForAsyncUtils.waitForFxEvents();

        verify(guiMock,times(1)).setServicesFloors(37, 99);
    }

    @Test
    public void testUpdateFloorButtonUp() {
        ElevatorGuiUpdater guiUpdater = new ElevatorGuiUpdater(guiMock);

        guiUpdater.updateFloorButtonUp(32);
        WaitForAsyncUtils.waitForFxEvents();

        verify(guiMock,times(1)).setFloorButtonUp(32);
    }

    @Test
    public void testUpdateFloorButtonDown() {
        ElevatorGuiUpdater guiUpdater = new ElevatorGuiUpdater(guiMock);

        guiUpdater.updateFloorButtonDown(33);
        WaitForAsyncUtils.waitForFxEvents();

        verify(guiMock,times(1)).setFloorButtonDown(33);
    }

    @Test
    public void testUpdateElevatorButton() {
        ElevatorGuiUpdater guiUpdater = new ElevatorGuiUpdater(guiMock);

        guiUpdater.updateElevatorButton(77);
        WaitForAsyncUtils.waitForFxEvents();

        verify(guiMock,times(1)).setElevatorButton(77);
    }
}
