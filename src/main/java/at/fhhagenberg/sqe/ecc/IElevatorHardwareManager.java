package at.fhhagenberg.sqe.ecc;

public interface IElevatorHardwareManager extends IElevator {

    public void wrappedSetServicesFloors(int elevatorNumber, int floor, boolean service);

    public void wrappedSetTarget(int elevatorNumber, int target);

    public void wrappedSetCommittedDirection(int elevatorNumber, int direction);
}
