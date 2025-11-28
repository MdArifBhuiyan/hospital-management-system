import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract class
abstract class Person {
    
    //Encapsulation
    private String id;
    private String name;
    private int age;

    public Person(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getId() { return id; }

    // Abstract method enforcing specific behavior in child classes
    public abstract void performRole();
    
    // Method Overriding - polymorphism
    @Override
    public String toString() {
        return "[" + id + "] " + name + " (" + age + " yrs)";
    }
}

// Inheritance
class Doctor extends Person {
    private String specialization;
    private String departmentId;

    public Doctor(String id, String name, int age, String specialization) {
        super(id, name, age);
        this.specialization = specialization;
        this.departmentId = "";
    }

    // Method Overriding - polymorphism
    @Override
    public void performRole() {
        System.out.println("Doctor " + getName() + " (" + specialization + ") is seeing patients.");
    }

    // Method Overriding - polymorphism
    @Override
    public String toString() {
        return super.toString() + " - [Doctor: " + specialization + "]";
    }

    public void diagnosePatient(Patient p, String disease) {
        System.out.println(getName() + " has diagnosed " + p.getName() + " with " + disease);
        p.setDiagnosis(disease);
    }
    
    public void setDepartment(String deptId) { this.departmentId = deptId; }
    public String getDepartmentId() { return departmentId; }
}

// Inheritance
class Nurse extends Person {
    private String shift;

    public Nurse(String id, String name, int age, String shift) {
        super(id, name, age);
        this.shift = shift;
    }
    
    // Method Overriding - polymorphism
    @Override
    public void performRole() {
        System.out.println("Nurse " + getName() + " is on " + shift + " shift checking vitals.");
    }

    // Method Overriding - polymorphism
    @Override
    public String toString() {
        return super.toString() + " - [Nurse: " + shift + " Shift]";
    }
}

// Inheritance
class Patient extends Person {
    private String illness;
    private String diagnosis;
    private boolean admitted;

    public Patient(String id, String name, int age, String illness) {
        super(id, name, age);
        this.illness = illness;
        this.admitted = true;
        this.diagnosis = "Pending";
    }
    
    // Method Overriding - polymorphism
    @Override
    public void performRole() {
        System.out.println("Patient " + getName() + " is recovering from " + illness);
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    
    public String getDiagnosis() { return diagnosis; }
    public boolean isAdmitted() { return admitted; }
    public void discharge() { this.admitted = false; }
    
    // Method Overriding - polymorphism
    @Override
    public String toString() {
        String status = admitted ? "Admitted" : "Discharged";
        return super.toString() + " - Complaint: " + illness + " | Diagnosis: " + diagnosis + " | Status: " + status;
    }
}

// New class for Departments
class Department {
    private String id;
    private String name;
    private String location;
    
    public Department(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }
    
    public String getId() { return id; }
    public String getName() { return name; }
    
    @Override
    public String toString() {
        return "[" + id + "] " + name + " - Location: " + location;
    }
}

// New class for Appointments
class Appointment {
    private String id;
    private String patientId;
    private String doctorId;
    private String date;
    private String time;
    private String status;
    
    public Appointment(String id, String patientId, String doctorId, String date, String time) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
        this.status = "Scheduled";
    }
    
    public String getId() { return id; }
    public String getPatientId() { return patientId; }
    public String getDoctorId() { return doctorId; }
    public void setStatus(String status) { this.status = status; }
    
    @Override
    public String toString() {
        return "[" + id + "] Patient: " + patientId + " | Doctor: " + doctorId + 
               " | Date: " + date + " " + time + " | Status: " + status;
    }
}

// New class for Ambulance
class Ambulance {
    private String id;
    private String vehicleNumber;
    private String driverName;
    private String status;
    
    public Ambulance(String id, String vehicleNumber, String driverName) {
        this.id = id;
        this.vehicleNumber = vehicleNumber;
        this.driverName = driverName;
        this.status = "Available";
    }
    
    public String getId() { return id; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    @Override
    public String toString() {
        return "[" + id + "] Vehicle: " + vehicleNumber + " | Driver: " + driverName + 
               " | Status: " + status;
    }
}

// New class for Emergency Calls
class EmergencyCall {
    private String id;
    private String callerName;
    private String location;
    private String emergencyType;
    private String status;
    private String assignedAmbulanceId;
    
