package at.fhhagenberg.sqe.ecc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author Alexander Kemptner - S2010567016
 */
public class FloorTest {

    Floor floor;

    @BeforeEach
    void initFloor() {
        floor = new Floor(false, false, false, false);
    }

    @Test
    void testFloorButtonFalseInitial() {
        assertFalse(floor.isFloorRequestButton());
    }

    @Test
    void testFloorButtonSetGetTrue() {
        floor.setFloorRequestButton(true);
        assertTrue(floor.isFloorRequestButton());
    }

    @Test
    void testFloorButtonSetGetFalse() {
        floor.setFloorRequestButton(false);
        assertFalse(floor.isFloorRequestButton());
    }

    @Test
    void testFloorButtonUpFalseInitial() {
        assertFalse(floor.isFloorButtonUp());
    }

    @Test
    void testFloorButtonUpSetGetTrue() {
        floor.setFloorButtonUp(true);
        assertTrue(floor.isFloorButtonUp());
    }

    @Test
    void testFloorButtonUpSetGetFalse() {
        floor.setFloorButtonUp(false);
        assertFalse(floor.isFloorButtonUp());
    }

    @Test
    void testFloorButtonDownFalseInitial() {
        assertFalse(floor.isFloorButtonDown());
    }

    @Test
    void testFloorButtonDownSetGetTrue() {
        floor.setFloorButtonDown(true);
        assertTrue(floor.isFloorButtonDown());
    }

    @Test
    void testFloorButtonDownSetGetFalse() {
        floor.setFloorButtonDown(false);
        assertFalse(floor.isFloorButtonDown());
    }

    @Test
    void testFloorServicedInitial() {
        assertFalse(floor.isServiced());
    }

    @Test
    void testFloorServicedSetGetTrue() {
        floor.setServiced(true);
        assertTrue(floor.isServiced());
    }

    @Test
    void testFloorServicedSetGetFalse() {
        floor.setServiced(false);
        assertFalse(floor.isServiced());
    }
}
