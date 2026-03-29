public class Operator {

    private int id;
    private String name;
    private final ElevatorManager em;

    public Operator(int id, String name, ElevatorManager em) {
        this.id = id;
        this.name = name;
        this.em = em;
    }

    public void addFloor(Floor floor) {
        em.addFloor(floor);
        System.out.println("Operator added Floor " + floor.floorNo + ".");
    }

    public void setMaintenance(Elevator elevator) {
        elevator.setUnderMaintenance();
        System.out.println("Operator set Elevator " + elevator.getId() + " UNDER MAINTENANCE.");
    }

    public void unsetMaintenance(Elevator elevator) {
        elevator.unsetMaintenance();
        System.out.println("Operator made Elevator " + elevator.getId() + " restored to service.");
    }
}
