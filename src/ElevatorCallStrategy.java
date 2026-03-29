import java.util.*;

public interface ElevatorCallStrategy {

    Elevator assignElevator(OutsideRequest request, List<Elevator> elevators);
}