    public EmergencyCall(String id, String callerName, String location, String emergencyType) {
        this.id = id;
        this.callerName = callerName;
        this.location = location;
        this.emergencyType = emergencyType;
        this.status = "Pending";
        this.assignedAmbulanceId = "";
    }
    
    public String getId() { return id; }
    public String getStatus() { return status; }
    public void assignAmbulance(String ambId) { 
        this.assignedAmbulanceId = ambId;
        this.status = "Dispatched";
    }
    
    @Override
    public String toString() {
        String amb = assignedAmbulanceId.isEmpty() ? "None" : assignedAmbulanceId;
        return "[" + id + "] " + emergencyType + " at " + location + 
               " | Caller: " + callerName + " | Status: " + status + " | Ambulance: " + amb;
    }
}

// New class for Medical Records
class MedicalRecord {
    private String id;
    private String patientId;
    private String doctorId;
    private String symptoms;
    private String diagnosis;
    private String prescription;
    private String date;
    
    public MedicalRecord(String id, String patientId, String doctorId, String date) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.symptoms = "";
        this.diagnosis = "";
        this.prescription = "";
    }
    
    public void setDetails(String symptoms, String diagnosis, String prescription) {
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
    }
    
    @Override
    public String toString() {
        return "[" + id + "] Patient: " + patientId + " | Doctor: " + doctorId + 
               " | Date: " + date + "\n                                                        " +
               "Symptoms: " + symptoms + " | Diagnosis: " + diagnosis + 
               " | Prescription: " + prescription;
    }
}

class HospitalUtils {
    
    // Search by ID (Method Overloading - polymorphism)
    public static Person findPerson(List<Person> people, String id) {
        for (Person p : people) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    // Search by name & age(Method Overloading - polymorphism)
    public static Person findPerson(List<Person> people, String name, int age) {
        for (Person p : people) {
            if (p.getName().equalsIgnoreCase(name) && p.getAge() == age) {
                return p;
            }
        }
        return null;
    }
}

class HospitalBilling {
    //Option 7: Generate and Shows specific bills
    // Standard Bill (Method Overloading - polymorphism)
    public void generateBill(Patient p, int amount) {
        System.out.println();
        System.out.println("                                                          --- FINAL BILL ---");
        System.out.println("                                                        Patient: " + p.getName());
        System.out.println("                                                        Total Amount: BDT " + amount);
    }

    // Discounted Bill (Method Overloading - polymorphism)
    public void generateBill(Patient p, int amount, int insuranceCoverage) {
        System.out.println();
        System.out.println("                                                          --- FINAL BILL (INSURANCE APPLIED) ---");
        System.out.println("                                                        Patient: " + p.getName());
        System.out.println("                                                        Base Amount: BDT " + amount);
        System.out.println("                                                        Insurance Paid: -BDT " + insuranceCoverage);
        System.out.println("                                                        Patient Pays: BDT " + (amount - insuranceCoverage));
    }
}

// Main class
public class HospitalManagementSystem {
    
    // polymorphism in stafflist  
    private static List<Person> staffList = new ArrayList<>();
    private static List<Patient> patientList = new ArrayList<>();
    private static List<Department> departmentList = new ArrayList<>();
    private static List<Appointment> appointmentList = new ArrayList<>();
    private static List<Ambulance> ambulanceList = new ArrayList<>();
    private static List<EmergencyCall> emergencyList = new ArrayList<>();
    private static List<MedicalRecord> medicalRecordList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Pre-populating some data
        departmentList.add(new Department("DEPT01", "Cardiology", "Building A, Floor 2"));
        departmentList.add(new Department("DEPT02", "Neurology", "Building B, Floor 1"));
        departmentList.add(new Department("DEPT03", "Orthopedics", "Building A, Floor 3"));
        departmentList.add(new Department("DEPT04", "Pediatrics", "Building C, Floor 1"));
        
