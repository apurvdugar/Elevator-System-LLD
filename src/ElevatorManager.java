import java.util.*;

public class ElevatorManager {

    private List<Floor> floors;
    private final List<Elevator> elevators;
    private final ElevatorCallStrategy strategy;

    public ElevatorManager(List<Elevator> elevators, ElevatorCallStrategy strategy, List<Floor> floors) {
        this.elevators = elevators;
        this.strategy = strategy;
        this.floors = floors;
    }

    public void handleOutsideRequest(OutsideRequest request){
        Elevator assignedElevator = strategy.assignElevator(request, elevators);
        System.out.println("Assigned Elevator: " + assignedElevator.getId());
        int floor = request.floor.floorNo;
        assignedElevator.addRequest(floor);
    }

    public void handleInsideRequest(Elevator elevator, InsideRequest request) {
        System.out.println("Inside request for floor " + request.destinationFloor + " in Elevator " + elevator.getId());
        elevator.addRequest(request.destinationFloor);
    }

    public void addFloor(Floor f) {
        floors.add(f);
    }
}
