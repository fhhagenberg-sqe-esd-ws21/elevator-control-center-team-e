package at.fhhagenberg.sqe.ecc;

import sqelevator.IElevator;

/**
 * Extending given Elevator Interface to wrap exception throwing setters.
 * @author Markus Lindner - s2010567018
 */
public interface IElevatorHardwareManager extends IElevator {

    /**
     * Handles throwing exception from IElevator interface method setServicesFloors().
     * @param elevatorNumber The number of the elevator for which the serviced floors should be changed.
     * @param floor The actual floor for which the serviced status should be changed.
     * @param service Specifies if floor should be serviced or not.
     */
    public void wrappedSetServicesFloors(int elevatorNumber, int floor, boolean service);

    /**
     * Handles throwing exception from IElevator interface setTarget().
     * @param elevatorNumber The number of the elevator for which the target floor should be set.
     * @param target The actual target floor the elevator should aim for.
     */
    public void wrappedSetTarget(int elevatorNumber, int target);

    /**
     * Handles throwing exception from IElevator interface setCommittedDirection().
     * @param elevatorNumber The number of the elevator for which the commited direction should be set.
     * @param direction The actual direction the elevator should take (UP, DOWN, UNCOMMITED).
     */
    public void wrappedSetCommittedDirection(int elevatorNumber, int direction);
}
