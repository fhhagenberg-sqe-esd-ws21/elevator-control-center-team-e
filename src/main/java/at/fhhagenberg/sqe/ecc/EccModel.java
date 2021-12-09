package at.fhhagenberg.sqe.ecc;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EccModel implements IElevator{

    private List<Elevator> elevators;
    private List<Floor> floors;

    private long clockTick;

    public EccModel() {
        elevators = new ArrayList<>();
        floors = new ArrayList<>();
        clockTick = 0;

        floors.add(new Floor(false, false, false, 2500));
        floors.add(new Floor(false, false, false, 2500));
        floors.add(new Floor(false, false, false, 2500));
        floors.add(new Floor(false, false, false, 2500));

        Map<Integer, Floor> servicedFloors = new HashMap<>();
        for(int i = 0; i < floors.size(); i++)
        {
            servicedFloors.put(i,floors.get(i));
        }

        elevators.add(new Elevator(ELEVATOR_DIRECTION_UNCOMMITTED, 0, ELEVATOR_DOORS_CLOSED, 0, 0, 0, 0, 8, servicedFloors, 0));
    }

    @Override
    public int getCommittedDirection(int elevatorNumber) throws RemoteException {
        if(elevatorNumber > elevators.size())
            return 0; //TODO: exception?

        return elevators.get(elevatorNumber-1).getCommittedDirection();
    }

    @Override
    public int getElevatorAccel(int elevatorNumber) throws RemoteException {
        if(elevatorNumber > elevators.size())
            return 0;

        return elevators.get(elevatorNumber-1).getElevatorAccel();
    }

    @Override
    public boolean getElevatorButton(int elevatorNumber, int floor) throws RemoteException {
        if(elevatorNumber > elevators.size())
            return false; //TODO: exception?

        return elevators.get(elevatorNumber-1).getElevatorButton(floor);
    }

    @Override
    public int getElevatorDoorStatus(int elevatorNumber) throws RemoteException {
        if(elevatorNumber > elevators.size())
            return 0; //TODO: exception?

        return elevators.get(elevatorNumber-1).getElevatorDoorStatus();
    }

    @Override
    public int getElevatorFloor(int elevatorNumber) throws RemoteException {
        if(elevatorNumber > elevators.size())
            return 0; //TODO: exception?

        return elevators.get(elevatorNumber-1).getElevatorFloor();
    }

    @Override
    public int getElevatorNum() throws RemoteException {
        return elevators.size();
    }

    @Override
    public int getElevatorPosition(int elevatorNumber) throws RemoteException {
        if(elevatorNumber > elevators.size())
            return 0; //TODO: exception?

        return elevators.get(elevatorNumber-1).getElevatorPosition();
    }

    @Override
    public int getElevatorSpeed(int elevatorNumber) throws RemoteException {
        if(elevatorNumber > elevators.size())
            return 0; //TODO: exception?

        return elevators.get(elevatorNumber-1).getElevatorSpeed();
    }

    @Override
    public int getElevatorWeight(int elevatorNumber) throws RemoteException {
        if(elevatorNumber > elevators.size())
            return 0; //TODO: exception?

        return elevators.get(elevatorNumber-1).getElevatorWeight();
    }

    @Override
    public int getElevatorCapacity(int elevatorNumber) throws RemoteException {
        if(elevatorNumber > elevators.size())
            return 0; //TODO: exception?

        return elevators.get(elevatorNumber-1).getElevatorCapacity();
    }

    @Override
    public boolean getFloorButtonDown(int floor) throws RemoteException {
        if(floor > floors.size()-1)
            return false; //TODO: exception?

        return floors.get(floor).isFloorButtonDown();
    }

    @Override
    public boolean getFloorButtonUp(int floor) throws RemoteException {
        if(floor > floors.size()-1)
            return false; //TODO: exception?

        return floors.get(floor).isFloorButtonUp();
    }

    @Override
    public int getFloorHeight() throws RemoteException {
        if(floors.size() == 0)
            return 0; //TODO: exception?

        return floors.get(0).getFloorHeight();
    }

    @Override
    public int getFloorNum() throws RemoteException {
        return floors.size();
    }

    @Override
    public boolean getServicesFloors(int elevatorNumber, int floor) throws RemoteException {
        if(elevatorNumber > elevators.size() && floor >= floors.size())
            return false; //TODO: exception?

        return elevators.get(elevatorNumber-1).getServicesFloors(floor);
    }

    @Override
    public int getTarget(int elevatorNumber) throws RemoteException {
        if(elevatorNumber > elevators.size())
            return 0; //TODO: exception?

        return elevators.get(elevatorNumber-1).getElevatorTargetFloor();
    }

    @Override
    public void setCommittedDirection(int elevatorNumber, int direction) throws RemoteException {
        //TODO: check direction?
        if(elevatorNumber <= elevators.size()) {
            elevators.get(elevatorNumber - 1).setCommittedDirection(direction);
        }
    }

    @Override
    public void setServicesFloors(int elevatorNumber, int floor, boolean service) throws RemoteException {
        if(elevatorNumber <= elevators.size() && floor < floors.size()) {
            elevators.get(elevatorNumber - 1).setServicesFloor(floor, service, floors.get(floor));
        }
    }

    @Override
    public void setTarget(int elevatorNumber, int target) throws RemoteException {
        if(elevatorNumber <= elevators.size() && target < floors.size()) {
            elevators.get(elevatorNumber - 1).setElevatorTargetFloor(target);
        }
    }

    @Override
    public long getClockTick() throws RemoteException {
        return 0;
    }
}
