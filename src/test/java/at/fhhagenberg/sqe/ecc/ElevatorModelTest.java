package at.fhhagenberg.sqe.ecc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

/**
 *
 * @author Bernhard Wandl - s2010567008
 */
public class ElevatorModelTest {

	private static ElevatorModel model = new ElevatorModel(3, 10, 4);

	@Mock
	private static ElevatorGuiUpdater guiUpdater = mock(ElevatorGuiUpdater.class);

	@BeforeAll
	public static void setUpGuiUpdater() {
		model.setGuiUpdater(guiUpdater);
	}

	@Test
	public void testElevatorModel_GetNumOfElevators()
	{
		assertEquals(3, model.getNumOfElevators());
	}
	
	@Test
	void testElevatorModel_GetNumOfFloors()
	{
		assertEquals(10, model.getNumOfFloors());
	}
	
	@Test
	void testElevatorModel_GetFloorHeight()
	{
		assertEquals(4, model.getFloorHeight());
	}
	
	@Test
	void testElevatorModel_getCommittedDirectionTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getCommittedDirection(-1);
		});
	}
	
	@Test
	void testElevatorModel_getCommittedDirectionTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getCommittedDirection(3);
		});
	}
	
	@Test
	void testElevatorModel_setCommittedDirectionTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setCommittedDirection(-1, 5);
		});
	}
	
	@Test
	void testElevatorModel_setCommittedDirectionTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setCommittedDirection(3, 5);
		});
	}
	
	@Test
	void testElevatorModel_CommittedDirection()
	{
		model.setCommittedDirection(1, 1);
		assertEquals(1, model.getCommittedDirection(1));
	}

	@Test
	void testElevatorModel_CommittedDirectionInvalidTooLow()
	{
		assertThrows(IllegalArgumentException.class, () -> model.setCommittedDirection(1, -1));
	}

	@Test
	void testElevatorModel_CommittedDirectionInvalidTooHigh()
	{
		assertThrows(IllegalArgumentException.class, () -> model.setCommittedDirection(1, 3));
	}

	@Test
	void testElevatorModel_getServicesFloorsTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getServicesFloors(-1, 5);
		});
	}
	
	@Test
	void testElevatorModel_getServicesFloorsTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getServicesFloors(3, 5);
		});
	}
	
	@Test
	void testElevatorModel_getServicesFloorsTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getServicesFloors(1, -1);
		});
	}
	
	@Test
	void testElevatorModel_getServicesFloorsTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getServicesFloors(1, 10);
		});
	}
	
	@Test
	void testElevatorModel_setServicesFloorsTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setServicesFloors(-1, 5, true);
		});
	}
	
	@Test
	void testElevatorModel_setServicesFloorsTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setServicesFloors(3, 5, true);
		});
	}
	
	@Test
	void testElevatorModel_setServicesFloorsTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setServicesFloors(1, -1, true);
		});
	}
	
	@Test
	void testElevatorModel_setServicesFloorsTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setServicesFloors(1, 10, true);
		});
	}
	
	@Test
	void testElevatorModel_ServicesFloors()
	{
		model.setServicesFloors(2, 9, true);
		assertEquals(true, model.getServicesFloors(2, 9));
	}
	
	@Test
	void testElevatorModel_getTargetTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getTarget(-1);
		});
	}
	
	@Test
	void testElevatorModel_getTargetTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getTarget(3);
		});
	}
	
	@Test
	void testElevatorModel_setTargetTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setTarget(-1, 5);
		});
	}
	
	@Test
	void testElevatorModel_setTargetTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setTarget(3, 5);
		});
	}
	
	@Test
	void testElevatorModel_setTargetTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setTarget(1, -1);
		});
	}
	
	@Test
	void testElevatorModel_setTargetTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setTarget(1, 10);
		});
	}
	
	@Test
	void testElevatorModel_Target()
	{
		model.setTarget(0, 9);
		assertEquals(9, model.getTarget(0));
	}
	
	@Test
	void testElevatorModel_getElevatorAccelTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorAccel(-1);
		});
	}
	
	@Test
	void testElevatorModel_getElevatorAccelTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorAccel(3);
		});
	}
	
	
	@Test
	void testElevatorModel_setElevatorAccelTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorAccel(-1, 923);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorAccelTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorAccel(3, 123);
		});
	}
	
	@Test
	void testElevatorModel_ElevatorAccel()
	{
		model.setElevatorAccel(2, 49);
		assertEquals(49, model.getElevatorAccel(2));
	}
	
	@Test
	void testElevatorModel_getElevatorButtonTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorButton(-1, 5);
		});
	}
	
	@Test
	void testElevatorModel_getElevatorButtonTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorButton(3, 5);
		});
	}
	
	@Test
	void testElevatorModel_getElevatorButtonTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorButton(1, -1);
		});
	}
	
	@Test
	void testElevatorModel_getElevatorButtonTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorButton(1, 10);
		});
	}
	
	
	@Test
	void testElevatorModel_setElevatorButtonTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorButton(-1, 4, false);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorButtonTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorButton(3, 3, false);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorButtonTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorButton(0, -1, false);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorButtonTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorButton(0, 10, false);
		});
	}
	
	@Test
	void testElevatorModel_ElevatorButton()
	{
		model.setElevatorButton(1, 6, true);
		assertEquals(true, model.getElevatorButton(1, 6));
	}
	
	@Test
	void testElevatorModel_getElevatorDoorStatusTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorDoorStatus(-1);
		});
	}
	
	@Test
	void testElevatorModel_getElevatorDoorStatusTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorDoorStatus(3);
		});
	}
	
	
	@Test
	void testElevatorModel_setElevatorDoorStatusTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorDoorStatus(-1, 923);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorDoorStatusTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorDoorStatus(3, 123);
		});
	}
	
	@Test
	void testElevatorModel_ElevatorDoorStatus()
	{
		model.setElevatorDoorStatus(1, 3);
		assertEquals(3, model.getElevatorDoorStatus(1));
	}
	
	@Test
	void testElevatorModel_getElevatorFloorTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorFloor(-1);
		});
	}
	
	@Test
	void testElevatorModel_getElevatorFloorTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorFloor(3);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorFloorTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorFloor(-1, 5);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorFloorTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorFloor(3, 5);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorFloorTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorFloor(1, -1);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorFloorTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorFloor(1, 10);
		});
	}
	
	@Test
	void testElevatorModel_ElevatorFloor()
	{
		model.setElevatorFloor(0, 3);
		assertEquals(3, model.getElevatorFloor(0));
	}
	
	@Test
	void testElevatorModel_getElevatorPositionTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorPosition(-1);
		});
	}
	
	@Test
	void testElevatorModel_getElevatorPositionTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorPosition(3);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorPositionTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorPosition(-1, 5);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorPositionTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorPosition(3, 5);
		});
	}
	
	@Test
	void testElevatorModel_ElevatorPosition()
	{
		model.setElevatorPosition(1, 192);
		assertEquals(192, model.getElevatorPosition(1));
	}
	
	@Test
	void testElevatorModel_getElevatorSpeedTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorSpeed(-1);
		});
	}
	
	@Test
	void testElevatorModel_getElevatorSpeedTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorSpeed(3);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorSpeedTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorSpeed(-1, 5);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorSpeedTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorSpeed(3, 5);
		});
	}
	
	@Test
	void testElevatorModel_ElevatorSpeed()
	{
		model.setElevatorSpeed(0, 203);
		assertEquals(203, model.getElevatorSpeed(0));
	}
	
	@Test
	void testElevatorModel_getElevatorWeightTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorWeight(-1);
		});
	}
	
	@Test
	void testElevatorModel_getElevatorWeightTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorWeight(3);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorWeightTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorWeight(-1, 5);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorWeightTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorWeight(3, 5);
		});
	}
	
	@Test
	void testElevatorModel_ElevatorWeight()
	{
		model.setElevatorWeight(2, 900);
		assertEquals(900, model.getElevatorWeight(2));
	}
	
	@Test
	void testElevatorModel_getElevatorCapacityTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorCapacity(-1);
		});
	}
	
	@Test
	void testElevatorModel_getElevatorCapacityTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getElevatorCapacity(3);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorCapacityTooSmallElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorCapacity(-1, 5);
		});
	}
	
	@Test
	void testElevatorModel_setElevatorCapacityTooHighElevator()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setElevatorCapacity(3, 5);
		});
	}
	
	@Test
	void testElevatorModel_ElevatorCapacity()
	{
		model.setElevatorCapacity(2, 450);
		assertEquals(450, model.getElevatorCapacity(2));
	}
	
	@Test
	void testElevatorModel_getFloorButtonDownTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getFloorButtonDown(-1);
		});
	}
	
	@Test
	void testElevatorModel_getFloorButtonDownTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getFloorButtonDown(10);
		});
	}
	
	@Test
	void testElevatorModel_setFloorButtonDownTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setFloorButtonDown(-1, true);
		});
	}
	
	@Test
	void testElevatorModel_setFloorButtonDownTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setFloorButtonDown(10, true);
		});
	}
	
	@Test
	void testElevatorModel_FloorButtonDown()
	{
		model.setFloorButtonDown(2, true);
		assertEquals(true, model.getFloorButtonDown(2));
	}
	
	@Test
	void testElevatorModel_getFloorButtonUpTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getFloorButtonUp(-1);
		});
	}
	
	@Test
	void testElevatorModel_getFloorButtonUpTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.getFloorButtonUp(10);
		});
	}
	
	@Test
	void testElevatorModel_setFloorButtonUpTooSmallFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setFloorButtonUp(-1, true);
		});
	}
	
	@Test
	void testElevatorModel_setFloorButtonUpTooHighFloor()
	{
		assertThrows(IllegalArgumentException.class, () ->{
			model.setFloorButtonUp(10, true);
		});
	}
	
	@Test
	void testElevatorModel_FloorButtonUp()
	{
		model.setFloorButtonUp(6, true);
		assertEquals(true, model.getFloorButtonUp(6));
	}
	
	
	@Test
	void testElevatorModel_LoggingLevel()
	{
		model.setLogging(6);
		assertEquals(6, model.getLogging());
	}
	
	@Test
	void testElevatorModel_ClockTick()
	{
		model.setClockTick(9);
		assertEquals(9, model.getClockTick());
	}
		
}
