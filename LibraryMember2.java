public class LibraryMember {
    private static int counter = 100;
    private static int enrolledCount = 0;
    public final String memberNumber;
    
    private int borrowLimit;
    private int booksBorrowed = 0;

    // Assigns the final ID and increments static counters
    public LibraryMember(int borrowLimit) {
        this.borrowLimit = borrowLimit;
        counter++;
        enrolledCount++;
        this.memberNumber = "LIB-" + counter;
    }

    public void borrowBook() {
        booksBorrowed++;
    }

    // Overloaded method reusing the no-arg logic
    public void borrowBook(String genre) {
        this.borrowBook(); 
    }

    public static int getMembersEnrolled() {
        return enrolledCount;
    }

    // Manual format checking without Regex
    public static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) return false;
        if (code.charAt(0) != 'R') return false;
        if (!Character.isDigit(code.charAt(1))) return false;
        if (!Character.isDigit(code.charAt(2))) return false;
        if (!Character.isUpperCase(code.charAt(3))) return false;
        
        return true;
    }

    // Instance checks with null-safety
    public static String processNightlyAudit(LibraryMember[] members) {
        int processed = 0, nullSkipped = 0, faculty = 0, regular = 0;
        
        for (LibraryMember m : members) {
            if (m == null) {
                nullSkipped++;
            } else {
                processed++;
                if (m instanceof FacultyMember) {
                    faculty++;
                } else {
                    regular++;
                }
            }
        }
        return processed + " processed | " + nullSkipped + " null skipped | " + 
               faculty + " faculty | " + regular + " regular";
    }
}