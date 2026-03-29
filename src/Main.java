import java.util.*;

public class Main {
    public static void main(String[] args) {

        int numOfFloors = 10;
        int numOfElevators = 3;

        List<Floor> floors = new ArrayList<>();
        for (int f = 1; f <= numOfFloors; f++) {
            floors.add(new Floor(f, new OutsidePanel(new UpButton(), new DownButton())));
        }

        List<Elevator> elevators = new ArrayList<>();

        for(int i=0; i<numOfElevators; i++){
            List<FloorButton> floorButtons = new ArrayList<>();
            for(int f=0; f<numOfFloors; f++){
                floorButtons.add(new FloorButton(f));
            }
            ElevatorPanel panel = new ElevatorPanel(floorButtons, new OpenDoorButton(), new CloseDoorButton(), new AlarmButton());
            elevators.add(new Elevator(i+1, panel, 700));
        }

        ElevatorManager em = new ElevatorManager(elevators, new ShortestSeekStrategy(), floors);

        Operator operator = new Operator(1, "Operator", em);

        // SIMULATION

        System.out.println("Elevator System Created with "+numOfFloors+ " and "+numOfElevators+" elevators.");

        Elevator e1 = elevators.get(0);
        Elevator e2 = elevators.get(1);
        Elevator e3 = elevators.get(2);

        System.out.println("\n=== Scenario 1: Outside request — Floor 5 UP ===");
        em.handleOutsideRequest(new OutsideRequest(floors.get(4), Direction.UP));

        System.out.println("\n=== Scenario 2: Inside request — Elevator 1 → Floor 8 ===");
        em.handleInsideRequest(e1, new InsideRequest(8));

        System.out.println("\n=== Scenario 3: Strategy picks nearest — Floor 7 DOWN ===");
        em.handleOutsideRequest(new OutsideRequest(floors.get(6), Direction.DOWN));

        System.out.println("\n=== Scenario 4: Operator sets E2 under maintenance ===");
        operator.setMaintenance(e2);

        System.out.println("\n=== Scenario 5: Operator adds Floor 11 ===");
        Floor floor11 = new Floor(11, new OutsidePanel(new UpButton(), new DownButton()));
        operator.addFloor(floor11);

        System.out.println("\n Outside request for newly added floor 11 UP:");
        em.handleOutsideRequest(new OutsideRequest(floor11, Direction.UP));

        System.out.println("\n=== Scenario 6: Overload simulation — Elevator 1 ===");
        e1.addWeight(300);
        e1.addWeight(250);
        e1.addWeight(200);
    }
}