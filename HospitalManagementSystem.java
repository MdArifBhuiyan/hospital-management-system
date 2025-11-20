import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract class acting as a base
abstract class Person {
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
    
    // Overriding toString for easier printing
    @Override
    public String toString() {
        return "[" + id + "] " + name + " (" + age + " yrs)";
    }
}

class Doctor extends Person {
    private String specialization;

    public Doctor(String id, String name, int age, String specialization) {
        super(id, name, age);
        this.specialization = specialization;
    }

    // Doctor specific implementation
    @Override
    public void performRole() {
        System.out.println("Doctor " + getName() + " (" + specialization + ") is seeing patients.");
    }

    // Overriding toString to show Doctor details in the list
    @Override
    public String toString() {
        return super.toString() + " - [Doctor: " + specialization + "]";
    }

    public void diagnosePatient(Patient p, String disease) {
        System.out.println(getName() + " has diagnosed " + p.getName() + " with " + disease);
        p.setDiagnosis(disease);
    }
}

class Nurse extends Person {
    private String shift;

    public Nurse(String id, String name, int age, String shift) {
        super(id, name, age);
        this.shift = shift;
    }

    @Override
    public void performRole() {
        System.out.println("Nurse " + getName() + " is on " + shift + " shift checking vitals.");
    }

    // Overriding toString to show Nurse details in the list
    @Override
    public String toString() {
        return super.toString() + " - [Nurse: " + shift + " Shift]";
    }
}

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

    @Override
    public void performRole() {
        System.out.println("Patient " + getName() + " is recovering from " + illness);
    }

    // Specific methods for Patient
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    
    public String getDiagnosis() { return diagnosis; }
    
    @Override
    public String toString() {
        return super.toString() + " - Complaint: " + illness + " | Diagnosis: " + diagnosis;
    }
}

class HospitalUtils {
    
    // Search by ID only
    public static Person findPerson(List<Person> people, String id) {
        for (Person p : people) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    // Search by Name and Age (Overloaded)
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
    
    // Standard Bill
    public void generateBill(Patient p, int amount) {
        System.out.println("\n\t\t\t\t\t\t\t    --- FINAL BILL ---");
        System.out.println("\t\t\t\t\t\t\tPatient: " + p.getName());
        System.out.println("\t\t\t\t\t\t\tTotal Amount: BDT " + amount);
    }

    // Discounted Bill (Insurance)
    public void generateBill(Patient p, int amount, int insuranceCoverage) {
        System.out.println("\n\t\t\t\t\t\t\t  --- FINAL BILL (INSURANCE APPLIED) ---");
        System.out.println("\t\t\t\t\t\t\tPatient: " + p.getName());
        System.out.println("\t\t\t\t\t\t\tBase Amount: BDT " + amount);
        System.out.println("\t\t\t\t\t\t\tInsurance Paid: -BDT " + insuranceCoverage);
        System.out.println("\t\t\t\t\t\t\tPatient Pays: BDT " + (amount - insuranceCoverage));
    }
}

public class HospitalManagementSystem {
    private static List<Person> staffList = new ArrayList<>();
    private static List<Patient> patientList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Pre-populating some data
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

