public class BatchPayments {
    static class FeeAccount {}
    static class HostelFeeAccount extends FeeAccount {}

    static class FinanceProcessor {
        int hostelCount = 0;
        int dayScholarCount = 0;

        void processPayment(FeeAccount account, double amount) {
            if (account instanceof HostelFeeAccount) {
                System.out.println("Paid in two installments (hostel account)");
                hostelCount++;
            } else if (account instanceof FeeAccount) {
                System.out.println("Paid in one go (day-scholar account)");
                dayScholarCount++;
            }
        }
        
        void printCounters() {
            System.out.println("Hostel accounts processed: " + hostelCount + " | Day-scholar accounts processed: " + dayScholarCount);
        }
    }

    public static void main(String[] args) {
        FeeAccount[] accounts = {
            new HostelFeeAccount(), 
            new HostelFeeAccount(), 
            new FeeAccount(), 
            new FeeAccount()
        };
        
        FinanceProcessor processor = new FinanceProcessor();
        double amount = 60000;

        for (FeeAccount acc : accounts) {
            processor.processPayment(acc, amount);
        }
        
        processor.printCounters();
    }
}