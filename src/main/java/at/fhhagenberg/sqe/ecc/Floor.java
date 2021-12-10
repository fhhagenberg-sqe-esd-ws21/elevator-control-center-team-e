package at.fhhagenberg.sqe.ecc;

public class Floor {

    private boolean serviced;
    private boolean floorRequestButton;
    private boolean floorButtonDown;
    private boolean floorButtonUp;

    public Floor(boolean floorRequestButton, boolean floorButtonDown, boolean floorButtonUp) {
        this.floorRequestButton = floorRequestButton;
        this.floorButtonDown = floorButtonDown;
        this.floorButtonUp = floorButtonUp;
    }

    public boolean isFloorRequestButton() {
        return floorRequestButton;
    }

    public boolean isFloorButtonDown() {
        return floorButtonDown;
    }

    public boolean isFloorButtonUp() {
        return floorButtonUp;
    }

    public boolean isServiced() {
        return serviced;
    }

    public void setServiced(boolean serviced) {
        this.serviced = serviced;
    }

    public void setFloorButtonDown(boolean floorButtonDown) {
        this.floorButtonDown = floorButtonDown;
    }

    public void setFloorButtonUp(boolean floorButtonUp) {
        this.floorButtonUp = floorButtonUp;
    }

    public void setFloorRequestButton(boolean floorRequestButton) {
        this.floorRequestButton = floorRequestButton;
    }
}
