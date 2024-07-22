import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Vehicle {
    private String plateNumber;
    private String model;
    private String ownerName;

    public Vehicle(String plateNumber, String model, String ownerName) {
        this.plateNumber = plateNumber;
        this.model = model;
        this.ownerName = ownerName;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getModel() {
        return model;
    }

    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public String toString() {
        return "Vehicle [Plate Number=" + plateNumber + ", Model=" + model + ", Owner=" + ownerName + "]";
    }
}

class Customer {
    private String name;
    private String phone;
    private String email;

    public Customer(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Customer [Name=" + name + ", Phone=" + phone + ", Email=" + email + "]";
    }
}

class RepairJob {
    private Vehicle vehicle;
    private String description;
    private double cost;
    private String status;

    public RepairJob(Vehicle vehicle, String description, double cost) {
        this.vehicle = vehicle;
        this.description = description;
        this.cost = cost;
        this.status = "Pending";
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RepairJob [Vehicle=" + vehicle + ", Description=" + description + ", Cost=" + cost + ", Status=" + status + "]";
    }
}

class RepairShop {
    private List<Vehicle> vehicles;
    private List<Customer> customers;
    private List<RepairJob> repairJobs;

    public RepairShop() {
        vehicles = new ArrayList<>();
        customers = new ArrayList<>();
        repairJobs = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addRepairJob(RepairJob repairJob) {
        repairJobs.add(repairJob);
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<RepairJob> getRepairJobs() {
        return repairJobs;
    }

    public RepairJob findRepairJobByPlateNumber(String plateNumber) {
        for (RepairJob job : repairJobs) {
            if (job.getVehicle().getPlateNumber().equals(plateNumber)) {
                return job;
            }
        }
        return null;
    }
}

public class VehicleRepairingSystem
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RepairShop repairShop = new RepairShop();

        while (true) {
            System.out.println("Vehicle Repairing System");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Add Customer");
            System.out.println("3. Add Repair Job");
            System.out.println("4. View Repair Jobs");
            System.out.println("5. Update Repair Job Status");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Enter plate number: ");
                    String plateNumber = scanner.nextLine();
                    System.out.print("Enter model: ");
                    String model = scanner.nextLine();
                    System.out.print("Enter owner name: ");
                    String ownerName = scanner.nextLine();
                    Vehicle vehicle = new Vehicle(plateNumber, model, ownerName);
                    repairShop.addVehicle(vehicle);
                    System.out.println("Vehicle added successfully!");
                    break;
                case 2:
                    System.out.print("Enter customer name: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    Customer customer = new Customer(customerName, phone, email);
                    repairShop.addCustomer(customer);
                    System.out.println("Customer added successfully!");
                    break;
                case 3:
                    System.out.print("Enter vehicle plate number: ");
                    String jobPlateNumber = scanner.nextLine();
                    System.out.print("Enter repair description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter cost: ");
                    double cost = scanner.nextDouble();
                    scanner.nextLine();
                    Vehicle jobVehicle = repairShop.getVehicles().stream()
                            .filter(v -> v.getPlateNumber().equals(jobPlateNumber))
                            .findFirst().orElse(null);
                    if (jobVehicle == null) {
                        System.out.println("Vehicle not found!");
                    } else {
                        RepairJob repairJob = new RepairJob(jobVehicle, description, cost);
                        repairShop.addRepairJob(repairJob);
                        System.out.println("Repair job added successfully!");
                    }
                    break;
                case 4:
                    System.out.println("Repair Jobs:");
                    for (RepairJob job : repairShop.getRepairJobs()) {
                        System.out.println(job);
                    }
                    break;
                case 5:
                    System.out.print("Enter vehicle plate number: ");
                    String updatePlateNumber = scanner.nextLine();
                    RepairJob repairJob = repairShop.findRepairJobByPlateNumber(updatePlateNumber);
                    if (repairJob == null) {
                        System.out.println("Repair job not found!");
                    } else {
                        System.out.print("Enter new status: ");
                        String status = scanner.nextLine();
                        repairJob.setStatus(status);
                        System.out.println("Repair job status updated successfully!");
                    }
                    break;
                case 6:
                    System.out.println("Exiting system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
