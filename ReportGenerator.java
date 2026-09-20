public class ReportGenerator {
    public static String batchPrint(LibraryMember[] members) {
        StringBuilder sb = new StringBuilder();
        
        for (LibraryMember m : members) {
            sb.append(m.displayInfo());
            
            // Check first, then cast safely inside the branch
            if (m instanceof StudentMember) {
                StudentMember sm = (StudentMember) m; 
                sb.append(" [Course via downcast: ").append(sm.getCourse()).append("]");
            }
            sb.append(" | ");
        }
        
        return sb.toString();
    }
}