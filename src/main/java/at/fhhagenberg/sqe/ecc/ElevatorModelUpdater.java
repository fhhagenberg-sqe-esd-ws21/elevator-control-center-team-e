package at.fhhagenberg.sqe.ecc;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Class to handle cyclic updates of low level elevator system.
 * 
 * @author Markus Lindner - s2010567018
 */
public class ElevatorModelUpdater {

	IElevatorHardwareManager hwManager;
	ElevatorModel model;

	/**
	 * Constructor of the class.
	 * 
	 * @param hwManager 		Unit which should be used to communicate
	 *                          with low level elevator system.
	 * @param model             The model which should be used to store the
	 *                          information at.
	 */
	public ElevatorModelUpdater(IElevatorHardwareManager hwManager, ElevatorModel model) {
		this.hwManager = hwManager;
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

				int result = hwManager.getCommittedDirection(i);
				if(model.getCommittedDirection(i) != result) {
					model.setCommittedDirection(i, result);
				}

				result = hwManager.getElevatorAccel(i);
				if(model.getElevatorAccel(i) != result) {
					model.setElevatorAccel(i, result);
				}
				
				result = hwManager.getElevatorDoorStatus(i);
				if(model.getElevatorDoorStatus(i) != result) {
					model.setElevatorDoorStatus(i, result);
				}
				
				result = hwManager.getElevatorFloor(i);
				if(model.getElevatorFloor(i) != result) {
					model.setElevatorFloor(i, result);
				}
				
				result = hwManager.getElevatorPosition(i);
				if(model.getElevatorPosition(i) != result) {
					model.setElevatorPosition(i, result);
				}
				
				result = hwManager.getElevatorSpeed(i);
				if(model.getElevatorSpeed(i) != result) {
					model.setElevatorSpeed(i, result);
				}
				
				result = hwManager.getElevatorWeight(i);
				if(model.getElevatorWeight(i) != result) {
					model.setElevatorWeight(i, result);
				}
				
				result = hwManager.getElevatorCapacity(i);
				if(model.getElevatorCapacity(i) != result) {
					model.setElevatorCapacity(i, result);
				}
				
				result = hwManager.getTarget(i);
				if(model.getTarget(i) != result) {
					model.setTarget(i, result);
				}

				UpdateServicedFloors(i);
				UpdateElevatorButtons(i);
			}

			for (int i = 0; i < model.getNumOfFloors(); ++i) {
				boolean bRes = hwManager.getFloorButtonDown(i);
				if(model.getFloorButtonDown(i) != bRes) {
					model.setFloorButtonDown(i, bRes);
				}

				bRes = hwManager.getFloorButtonUp(i);
				if(model.getFloorButtonUp(i) != bRes) {
					model.setFloorButtonUp(i, bRes);
				}
			}

			var clockTickData = hwManager.getClockTick();
			if(model.getClockTick() != clockTickData) {
				model.setClockTick(clockTickData);
			}

		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			model.setErrorMessage("Connection Error: Please check connection to low-level elevator system.");
		}
	}

	private void UpdateServicedFloors(int elevatorNumber) throws RemoteException {
		for (int i = 0; i < model.getNumOfFloors(); ++i) {

			var result = hwManager.getServicesFloors(elevatorNumber, i);
			if(model.getServicesFloors(elevatorNumber, i) != result) {
				model.setServicesFloors(elevatorNumber, i, result);
			}
		}
	}

	private void UpdateElevatorButtons(int elevatorNumber) throws RemoteException {
		for (int i = 0; i < model.getNumOfFloors(); ++i) {
			var result = hwManager.getElevatorButton(elevatorNumber, i);
			if(model.getElevatorButton(elevatorNumber, i) != result) {
				model.setElevatorButton(elevatorNumber, i, result);
			}
		}
	}
}
