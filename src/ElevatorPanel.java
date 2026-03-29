import java.util.*;

public class ElevatorPanel {

    List<FloorButton> floorButtons;
    OpenDoorButton openDoorButton;
    CloseDoorButton closeDoorButton;
    AlarmButton alarmButton;

    public ElevatorPanel(List<FloorButton> floorButtons, OpenDoorButton openDoorButton, CloseDoorButton closeDoorButton, AlarmButton alarmButton) {
        this.floorButtons = floorButtons;
        this.openDoorButton = openDoorButton;
        this.closeDoorButton = closeDoorButton;
        this.alarmButton = alarmButton;
    }
}
