import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Assignment {
    protected String title;
    protected LocalDate dueDate;
    protected int maxMarks;

    public Assignment(String title, LocalDate dueDate, int maxMarks) {
        this.title = title;
        this.dueDate = dueDate;
        this.maxMarks = maxMarks;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public long getLateDays(LocalDate submissionDate) {
        return Math.max(0,
            ChronoUnit.DAYS.between(dueDate, submissionDate));
    }

    public abstract double applyPenalty(double marks, long lateDays);
}

class CodingAssignment extends Assignment {
    public CodingAssignment(String title, LocalDate dueDate, int maxMarks) {
        super(title, dueDate, maxMarks);
    }

    public double applyPenalty(double marks, long lateDays) {
        return marks * Math.max(0, 1.0 - 0.10 * lateDays);
    }
}

class WrittenAssignment extends Assignment {
    public WrittenAssignment(String title, LocalDate dueDate, int maxMarks) {
        super(title, dueDate, maxMarks);
    }

    public double applyPenalty(double marks, long lateDays) {
        return marks * Math.max(0, 1.0 - 0.20 * lateDays);
    }
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

class Submission {
    private Student student;
    private Assignment assignment;
    private LocalDate submissionDate;
    private String status = "Submitted";
    private double finalMarks;

    public Submission(Student student, Assignment assignment,
                      LocalDate submissionDate) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDate = submissionDate;
    }

    public void grade(double marks) {
        if (!status.equals("Submitted")) {
            System.out.println("Cannot grade: submission is already graded.");
            return;
        }

        if (marks < 0 || marks > assignment.getMaxMarks()) {
            System.out.println("Invalid marks.");
            return;
        }

        long lateDays = assignment.getLateDays(submissionDate);
        finalMarks = assignment.applyPenalty(marks, lateDays);
        status = "Graded";

        if (lateDays == 0) {
            System.out.printf(
                "%s graded: %.0f/%d.%n",
                student.getName(), finalMarks, assignment.getMaxMarks()
            );
        } else {
            double penalty = Math.min(100,
                lateDays * (assignment instanceof CodingAssignment ? 10 : 20));

            System.out.printf(
                "%s graded: %.0f/%d after %.0f%% late penalty.%n",
                student.getName(), finalMarks,
                assignment.getMaxMarks(), penalty
            );
        }

        System.out.println("Status: " + status + ".");
    }

    public void resubmit(LocalDate newDate) {
        if (status.equals("Graded")) {
            System.out.println("Cannot resubmit: '" +
                assignment.getTitle() + "' has already been graded.");
        } else {
            submissionDate = newDate;
            System.out.println("Submission updated.");
        }
    }

    public String getStatus() {
        return status;
    }
}

public class The_Assignment_Submission_Portal {
    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding = new CodingAssignment(
            "Linked List Lab", LocalDate.of(2026, 3, 10), 50);

        Assignment written = new WrittenAssignment(
            "Design Essay", LocalDate.of(2026, 3, 12), 50);

        Submission s1 = new Submission(
            asha, coding, LocalDate.of(2026, 3, 10));

        Submission s2 = new Submission(
            ravi, written, LocalDate.of(2026, 3, 14));

        System.out.println(
            "Asha's submission for 'Linked List Lab' received (on time). Status: Submitted.");

        System.out.println(
            "Ravi's submission for 'Design Essay' received (2 days late). Status: Submitted.");

        s1.grade(45);
        s2.grade(40);

        s1.resubmit(LocalDate.of(2026, 3, 11));
    }
}