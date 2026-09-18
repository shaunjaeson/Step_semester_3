public class CollegeSetup {
    static class SrmStudent {
        static String collegeName;
        static String academicYear;

        // Static block sets info exactly once[cite: 1]
        static {
            collegeName = "SRM";
            academicYear = "2026";
            System.out.println("College info loaded");
        }

        String name;

        public SrmStudent(String name) {
            this.name = name;
            System.out.println("Student record created: " + name);
        }
    }

    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya", "Anitha"};
        
        for (String name : names) {
            new SrmStudent(name);
        }
    }
}