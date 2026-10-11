import java.util.Locale;

abstract class WashCycle {
    public abstract int getDuration();
    public abstract double getCharge();
    public abstract String getType();
}

class QuickWash extends WashCycle {
    public int getDuration() { return 30; }
    public double getCharge() { return 20.0; }
    public String getType() { return "Quick"; }
}

class NormalWash extends WashCycle {
    public int getDuration() { return 45; }
    public double getCharge() { return 30.0; }
    public String getType() { return "Normal"; }
}

class HeavyWash extends WashCycle {
    public int getDuration() { return 60; }
    public double getCharge() { return 45.0; }
    public String getType() { return "Heavy"; }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class WashingMachine {
    private String id;
    private WashBooking currentBooking;

    public WashingMachine(String id) {
        this.id = id;
    }

    public boolean isFree() {
        return currentBooking == null;
    }

    public void startWash(Student student, WashCycle wash) {
        if (!isFree()) {
            System.out.println("Machine " + id + " is currently busy.");
            return;
        }

        currentBooking = new WashBooking(student, this, wash);

        System.out.printf(
            Locale.US,
            "%s wash started on %s for %s (%d min). Charge: ₹%.2f.%n",
            wash.getType(), id, student.getName(),
            wash.getDuration(), wash.getCharge()
        );
    }

    public void completeWash() {
        if (isFree()) {
            System.out.println("Machine " + id + " has no active wash.");
            return;
        }

        System.out.println(id + " cycle completed.");
        currentBooking = null;
        System.out.println(id + " is now free.");
    }

    public String getId() {
        return id;
    }
}

class WashBooking {
    private Student student;
    private WashingMachine machine;
    private WashCycle wash;

    public WashBooking(Student student, WashingMachine machine,
                       WashCycle wash) {
        this.student = student;
        this.machine = machine;
        this.wash = wash;
    }
}

public class The_Hostel_Laundry_Queue {
    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        m1.startWash(asha, new QuickWash());
        m1.startWash(ravi, new HeavyWash());
        m2.startWash(ravi, new HeavyWash());

        m1.completeWash();

        m1.startWash(neha, new NormalWash());
    }
}