        staffList.add(new Doctor("D001", "Dr. Anisur Rahman", 45, "Cardiology"));
        staffList.add(new Doctor("D002", "Dr. Fatema Begum", 50, "Neurology"));
        staffList.add(new Doctor("D003", "Dr. Kamal Hossain", 55, "Orthopedics"));
        staffList.add(new Doctor("D004", "Dr. Nasreen Sultana", 40, "Pediatrics"));
        staffList.add(new Nurse("N001", "Shirin Akter", 29, "Night"));
        staffList.add(new Nurse("N002", "Rahim Uddin", 32, "Day"));
        staffList.add(new Nurse("N003", "Salma Khatun", 25, "Morning"));
        staffList.add(new Nurse("N004", "Joya Das", 27, "Evening"));
        
        patientList.add(new Patient("P001", "Rafiqul Islam", 30, "Chest Pain"));
        patientList.add(new Patient("P002", "Karim Ahmed", 45, "High Fever"));
        patientList.add(new Patient("P003", "Sumaiya Parvin", 22, "Dengue"));
        patientList.add(new Patient("P004", "Amit Roy", 12, "Fracture"));
        
        ambulanceList.add(new Ambulance("AMB01", "DHK-1234", "Abdul Karim"));
        ambulanceList.add(new Ambulance("AMB02", "DHK-5678", "Shakil Ahmed"));
        
        // loop for menu
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("                                                        ------------------------------------------");
            System.out.println("                                                        ---     HOSPITAL MANAGEMENT SYSTEM     ---");
            System.out.println("                                                        ------------------------------------------");
            System.out.println("                                                        1. Register New Patient");
            System.out.println("                                                        2. View Staff Details");
            System.out.println("                                                        3. View Staff Activity");
            System.out.println("                                                        4. View Patient Activity");
            System.out.println("                                                        5. View All Patients");
            System.out.println("                                                        6. Doctor Diagnosis");
            System.out.println("                                                        7. Generate Bill");
            System.out.println("                                                        8. Search Patient");
            System.out.println("                                                        9. View Departments");
            System.out.println("                                                        10. Schedule Appointment");
            System.out.println("                                                        11. View Appointments");
            System.out.println("                                                        12. Emergency Call & Ambulance");
            System.out.println("                                                        13. Create Medical Record");
            System.out.println("                                                        14. View Medical Records");
            System.out.println("                                                        15. Exit");
            System.out.println("                                                        ------------------------------------------");
            System.out.print("                                                        Enter Choice: ");
            
            String input = scanner.nextLine();
            int choice = -1;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("                                                        Invalid Input!");
                continue;
            }

