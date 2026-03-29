public class Door {

    private DoorStatus doorStatus;

    public Door() {
        this.doorStatus = DoorStatus.Close;
    }

    public void open(){
        this.doorStatus = DoorStatus.Open;
        System.out.println("Door opened.");
    }

    public void close(){
        this.doorStatus = DoorStatus.Close;
        System.out.println("Door closed.");
    }
}
