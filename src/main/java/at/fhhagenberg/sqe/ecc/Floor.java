package at.fhhagenberg.sqe.ecc;

public class Floor {

    private boolean floorRequestButton;
    private boolean floorButtonDown;
    private boolean floorButtonUp;
    private int floorHeight;

    public Floor(boolean floorRequestButton, boolean floorButtonDown, boolean floorButtonUp, int floorHeight) {
        this.floorRequestButton = floorRequestButton;
        this.floorButtonDown = floorButtonDown;
        this.floorButtonUp = floorButtonUp;
        this.floorHeight = floorHeight;
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

    public int getFloorHeight() {
        return floorHeight;
    }

    public void setFloorButtonDown(boolean floorButtonDown) {
        this.floorButtonDown = floorButtonDown;
    }

    public void setFloorButtonUp(boolean floorButtonUp) {
        this.floorButtonUp = floorButtonUp;
    }

    public void setFloorHeight(int floorHeight) {
        this.floorHeight = floorHeight;
    }

    public void setFloorRequestButton(boolean floorRequestButton) {
        this.floorRequestButton = floorRequestButton;
    }
}
