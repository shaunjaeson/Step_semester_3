public class LibraryMember {
    // ... (previous fields omitted for brevity)
    private int[] fineHistory = new int[10];
    private int fineCount = 0;

    protected void chargeFine(int amount) {
        if (fineCount < 10) {
            fineHistory[fineCount++] = amount;
        }
    }

    public int[] getFineHistory() {
        // Returns a defensive copy to prevent outside tampering
        return java.util.Arrays.copyOf(fineHistory, fineCount);
    }

    public int getTotalFine() {
        int total = 0;
        for (int i = 0; i < fineCount; i++) {
            total += fineHistory[i];
        }
        return total;
    }
}

class StudentMember extends LibraryMember {
    // ... (previous fields omitted for brevity)

    @Override
    protected void chargeFine(int amount) {
        // Halves the fine and delegates array-recording to the parent
        super.chargeFine(amount / 2); 
    }
}