            switch (choice) {
                case 1:
                    registerPatient();
                    break;
                case 2:
                    viewStaffDetails();
                    break;
                case 3:
                    viewStaffActivity();
                    break;
                case 4:
                    viewPatientActivity();
                    break;
                case 5:
                    viewPatients();
                    break;
                case 6:
                    performDiagnosis();
                    break;
                case 7:
                    billingProcess();
                    break;
                case 8:
                    searchSystem();
                    break;
                case 9:
                    viewDepartments();
                    break;
                case 10:
                    scheduleAppointment();
                    break;
                case 11:
                    viewAppointments();
                    break;
                case 12:
                    emergencyMenu();
                    break;
                case 13:
                    createMedicalRecord();
                    break;
                case 14:
                    viewMedicalRecords();
                    break;
                case 15:
                    // loop for menu exit
                    running = false;
                    System.out.println("                                                        System Exiting...");
                    break;
                default:
                    System.out.println("                                                        Invalid Choice!");
            }
        }
    }
    
    //Option 1: Patient registeration
    private static void registerPatient() {
        System.out.println();
        System.out.println("                                                               --- REGISTER PATIENT ---");
        
        System.out.print("                                                        Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("                                                        Enter Age: ");
        String ageStr = scanner.nextLine();
        int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            System.out.println("                                                        Invalid Age! Registration Cancelled.");
            return;
        }
        System.out.print("                                                        Enter Illness: ");
        String illness = scanner.nextLine();
        
        // Patient ID generation
        String id = String.format("P%03d", patientList.size() + 1); 
        
        Patient newPatient = new Patient(id, name, age, illness);
        patientList.add(newPatient);
        System.out.println("                                                        Patient Registered Successfully! ID: " + id);
        // Pause logic on success
        System.out.println();
        System.out.print("                                                        Type 0 to go back: ");
        while (!scanner.nextLine().equals("0")) {
            System.out.print("                                                        Type 0 to go back: ");
        }
    }

    // Option 2: Shows staff details
    private static void viewStaffDetails() {
        System.out.println();
        System.out.println("                                                                        --- STAFF DETAILS ---");
        for (Person p : staffList) {
            System.out.println("                                                        " + p);
        }
        // Pause logic
        System.out.println();
        System.out.print("                                                        Type 0 to go back: ");
        while (!scanner.nextLine().equals("0")) {
            System.out.print("                                                        Type 0 to go back: ");
        }
    }

    // Option 3: Shows staff specific activity
    private static void viewStaffActivity() {
        System.out.println();
        System.out.println("                                                                         --- STAFF ACTIVITY ---");
        for (Person p : staffList) {
            System.out.print("                                                        ");
            p.performRole(); 
        }
        // Pause logic
        System.out.println();
        System.out.print("                                                        Type 0 to go back: ");
        while (!scanner.nextLine().equals("0")) {
            System.out.print("                                                        Type 0 to go back: ");
        }
    }

    // Option 4: Shows Patient specific activity
    private static void viewPatientActivity() {
        System.out.println();
        System.out.println("                                                                       --- PATIENT ACTIVITY ---");
        for (Patient p : patientList) {
            System.out.print("                                                        ");
            p.performRole(); 
        }
        // Pause logic
        System.out.println();
        System.out.print("                                                        Type 0 to go back: ");
        while (!scanner.nextLine().equals("0")) {
            System.out.print("                                                        Type 0 to go back: ");
        }
    }
    
    // Option 5:Shows patient list
    private static void viewPatients() {
        System.out.println();
        System.out.println("                                                                                --- ADMITTED PATIENTS ---");
        for (Patient p : patientList) {
            System.out.println("                                                        " + p);
        }
        // Pause logic
        System.out.println();
        System.out.print("                                                        Type 0 to go back: ");
        while (!scanner.nextLine().equals("0")) {
            System.out.print("                                                        Type 0 to go back: ");
        }
    }

    // Option 6: Doctor performs diagnosis
    private static void performDiagnosis() {
        System.out.println();
        System.out.println("                                                                        --- DOCTOR DIAGNOSIS ---");
        
        System.out.print("                                                        Enter Patient ID: ");
        String pId = scanner.nextLine();
        Patient p = (Patient) HospitalUtils.findPerson(new ArrayList<>(patientList), pId);

        if (p != null) {
            System.out.print("                                                        Enter Doctor ID: ");
            String dId = scanner.nextLine();
            Person staff = HospitalUtils.findPerson(staffList, dId);
            
            if (staff instanceof Doctor) {
                System.out.print("                                                        Enter Diagnosis Details: ");
                String diagnosis = scanner.nextLine();
                System.out.print("                                                        ");
                ((Doctor) staff).diagnosePatient(p, diagnosis);
                
                // Pause logic on success
                System.out.println();
                System.out.print("                                                        Type 0 to go back: ");
                while (!scanner.nextLine().equals("0")) {
                    System.out.print("                                                        Type 0 to go back: ");
                }
            } else {
                System.out.println("                                                        Invalid Doctor ID or ID belongs to Nurse.");
                // Pause logic on error
                System.out.println();
                System.out.print("                                                        Type 0 to go back: ");
                while (!scanner.nextLine().equals("0"));
            }
        } else {
            System.out.println("                                                        Patient not found.");
            // Pause logic on error
            System.out.println();
            System.out.print("                                                        Type 0 to go back: ");
            while (!scanner.nextLine().equals("0"));
        }
    }

    private static void billingProcess() {
        
        System.out.print("                                                        Enter Patient ID: ");
        String id = scanner.nextLine();
        
        Patient p = (Patient) HospitalUtils.findPerson(new ArrayList<>(patientList), id);
        if (p != null) {
            HospitalBilling billing = new HospitalBilling();
            
            System.out.print("                                                        Enter Bill Amount: ");
            String amountStr = scanner.nextLine();
            
            int amount;
            try {
                amount = Integer.parseInt(amountStr);
            } catch (NumberFormatException e) {
                System.out.println("                                                        Invalid Amount! Billing Cancelled.");
                return;
            }
            
            System.out.print("                                                        Has Insurance? (true/false): ");
            String insuranceInput = scanner.nextLine();
            boolean hasInsurance = Boolean.parseBoolean(insuranceInput);
            
            if (hasInsurance) {
                System.out.print("                                                        Enter Insurance Coverage Amount: ");
                String coverageStr = scanner.nextLine();
                
                int coverage;
                try {
                    coverage = Integer.parseInt(coverageStr);
                } catch (NumberFormatException e) {
                    System.out.println("                                                        Invalid Coverage Amount! Billing Cancelled.");
                    return;
                }
                billing.generateBill(p, amount, coverage);
            } else {
                billing.generateBill(p, amount);
            }
            
            // Pause logic on success
            System.out.println();
            System.out.print("                                                        Type 0 to go back: ");
            while (!scanner.nextLine().equals("0")) {
                System.out.print("                                                        Type 0 to go back: ");
            }
        } else {
            System.out.println("                                                        Patient not found.");
            // Pause logic on error
            System.out.println();
            System.out.print("                                                        Type 0 to go back: ");
            while (!scanner.nextLine().equals("0"));
        }
    }
    
    //Option 8: Search Patients
    private static void searchSystem() {
        
        System.out.println();
        System.out.println("                                                                                        --- SEARCH PERSON ---");
        System.out.println("                                                        Search by: 1. ID  2. Name & Age");
        System.out.print("                                                        Enter Choice: ");
        String choiceStr = scanner.nextLine();
        int choice;
        try {
            choice = Integer.parseInt(choiceStr);
        } catch (NumberFormatException e) {
            System.out.println("                                                        Invalid Input!");
            return;
        }

        List<Person> allPeople = new ArrayList<>();
        allPeople.addAll(staffList);
        allPeople.addAll(patientList);

        Person result = null;
        if (choice == 1) {
            System.out.print("                                                        Enter ID: ");
            String id = scanner.nextLine();
            result = HospitalUtils.findPerson(allPeople, id);
        } else if (choice == 2) {
            System.out.print("                                                        Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("                                                        Enter Age: ");
            String ageStr = scanner.nextLine();
            
            int age;
            try {
                age = Integer.parseInt(ageStr);
            } catch (NumberFormatException e) {
                System.out.println("                                                        Invalid Age! Search Cancelled.");
                return;
            }
            result = HospitalUtils.findPerson(allPeople, name, age);
        }

        if (result != null) {
            System.out.println("                                                        Found: " + result);
        } else {
            System.out.println("                                                        Person not found.");
        }
        
        // Pause logic
        System.out.println();
        System.out.print("                                                        Type 0 to go back: ");
        while (!scanner.nextLine().equals("0")) {
            System.out.print("                                                        Type 0 to go back: ");
        }
    }
    
    // Option 9: View Departments
    private static void viewDepartments() {
        System.out.println();
        System.out.println("                                                                        --- DEPARTMENTS ---");
        for (Department d : departmentList) {
            System.out.println("                                                        " + d);
        }
        // Pause logic
        System.out.println();
        System.out.print("                                                        Type 0 to go back: ");
        while (!scanner.nextLine().equals("0")) {
            System.out.print("                                                        Type 0 to go back: ");
        }
    }
    
    // Option 10: Schedule Appointment
    private static void scheduleAppointment() {
        System.out.println();
        System.out.println("                                                                        --- SCHEDULE APPOINTMENT ---");
        
        System.out.print("                                                        Enter Patient ID: ");
        String pId = scanner.nextLine();
        Patient p = (Patient) HospitalUtils.findPerson(new ArrayList<>(patientList), pId);
        
        if (p == null) {
            System.out.println("                                                        Patient not found!");
            return;
        }
        
        System.out.print("                                                        Enter Doctor ID: ");
        String dId = scanner.nextLine();
        Person staff = HospitalUtils.findPerson(staffList, dId);
        
        if (!(staff instanceof Doctor)) {
            System.out.println("                                                        Invalid Doctor ID!");
            return;
        }
        
        System.out.print("                                                        Enter Date (DD-MM-YYYY): ");
        String date = scanner.nextLine();
        System.out.print("                                                        Enter Time (HH:MM): ");
        String time = scanner.nextLine();
        
        String apptId = String.format("APT%03d", appointmentList.size() + 1);
        Appointment appt = new Appointment(apptId, pId, dId, date, time);
        appointmentList.add(appt);
        
        System.out.println("                                                        Appointment Scheduled! ID: " + apptId);
        
        // Pause logic
        System.out.println();
        System.out.print("                                                        Type 0 to go back: ");
        while (!scanner.nextLine().equals("0")) {
            System.out.print("                                                        Type 0 to go back: ");
        }
    }
    
    // Option 11: View Appointments
    private static void viewAppointments() {
        System.out.println();
        System.out.println("                                                                        --- APPOINTMENTS ---");
        if (appointmentList.isEmpty()) {
            System.out.println("                                                        No appointments scheduled.");
        } else {
            for (Appointment a : appointmentList) {
                System.out.println("                                                        " + a);
            }
        }
        // Pause logic
        System.out.println();
        System.out.print("                                                        Type 0 to go back: ");
        while (!scanner.nextLine().equals("0")) {
            System.out.print("                                                        Type 0 to go back: ");
        }
    }
    
    // Option 12: Emergency Call & Ambulance
    private static void emergencyMenu() {
        System.out.println();
        System.out.println("                                                                        --- EMERGENCY SERVICES ---");
        System.out.println("                                                        1. Create Emergency Call");
        System.out.println("                                                        2. View Emergency Calls");
        System.out.println("                                                        3. Dispatch Ambulance");
        System.out.println("                                                        4. View Ambulances");
        System.out.print("                                                        Enter Choice: ");
        
        String choiceStr = scanner.nextLine();
        int choice;
        try {
            choice = Integer.parseInt(choiceStr);
        } catch (NumberFormatException e) {
            System.out.println("                                                        Invalid Input!");
            return;
        }
        
        switch (choice) {
            case 1:
                createEmergencyCall();
                break;
            case 2:
                viewEmergencyCalls();
                break;
            case 3:
                dispatchAmbulance();
                break;
            case 4:
                viewAmbulances();
                break;
            default:
                System.out.println("                                                        Invalid Choice!");
        }
    }
    
    private static void createEmergencyCall() {
        System.out.println();
        System.out.println("                                                                        --- CREATE EMERGENCY CALL ---");
        
        System.out.print("                                                        Caller Name: ");
        String caller = scanner.nextLine();
        System.out.print("                                                        Emergency Location: ");
        String location = scanner.nextLine();
        System.out.print("                                                        Emergency Type (Accident/Heart Attack/etc): ");
        String type = scanner.nextLine();
        
        String id = String.format("EC%03d", emergencyList.size() + 1);
        EmergencyCall call = new EmergencyCall(id, caller, location, type);
        emergencyList.add(call);
        
        System.out.println("                                                        Emergency Call Created! ID: " + id);
        
        // Pause logic
        System.out.println();
        System.out.print("                                                        Type 0 to go back: ");
        while (!scanner.nextLine().equals("0")) {
            System.out.print("                                                        Type 0 to go back: ");
        }
    }
    
    private static void viewEmergencyCalls() {
        System.out.println();
        System.out.println("                                                                        --- EMERGENCY CALLS ---");
        if (emergencyList.isEmpty()) {
            System.out.println("                                                        No emergency calls.");
        } else {
            for (EmergencyCall e : emergencyList) {
                System.out.println("                                                        " + e);
            }
        }
        // Pause logic
        System.out.println();
        System.out.print("                                                        Type 0 to go back: ");
        while (!scanner.nextLine().equals("0")) {
            System.out.print("                                                        Type 0 to go back: ");
        }
    }
    
    private static void dispatchAmbulance() {
        System.out.println();
        System.out.println("                                                                        --- DISPATCH AMBULANCE ---");
        
        System.out.print("                                                        Enter Emergency Call ID: ");
        String ecId = scanner.nextLine();
        
        EmergencyCall call = null;
        for (EmergencyCall e : emergencyList) {
            if (e.getId().equalsIgnoreCase(ecId)) {
                call = e;
                break;
            }
        }
        
        if (call == null) {
            System.out.println("                                                        Emergency call not found!");
            return;
        }
        
        System.out.print("                                                        Enter Ambulance ID: ");
        String ambId = scanner.nextLine();
        
        Ambulance amb = null;
        for (Ambulance a : ambulanceList) {
            if (a.getId().equalsIgnoreCase(ambId)) {
                amb = a;
                break;
            }
        }
        
        if (amb == null) {
            System.out.println("                                                        Ambulance not found!");
            return;
        }
        
        if (!amb.getStatus().equals("Available")) {
            System.out.println("                                                        Ambulance is not available!");
            return;
        }
        
        call.assignAmbulance(ambId);
        amb.setStatus("On Route");
        System.out.println("                                                        Ambulance Dispatched Successfully!");
        
        // Pause logic
        System.out.println();
        System.out.print("                                                        Type 0 to go back: ");
        while (!scanner.nextLine().equals("0")) {
            System.out.print("                                                        Type 0 to go back: ");
        }
    }
    
    private static void viewAmbulances() {
        System.out.println();
        System.out.println("                                                                        --- AMBULANCES ---");
        for (Ambulance a : ambulanceList) {
            System.out.println("                                                        " + a);
        }
        // Pause logic
        System.out.println();
        System.out.print("                                                        Type 0 to go back: ");
        while (!scanner.nextLine().equals("0")) {
            System.out.print("                                                        Type 0 to go back: ");
        }
    }
    
    // Option 13: Create Medical Record
    private static void createMedicalRecord() {
        System.out.println();
        System.out.println("                                                                        --- CREATE MEDICAL RECORD ---");
        
        System.out.print("                                                        Enter Patient ID: ");
        String pId = scanner.nextLine();
        Patient p = (Patient) HospitalUtils.findPerson(new ArrayList<>(patientList), pId);
        
        if (p == null) {
            System.out.println("                                                        Patient not found!");
            return;
        }
        
        System.out.print("                                                        Enter Doctor ID: ");
        String dId = scanner.nextLine();
        Person staff = HospitalUtils.findPerson(staffList, dId);
        
        if (!(staff instanceof Doctor)) {
            System.out.println("                                                        Invalid Doctor ID!");
            return;
        }
        
        System.out.print("                                                        Date (DD-MM-YYYY): ");
        String date = scanner.nextLine();
        
        String mrId = String.format("MR%03d", medicalRecordList.size() + 1);
        MedicalRecord record = new MedicalRecord(mrId, pId, dId, date);
        
        System.out.print("                                                        Symptoms: ");
        String symptoms = scanner.nextLine();
        System.out.print("                                                        Diagnosis: ");
        String diagnosis = scanner.nextLine();
        System.out.print("                                                        Prescription: ");
        String prescription = scanner.nextLine();
        
        record.setDetails(symptoms, diagnosis, prescription);
        medicalRecordList.add(record);
        
        System.out.println("                                                        Medical Record Created! ID: " + mrId);
        
        // Pause logic
        System.out.println();
        System.out.print("                                                        Type 0 to go back: ");
        while (!scanner.nextLine().equals("0")) {
            System.out.print("                                                        Type 0 to go back: ");
        }
    }
    
    // Option 14: View Medical Records
    private static void viewMedicalRecords() {
        System.out.println();
        System.out.println("                                                                        --- MEDICAL RECORDS ---");
        if (medicalRecordList.isEmpty()) {
            System.out.println("                                                        No medical records found.");
        } else {
            for (MedicalRecord mr : medicalRecordList) {
                System.out.println("                                                        " + mr);
            }
        }
        // Pause logic
        System.out.println();
        System.out.print("                                                        Type 0 to go back: ");
        while (!scanner.nextLine().equals("0")) {
            System.out.print("                                                        Type 0 to go back: ");
        }
    }
}

