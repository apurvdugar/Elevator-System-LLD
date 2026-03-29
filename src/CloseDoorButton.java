public class CloseDoorButton extends Button {

    private Door door;

    public void setDoor(Door door) {
        this.door = door;
    }

    void press() {
        if (!isPressed) {
            isPressed = true;
            if (door != null) door.close();
        }
    }
}