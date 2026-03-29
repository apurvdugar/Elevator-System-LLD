import java.util.*;

public class ElevatorManager {

    private final List<Elevator> elevators;
    private final ElevatorCallStrategy strategy;

    public ElevatorManager(List<Elevator> elevators, ElevatorCallStrategy strategy) {
        this.elevators = elevators;
        this.strategy = strategy;
    }

    public void handleOutsideRequest(OutsideRequest request){
        Elevator assignedElevator = strategy.assignElevator(request, elevators);
        System.out.println("Assigned Elevator: " + assignedElevator);
        int floor = request.floor.floorNo;

        assignedElevator.addRequest(floor);
    }
}
