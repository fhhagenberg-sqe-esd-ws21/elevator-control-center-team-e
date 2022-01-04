package at.fhhagenberg.sqe.ecc;

import java.rmi.RemoteException;

/**
 * Class to handle cyclic updates of low level elevator system.
 * 
 * @author Markus Lindner - s2010567018
 */
public class ElevatorModelUpdater {

	IElevator elevatorInterface;
	ElevatorModel model;

	/**
	 * Constructor of the class.
	 * 
	 * @param elevatorInterface The interface which should be used to communicate
	 *                          with low level elevator system.
	 * @param model             The model which should be used to store the
	 *                          information at.
	 */
	public ElevatorModelUpdater(IElevator elevatorInterface, ElevatorModel model) {
		this.elevatorInterface = elevatorInterface;
		this.model = model;
	}

	/**
	 * Uses the specified interface to get all information from the low level
	 * elevator system and stores the information using the specified model. This
	 * method should be called every 100 milliseconds.
	 */
	public void UpdateModel() {
		try {
			for (int i = 0; i < model.getNumOfElevators(); ++i) {

				model.setCommittedDirection(i, elevatorInterface.getCommittedDirection(i));
				model.setElevatorAccel(i, elevatorInterface.getElevatorAccel(i));
				model.setElevatorDoorStatus(i, elevatorInterface.getElevatorDoorStatus(i));
				model.setElevatorFloor(i, elevatorInterface.getElevatorFloor(i));
				model.setElevatorPosition(i, elevatorInterface.getElevatorPosition(i));
				model.setElevatorSpeed(i, elevatorInterface.getElevatorSpeed(i));
				model.setElevatorWeight(i, elevatorInterface.getElevatorWeight(i));
				model.setElevatorCapacity(i, elevatorInterface.getElevatorCapacity(i));
				model.setTarget(i, elevatorInterface.getTarget(i));
				UpdateServicedFloors(i);
				UpdateElevatorButtons(i);

			}

			for (int i = 0; i < model.getNumOfFloors(); ++i) {
				model.setFloorButtonDown(i, elevatorInterface.getFloorButtonDown(i));
				model.setFloorButtonUp(i, elevatorInterface.getFloorButtonUp(i));
			}

			model.setClockTick(elevatorInterface.getClockTick());
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		

	}

	private void UpdateServicedFloors(int elevatorNumber) throws RemoteException {
		for (int i = 0; i < model.getNumOfFloors(); ++i) {
			model.setServicesFloors(elevatorNumber, i, elevatorInterface.getServicesFloors(elevatorNumber, i));
		}
	}

	private void UpdateElevatorButtons(int elevatorNumber) throws RemoteException {
		for (int i = 0; i < model.getNumOfFloors(); ++i) {
			model.setElevatorButton(elevatorNumber, i, elevatorInterface.getElevatorButton(elevatorNumber, i));
		}
	}
}
