
import java.util.*;

public class Elevator {

    private final int id;
    private ElevatorPanel panel;
    private ElevatorState state;
    private Direction direction;
    private int currentFloor;
    private final int maxWeight;
    private int currentWeight;
    private Door door;

    private PriorityQueue<Integer> upQueue = new PriorityQueue<>();
    private PriorityQueue<Integer> downQueue = new PriorityQueue<>(Collections.reverseOrder());

    private WeightSensor weightSensor;
    private FloorSensor floorSensor;

    public Elevator(int id, ElevatorPanel panel, int maxWeight) {
        this.id = id;
        this.panel = panel;
        this.maxWeight = maxWeight;
        this.currentFloor = 0;
        this.currentWeight = 0;
        this.state = ElevatorState.Idle;
        this.direction = Direction.UP;
        this.weightSensor = new WeightSensor();
        this.floorSensor = new FloorSensor();
        this.door = new Door();
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public Direction getDirection() {
        return direction;
    }

    public ElevatorState getState() {
        return state;
    }

    public void addRequest(int floor) {
        if (floor > currentFloor) {
            upQueue.offer(floor);
        } else {
            downQueue.offer(floor);
        }
    }

    public void move() {
        if (direction == Direction.UP) {
            moveUp();
        } else {
            moveDown();
        }
    }

    private void moveUp() {
        while (!upQueue.isEmpty()) {
            int next = upQueue.poll();
            travelTo(next);
        }
        direction = Direction.DOWN;
    }

    private void moveDown() {
        while (!downQueue.isEmpty()) {
            int next = downQueue.poll();
            travelTo(next);
        }
        direction = Direction.UP;
    }

    private void travelTo(int floor) {
        System.out.println("Elevator " + id + " moving to floor " + floor);
        currentFloor = floor;
        door.open();
        door.close();
    }
}
