package at.fhhagenberg.sqe.ecc;

/**
 * Class representing a floor in a building. Stores floor information used in ECC.
 * @author Markus Lindner - s2010567018
 */
public class Floor {

    private boolean serviced;
    private boolean floorRequestButton;
    private boolean floorButtonDown;
    private boolean floorButtonUp;

    /**
     * Constructor of class Floor.
     * @param floorRequestButton Specifies if elevators request button is activated on creation.
     * @param floorButtonDown Specifies if the button for travelling down is activated on creation.
     * @param floorButtonUp Specifies if the button for travelling up is activated on creation.
     * @param floorServiced Specifies if this floor is serviced by the elevator.
     */
    public Floor(boolean floorRequestButton, boolean floorButtonDown, boolean floorButtonUp, boolean floorServiced) {
        this.floorRequestButton = floorRequestButton;
        this.floorButtonDown = floorButtonDown;
        this.floorButtonUp = floorButtonUp;
        this.serviced = floorServiced;
    }

    /**
     * Returns if the floor is requested for an elevator.
     * @return True if requested, false otherwise.
     */
    public boolean isFloorRequestButton() {
        return floorRequestButton;
    }

    /**
     * Returns if the button for travelling down is activated.
     * @return True if the button is activated, false otherwise.
     */
    public boolean isFloorButtonDown() {
        return floorButtonDown;
    }

    /**
     * Returns if the button for travelling up is activated.
     * @return True if the button is activated, false otherwise.
     */
    public boolean isFloorButtonUp() {
        return floorButtonUp;
    }

    /**
     * Returns if the floor is serviced by an elevator.
     * @return True if the floor is serviced by the current elevator, false otherwise.
     */
    public boolean isServiced() {
        return serviced;
    }

    /**
     * Setter for flag serviced. Using this flag an elevator specifies if the floor is serviced or not.
     * @param serviced True if the floor should be serviced, false otherwise.
     */
    public void setServiced(boolean serviced) {
        this.serviced = serviced;
    }

    /**
     * Sets the status of the floor button for requesting travelling down.
     * @param floorButtonDown True if the button should be activated, false if not.
     */
    public void setFloorButtonDown(boolean floorButtonDown) {
        this.floorButtonDown = floorButtonDown;
    }

    /**
     * Sets the status of the floor button for requesting travelling up.
     * @param floorButtonUp True if the button should be activated, false if not.
     */
    public void setFloorButtonUp(boolean floorButtonUp) {
        this.floorButtonUp = floorButtonUp;
    }

    /**
     * Used by an elevator to specify if a floor request button is set or not.
     * @param floorRequestButton True if the button should be activated, false if not.
     */
    public void setFloorRequestButton(boolean floorRequestButton) {
        this.floorRequestButton = floorRequestButton;
    }
}