        boolean running = true;
        while (running) {
            System.out.println("\n\n\n");
            System.out.println("\t\t\t\t\t\t\t------------------------------------------");
            System.out.println("\t\t\t\t\t\t\t---     HOSPITAL MANAGEMENT SYSTEM     ---");
            System.out.println("\t\t\t\t\t\t\t------------------------------------------");
            System.out.println("\t\t\t\t\t\t\t1. Register New Patient");
            System.out.println("\t\t\t\t\t\t\t2. View Staff Details");
            System.out.println("\t\t\t\t\t\t\t3. View Staff Activity");
            System.out.println("\t\t\t\t\t\t\t4. View Patient Activity");
            System.out.println("\t\t\t\t\t\t\t5. View All Patients");
            System.out.println("\t\t\t\t\t\t\t6. Doctor Diagnosis");
            System.out.println("\t\t\t\t\t\t\t7. Generate Bill");
            System.out.println("\t\t\t\t\t\t\t8. Search Person");
            System.out.println("\t\t\t\t\t\t\t9. Exit");
            System.out.println("\t\t\t\t\t\t\t------------------------------------------");
            System.out.print("\t\t\t\t\t\t\tEnter Choice: ");
            
            String input = scanner.nextLine();
            int choice = -1;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\t\t\t\t\t\t\tInvalid Input!");
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
                    running = false;
                    System.out.println("\t\t\t\t\t\t\tSystem Exiting...");
                    break;
                default:
                    System.out.println("\t\t\t\t\t\t\tInvalid Choice!");
            }
        }
    }

    private static void registerPatient() {
        System.out.println("\n\t\t\t\t\t\t\t                --- REGISTER PATIENT ---");
        
        System.out.print("\t\t\t\t\t\t\tEnter Name: ");
        String name = scanner.nextLine();

        System.out.print("\t\t\t\t\t\t\tEnter Age: ");
        String ageStr = scanner.nextLine();
        int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            System.out.println("\t\t\t\t\t\t\tInvalid Age! Registration Cancelled.");
            return;
        }

        System.out.print("\t\t\t\t\t\t\tEnter Illness: ");
        String illness = scanner.nextLine();
        
        String id = "P" + (patientList.size() + 101); // Auto-ID
        Patient newPatient = new Patient(id, name, age, illness);
        patientList.add(newPatient);
        System.out.println("\t\t\t\t\t\t\tPatient Registered Successfully! ID: " + id);

        // Pause to show success message
        System.out.print("\n\t\t\t\t\t\t\tType 0 to go back: ");
        while (!scanner.nextLine().equals("0")) {
            System.out.print("\t\t\t\t\t\t\tType 0 to go back: ");
        }
    }

    // Option 2: Shows Staff details
    private static void viewStaffDetails() {
        System.out.println("\n\t\t\t\t\t\t\t                --- STAFF DETAILS ---");
        for (Person p : staffList) {
            System.out.println("\t\t\t\t\t\t\t" + p);
        }
        // Pause logic
        System.out.print("\n\t\t\t\t\t\t\tType 0 to go back: ");
        while (!scanner.nextLine().equals("0")) {
            System.out.print("\t\t\t\t\t\t\tType 0 to go back: ");
        }
    }

    // Option 3: shows staff specific activity
    private static void viewStaffActivity() {
        System.out.println("\n\t\t\t\t\t\t\t                 --- STAFF ACTIVITY ---");
        for (Person p : staffList) {
            System.out.print("\t\t\t\t\t\t\t");
            p.performRole(); 
        }
        // Pause logic
        System.out.print("\n\t\t\t\t\t\t\tType 0 to go back: ");
        while (!scanner.nextLine().equals("0")) {
            System.out.print("\t\t\t\t\t\t\tType 0 to go back: ");
        }
    }

    // Option 4: Shows Patient specific activity
    private static void viewPatientActivity() {
        System.out.println("\n\t\t\t\t\t\t\t               --- PATIENT ACTIVITY ---");
        for (Patient p : patientList) {
            System.out.print("\t\t\t\t\t\t\t");
            p.performRole(); 
        }
        // Pause logic
        System.out.print("\n\t\t\t\t\t\t\tType 0 to go back: ");
        while (!scanner.nextLine().equals("0")) {
            System.out.print("\t\t\t\t\t\t\tType 0 to go back: ");
        }
    }

    private static void viewPatients() {
        System.out.println("\n\t\t\t\t\t\t\t                        --- ADMITTED PATIENTS ---");
        for (Patient p : patientList) {
            System.out.println("\t\t\t\t\t\t\t" + p);
        }
        // Pause logic
        System.out.print("\n\t\t\t\t\t\t\tType 0 to go back: ");
        while (!scanner.nextLine().equals("0")) {
            System.out.print("\t\t\t\t\t\t\tType 0 to go back: ");
        }
    }

    private static void performDiagnosis() {
        System.out.println("\n\t\t\t\t\t\t\t                --- DOCTOR DIAGNOSIS ---");
        
        System.out.print("\t\t\t\t\t\t\tEnter Patient ID: ");
        String pId = scanner.nextLine();

        Patient p = (Patient) HospitalUtils.findPerson(new ArrayList<>(patientList), pId);

        if (p != null) {
            System.out.print("\t\t\t\t\t\t\tEnter Doctor ID: ");
            String dId = scanner.nextLine();

            Person staff = HospitalUtils.findPerson(staffList, dId);
            
            if (staff instanceof Doctor) {
                System.out.print("\t\t\t\t\t\t\tEnter Diagnosis Details: ");
                String diagnosis = scanner.nextLine();

                System.out.print("\t\t\t\t\t\t\t");
                ((Doctor) staff).diagnosePatient(p, diagnosis);
                
                // Pause logic on success
                System.out.print("\n\t\t\t\t\t\t\tType 0 to go back: ");
                while (!scanner.nextLine().equals("0")) {
                    System.out.print("\t\t\t\t\t\t\tType 0 to go back: ");
                }

            } else {
                System.out.println("\t\t\t\t\t\t\tInvalid Doctor ID or ID belongs to Nurse.");
                // Pause logic on error
                System.out.print("\n\t\t\t\t\t\t\tType 0 to go back: ");
                while (!scanner.nextLine().equals("0"));
            }
        } else {
            System.out.println("\t\t\t\t\t\t\tPatient not found.");
            // Pause logic on error
            System.out.print("\n\t\t\t\t\t\t\tType 0 to go back: ");
            while (!scanner.nextLine().equals("0"));
        }
    }

    private static void billingProcess() {
        
        System.out.print("\t\t\t\t\t\t\tEnter Patient ID: ");
        String id = scanner.nextLine();
        
        Patient p = (Patient) HospitalUtils.findPerson(new ArrayList<>(patientList), id);

        if (p != null) {
            HospitalBilling billing = new HospitalBilling();
            
            System.out.print("\t\t\t\t\t\t\tEnter Bill Amount: ");
            String amountStr = scanner.nextLine();
            
            int amount;
            try {
                amount = Integer.parseInt(amountStr);
            } catch (NumberFormatException e) {
                System.out.println("\t\t\t\t\t\t\tInvalid Amount! Billing Cancelled.");
                return;
            }
            
            System.out.print("\t\t\t\t\t\t\tHas Insurance? (true/false): ");
            String insuranceInput = scanner.nextLine();

            boolean hasInsurance = Boolean.parseBoolean(insuranceInput);
            
            if (hasInsurance) {
                System.out.print("\t\t\t\t\t\t\tEnter Insurance Coverage Amount: ");
                String coverageStr = scanner.nextLine();
                
                int coverage;
                try {
                    coverage = Integer.parseInt(coverageStr);
                } catch (NumberFormatException e) {
                    System.out.println("\t\t\t\t\t\t\tInvalid Coverage Amount! Billing Cancelled.");
                    return;
                }
                billing.generateBill(p, amount, coverage);
            } else {
                billing.generateBill(p, amount);
            }
            
            // Pause logic on success
            System.out.print("\n\t\t\t\t\t\t\tType 0 to go back: ");
            while (!scanner.nextLine().equals("0")) {
                System.out.print("\t\t\t\t\t\t\tType 0 to go back: ");
            }

        } else {
            System.out.println("\t\t\t\t\t\t\tPatient not found.");
            // Pause logic on error
            System.out.print("\n\t\t\t\t\t\t\tType 0 to go back: ");
            while (!scanner.nextLine().equals("0"));
        }
    }

    private static void searchSystem() {
        
        System.out.println("\n\t\t\t\t\t\t\t                        --- SEARCH PERSON ---");
        System.out.println("\t\t\t\t\t\t\tSearch by: 1. ID  2. Name & Age");
        System.out.print("\t\t\t\t\t\t\tEnter Choice: ");
        String choiceStr = scanner.nextLine();

        int choice;
        try {
            choice = Integer.parseInt(choiceStr);
        } catch (NumberFormatException e) {
            System.out.println("\t\t\t\t\t\t\tInvalid Input!");
            return;
        }

        List<Person> allPeople = new ArrayList<>();
        allPeople.addAll(staffList);
        allPeople.addAll(patientList);

        Person result = null;

        if (choice == 1) {
            System.out.print("\t\t\t\t\t\t\tEnter ID: ");
            String id = scanner.nextLine();
            result = HospitalUtils.findPerson(allPeople, id);
        } else if (choice == 2) {
            System.out.print("\t\t\t\t\t\t\tEnter Name: ");
            String name = scanner.nextLine();

            System.out.print("\t\t\t\t\t\t\tEnter Age: ");
            String ageStr = scanner.nextLine();
            
            int age;
            try {
                age = Integer.parseInt(ageStr);
            } catch (NumberFormatException e) {
                System.out.println("\t\t\t\t\t\t\tInvalid Age! Search Cancelled.");
                return;
            }
            result = HospitalUtils.findPerson(allPeople, name, age);
        }

        if (result != null) {
            System.out.println("\t\t\t\t\t\t\tFound: " + result);
        } else {
            System.out.println("\t\t\t\t\t\t\tPerson not found.");
        }
        
        // Pause logic
        System.out.print("\n\t\t\t\t\t\t\tType 0 to go back: ");
        while (!scanner.nextLine().equals("0")) {
            System.out.print("\t\t\t\t\t\t\tType 0 to go back: ");
        }
    }
}