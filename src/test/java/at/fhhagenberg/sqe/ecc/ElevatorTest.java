package at.fhhagenberg.sqe.ecc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sqelevator.IElevator;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Alexander Kemptner - S2010567016
 */
public class ElevatorTest {

    Elevator elevator;
    public static final int NUM_FLOORS = 5;

    @BeforeEach
    void setupTestElevator() {
        elevator = new Elevator(NUM_FLOORS);
    }

    @Test
    void testSetGetElevatorButton()
    {
        elevator.setElevatorButton(3, true);
        assertTrue(elevator.getElevatorButton(3));
    }

    @Test
    void testSetGetElevatorButtonTooHigh()
    {
        assertThrows(IllegalArgumentException.class, () -> elevator.setElevatorButton(NUM_FLOORS, true));
        assertThrows(IllegalArgumentException.class, () -> elevator.getElevatorButton(NUM_FLOORS));
    }

    @Test
    void testSetGetElevatorButtonTooLow()
    {
        assertThrows(IllegalArgumentException.class, () -> elevator.setElevatorButton(-1, true));
        assertThrows(IllegalArgumentException.class, () -> elevator.getElevatorButton(NUM_FLOORS));
    }

    @Test
    void testSetGetServicesFloors()
    {
        elevator.setServicesFloors(3, false);
        assertFalse(elevator.getServicesFloors(3));
    }

    @Test
    void testSetGetServicesFloorsTooLow()
    {
        assertThrows(IllegalArgumentException.class, () -> elevator.setServicesFloors(-1, false));
        assertThrows(IllegalArgumentException.class, () -> elevator.getServicesFloors(-1));
    }

    @Test
    void testSetGetServicesFloorsTooHigh()
    {
        assertThrows(IllegalArgumentException.class, () -> elevator.setServicesFloors(NUM_FLOORS, false));
        assertThrows(IllegalArgumentException.class, () -> elevator.getServicesFloors(NUM_FLOORS));
    }

    @Test
    void testSetGetCommittedDirection()
    {
        elevator.setCommittedDirection(1);
        assertEquals(1, elevator.getCommittedDirection());
    }

    @Test
    void testSetGetCommittedDirectionTooLow()
    {
        int prev = elevator.getCommittedDirection();
        assertThrows(IllegalArgumentException.class, () -> elevator.setCommittedDirection(-1));
        // assert still identical
        assertEquals(prev, elevator.getCommittedDirection());
    }

    @Test
    void testSetGetCommittedDirectionTooHigh()
    {
        int prev = elevator.getCommittedDirection();
        assertThrows(IllegalArgumentException.class, () -> elevator.setCommittedDirection(3));
        // assert still identical
        assertEquals(prev, elevator.getCommittedDirection());
    }

    @Test
    void testSetGetElevatorAccel()
    {
        elevator.setElevatorAccel(42);
        assertEquals(42, elevator.getElevatorAccel());
    }

    @Test
    void testSetGetElevatorDoorStatus()
    {
        elevator.setElevatorDoorStatus(0);
        assertEquals(0, elevator.getElevatorDoorStatus());
    }

    @Test
    void testSetGetElevatorDoorStatusTooLow()
    {
        int prevVal = elevator.getElevatorDoorStatus();
        assertThrows(IllegalArgumentException.class, () -> elevator.setElevatorDoorStatus(IElevator.ELEVATOR_DIRECTION_UP-1));
        assertEquals(prevVal, elevator.getElevatorDoorStatus());
    }

    @Test
    void testSetGetElevatorDoorStatusTooHigh()
    {
        int prevVal = elevator.getElevatorDoorStatus();
        assertThrows(IllegalArgumentException.class, () -> elevator.setElevatorDoorStatus(IElevator.ELEVATOR_DOORS_CLOSING+1));
        assertEquals(prevVal, elevator.getElevatorDoorStatus());
    }

    @Test
    void testSetGetElevatorFloor()
    {
        elevator.setElevatorFloor(NUM_FLOORS-1);
        assertEquals(NUM_FLOORS-1, elevator.getElevatorFloor());
    }

    @Test
    void testSetGetElevatorFloorTooLow()
    {
        int prevVal = elevator.getElevatorFloor();
        assertThrows(IllegalArgumentException.class, () -> elevator.setElevatorFloor(-1));
        assertEquals(prevVal, elevator.getElevatorFloor());
    }

    @Test
    void testSetGetElevatorFloorTooHigh()
    {
        int prevVal = elevator.getElevatorFloor();
        assertThrows(IllegalArgumentException.class, () -> elevator.setElevatorFloor(NUM_FLOORS));
        assertEquals(prevVal, elevator.getElevatorFloor());
    }

    @Test
    void testSetGetElevatorPosition()
    {
        elevator.setElevatorPosition(100);
        assertEquals(100, elevator.getElevatorPosition());
    }

    @Test
    void testSetGetElevatorSpeed()
    {
        elevator.setElevatorSpeed(5);
        assertEquals(5, elevator.getElevatorSpeed());
    }

    @Test
    void testSetGetElevatorWeight()
    {
        elevator.setElevatorWeight(750);
        assertEquals(750, elevator.getElevatorWeight());
    }

    @Test
    void testSetGetElevatorCapacity()
    {
        elevator.setElevatorCapacity(7);
        assertEquals(7, elevator.getElevatorCapacity());
    }

    @Test
    void testSetGetElevatorTarget()
    {
        elevator.setTarget(NUM_FLOORS-1);
        assertEquals(NUM_FLOORS-1, elevator.getTarget());
    }

    @Test
    void testSetGetElevatorTargetTooHigh()
    {
        int prevVal = elevator.getTarget();
        assertThrows(IllegalArgumentException.class, () -> elevator.setTarget(NUM_FLOORS));
        assertEquals(prevVal, elevator.getTarget());
    }

    @Test
    void testSetGetElevatorTargetTooLow()
    {
        int prevVal = elevator.getTarget();
        assertThrows(IllegalArgumentException.class, () -> elevator.setTarget(-1));
        assertEquals(prevVal, elevator.getTarget());
    }
}