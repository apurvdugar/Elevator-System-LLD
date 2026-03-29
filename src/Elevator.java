
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

    public int getId() {
        return id;
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

    public FloorSensor getFloorSensor() {
        return floorSensor;
    }

    public void setUnderMaintenance() {
        this.state = ElevatorState.Under_Maintenance;
        System.out.println("Elevator " + id + " is now under maintenance.");
    }

    public void unsetMaintenance() {
        this.state = ElevatorState.Idle;
        System.out.println("Elevator " + id + " is ready to work.");
    }

    public void addRequest(int floor) {
        if (floor > currentFloor) {
            upQueue.offer(floor);
        } else {
            downQueue.offer(floor);
        }
        move();
    }

    public void move() {
        if (state == ElevatorState.Under_Maintenance) {
            System.out.println("Elevator " + id + " is under maintenance — request ignored.");
            return;
        }
        if (state == ElevatorState.Overloaded) {
            System.out.println("Elevator " + id + " is overloaded — cannot move until load is reduced.");
            return;
        }
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

    public void addWeight(int weight) {
        currentWeight += weight;
        weightSensor.setWeight(currentWeight);
        System.out.println("Elevator " + id + " — current weight: " + currentWeight + " kg / " + maxWeight + " kg");

        if (currentWeight > maxWeight) {
            state = ElevatorState.Overloaded;
            System.out.println("Elevator " + id + " OVERLOADED! Please reduce load.");
            door.open();   // door stays open
            Alarm.trigger();
        }
    }

    public void removeWeight(int weight) {
        currentWeight = Math.max(0, currentWeight - weight);
        weightSensor.setWeight(currentWeight);
        System.out.println("Elevator " + id + " — current weight after exit: " + currentWeight + " kg");

        if (state == ElevatorState.Overloaded && currentWeight <= maxWeight) {
            state = ElevatorState.Idle;
            System.out.println("Elevator " + id + " weight OK — closing door and resuming.");
            door.close();
        }
    }
}
