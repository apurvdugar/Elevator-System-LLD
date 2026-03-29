public abstract class Button {

    boolean isPressed;

    public Button() {
        this.isPressed = false;
    }

    abstract void press();

    public void reset() {
        this.isPressed = false;
    }
}
