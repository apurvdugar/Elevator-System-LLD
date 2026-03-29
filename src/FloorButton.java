public class FloorButton extends Button{

    int destinationFloor;

    public FloorButton(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    void press() {
        super.isPressed = true;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }
}
