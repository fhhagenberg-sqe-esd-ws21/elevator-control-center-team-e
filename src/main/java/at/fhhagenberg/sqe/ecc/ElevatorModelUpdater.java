package at.fhhagenberg.sqe.ecc;

import java.rmi.RemoteException;

/**
 * Class to handle cyclic updates of low level elevator system.
 * @author Markus Lindner - s2010567018
 */
public class ElevatorModelUpdater {

    IElevator elevatorInterface;
    ElevatorModel model;

    /**
     * Constructor of the class.
     * @param elevatorInterface The interface which should be used to communicate with low level elevator system.
     * @param model The model which should be used to store the information at.
     */
    public ElevatorModelUpdater(IElevator elevatorInterface, ElevatorModel model) {
        this.elevatorInterface = elevatorInterface;
        this.model = model;
    }

    /**
     * Uses the specified interface to get all information from the low level elevator system and stores the information
     * using the specified model.
     * This method should be called every 100 milliseconds.
     */
    public void UpdateModel() {
        for(int i = 0; i < model.getNumOfElevators(); ++i) {
            try {
                model.setCommittedDirection(i, elevatorInterface.getCommittedDirection(i));
            } catch(RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }

            try {
                model.setElevatorAccel(i, elevatorInterface.getElevatorAccel(i));
            } catch(RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }

            try {
                model.setElevatorDoorStatus(i, elevatorInterface.getElevatorDoorStatus(i));
            } catch(RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }

            try {
                model.setElevatorFloor(i, elevatorInterface.getElevatorFloor(i));
            } catch(RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }

            try {
                model.setElevatorPosition(i, elevatorInterface.getElevatorPosition(i));
            } catch(RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }

            try {
                model.setElevatorSpeed(i, elevatorInterface.getElevatorSpeed(i));
            } catch(RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }

            try {
                model.setElevatorWeight(i, elevatorInterface.getElevatorWeight(i));
            } catch(RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }

            try {
                model.setElevatorCapacity(i, elevatorInterface.getElevatorCapacity(i));
            } catch(RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }

            try {
                model.setTarget(i, elevatorInterface.getTarget(i));
            } catch(RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }

            UpdateServicedFloors(i);
            UpdateElevatorButtons(i);
        }

        for(int i = 0; i < model.getNumOfFloors(); ++i) {
            try {
                model.setFloorButtonDown(i, elevatorInterface.getFloorButtonDown(i));
            } catch(RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }

            try {
                model.setFloorButtonUp(i, elevatorInterface.getFloorButtonUp(i));
            } catch(RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }

        try {
            model.setClockTick(elevatorInterface.getClockTick());
        } catch(RemoteException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

    }

    private void UpdateServicedFloors(int elevatorNumber) {
        for(int i = 0; i < model.getNumOfFloors(); ++i) {
            try {
                model.setServicesFloors(elevatorNumber, i,
                        elevatorInterface.getServicesFloors(elevatorNumber, i));
            } catch(RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void UpdateElevatorButtons(int elevatorNumber) {
        for(int i = 0; i < model.getNumOfFloors(); ++i) {
            try {
                model.setElevatorButton(elevatorNumber, i,
                        elevatorInterface.getElevatorButton(elevatorNumber, i));
            } catch (RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
