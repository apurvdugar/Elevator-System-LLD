import java.util.*;

public class Main {
    public static void main(String[] args) {

        int floors = 10;
        int numOfElevators = 5;

        List<Elevator> elevators = new ArrayList<>();
        List<FloorButton> floorButtons = new ArrayList<>();

        for(int i=0; i<numOfElevators; i++){
            for(int f=0; f<floors; f++){
                floorButtons.add(new FloorButton(f));
            }
            ElevatorPanel panel = new ElevatorPanel(floorButtons, new OpenDoorButton(), new CloseDoorButton(), new AlarmButton());
            elevators.add(new Elevator(i+1, panel, 700));
        }

        ElevatorManager em = new ElevatorManager(elevators, new ShortestSeekStrategy());
    }
}