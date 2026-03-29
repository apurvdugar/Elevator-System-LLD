import java.util.*;

public class ShortestSeekStrategy implements ElevatorCallStrategy {

    @Override
    public Elevator assignElevator(OutsideRequest request, List<Elevator> elevators) {

        Elevator best = null;
        int minDistance = Integer.MAX_VALUE;

        int requestedFloor = request.floor.floorNo;

        for (Elevator e : elevators) {
            int distance = Math.abs(e.getCurrentFloor() - requestedFloor);

            if (distance < minDistance && e.getState() != ElevatorState.Under_Maintenance) {
                minDistance = distance;
                best = e;
            }
        }

        return best;
    }
}