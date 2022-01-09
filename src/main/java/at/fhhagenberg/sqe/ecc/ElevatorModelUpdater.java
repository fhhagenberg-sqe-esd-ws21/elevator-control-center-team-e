package at.fhhagenberg.sqe.ecc;

import java.rmi.RemoteException;

/**
 * Class to handle cyclic updates of low level elevator system.
 * 
 * @author Markus Lindner - s2010567018
 */
public class ElevatorModelUpdater {

	ElevatorHardwareManager hwManager;
	ElevatorModel model;

	/**
	 * Constructor of the class.
	 * 
	 * @param hwManager 		Unit which should be used to communicate
	 *                          with low level elevator system.
	 * @param model             The model which should be used to store the
	 *                          information at.
	 */
	public ElevatorModelUpdater(ElevatorHardwareManager hwManager, ElevatorModel model) {
		this.hwManager = hwManager;
		this.model = model;
	}

	/**
	 * Uses the specified interface to get all information from the low level
	 * elevator system and stores the information using the specified model. This
	 * method should be called every 100 milliseconds.
	 */
	public void UpdateModel() {

		System.out.println("Running MODEL update!");

		try {
			for (int i = 0; i < model.getNumOfElevators(); ++i) {
				model.setCommittedDirection(i, hwManager.getCommittedDirection(i));
				model.setElevatorAccel(i, hwManager.getElevatorAccel(i));
				model.setElevatorDoorStatus(i, hwManager.getElevatorDoorStatus(i));
				model.setElevatorFloor(i, hwManager.getElevatorFloor(i));
				model.setElevatorPosition(i, hwManager.getElevatorPosition(i));
				model.setElevatorSpeed(i, hwManager.getElevatorSpeed(i));
				model.setElevatorWeight(i, hwManager.getElevatorWeight(i));
				model.setElevatorCapacity(i, hwManager.getElevatorCapacity(i));
				model.setTarget(i, hwManager.getTarget(i));

				UpdateServicedFloors(i);
				UpdateElevatorButtons(i);
			}

			for (int i = 0; i < model.getNumOfFloors(); ++i) {
				model.setFloorButtonDown(i, hwManager.getFloorButtonDown(i));
				model.setFloorButtonUp(i, hwManager.getFloorButtonUp(i));
			}

			model.setClockTick(hwManager.getClockTick());
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
		}
		
		
		System.out.println("Model Update DONE!");
	}

	private void UpdateServicedFloors(int elevatorNumber) throws RemoteException {
		for (int i = 0; i < model.getNumOfFloors(); ++i) {
			model.setServicesFloors(elevatorNumber, i, hwManager.getServicesFloors(elevatorNumber, i));
		}
	}

	private void UpdateElevatorButtons(int elevatorNumber) throws RemoteException {
		for (int i = 0; i < model.getNumOfFloors(); ++i) {
			model.setElevatorButton(elevatorNumber, i, hwManager.getElevatorButton(elevatorNumber, i));
		}
	}
}
