class Course {
    String code;
    String title;
    int credits;
    int labCredits;

    public Course(String code, String title, int credits, int labCredits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.labCredits = labCredits;
    }

    public Course(String code, String title, int credits) {
        this(code, title, credits, 0);
    }

    public int totalCredits() {
        return credits + labCredits;
    }

    public void printInfo() {
        System.out.println(code + " total credits: " + totalCredits());
    }
}

public class MainM3 {
    public static void main(String[] args) {
        Course dsa = new Course("21CSC201J", "Data Structures", 4);
        dsa.printInfo();

        Course dsaLab = new Course("21CSC205L", "DSA Lab", 3, 1);
        dsaLab.printInfo();
    }
}