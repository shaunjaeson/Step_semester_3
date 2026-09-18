public class PayrollBatch {
    static class Employee {
        String id;
        double salary;

        public Employee(String id, double salary) {
            this.id = id;
            this.salary = salary;
        }

        // Method resolves naming clash using this
        void raiseSalary(double salary) {
            this.salary += salary;
        }

        void printFinalSalary() {
            System.out.println(id + " | Final Salary: Rs " + salary);
        }
    }

    public static void main(String[] args) {
        Employee[] team = {
            new Employee("E-101", 40000),
            new Employee("E-102", 55000),
            new Employee("E-103", 62000),
            new Employee("E-104", 48000)
        };

        for (Employee emp : team) {
            emp.raiseSalary(5000); // 5000 applied to each
            emp.printFinalSalary();
        }
    }
}