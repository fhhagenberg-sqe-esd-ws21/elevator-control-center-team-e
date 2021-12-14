package at.fhhagenberg.sqe.ecc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 * @author Alexander Kemptner - S2010567016
 */
public class ElevatorTest {

    Elevator elevator;

    @BeforeEach
    void setupTestElevator() {
        elevator = new Elevator(5);
    }
}
