import java.util.*;

interface NotificationChannel {
    void send(String studentName, String message);
}

class EmailChannel implements NotificationChannel {
    public void send(String studentName, String message) {
        System.out.println("[Email → " + studentName + "] " + message);
    }
}

class SmsChannel implements NotificationChannel {
    public void send(String studentName, String message) {
        System.out.println("[SMS → " + studentName + "] " + message);
    }
}

class AppChannel implements NotificationChannel {
    public void send(String studentName, String message) {
        System.out.println("[App → " + studentName + "] " + message);
    }
}

class Student {
    private String name;
    private String department;
    private List<NotificationChannel> channels = new ArrayList<>();

    public Student(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public void addChannel(NotificationChannel channel) {
        channels.add(channel);
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public List<NotificationChannel> getChannels() {
        return channels;
    }
}

class Notice {
    private String title;
    private Set<String> departments;

    public Notice(String title, Set<String> departments) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Notice title is required.");
        }

        if (departments == null || departments.isEmpty()) {
            throw new IllegalArgumentException(
                "At least one target department is required.");
        }

        this.title = title;
        this.departments = new HashSet<>(departments);
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getDepartments() {
        return departments;
    }
}

class NoticeBoard {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void postNotice(Notice notice) {
        System.out.println("Notice '" + notice.getTitle()
            + "' posted to " +
            String.join(", ", notice.getDepartments()) + ".");

        for (Student student : students) {
            if (notice.getDepartments().contains(
                    student.getDepartment())) {

                for (NotificationChannel channel :
                        student.getChannels()) {
                    channel.send(student.getName(), notice.getTitle());
                }
            }
        }
    }

    public void tryPostNotice(String title, Set<String> departments) {
        try {
            Notice notice = new Notice(title, departments);
            postNotice(notice);
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot post notice: " + e.getMessage());
        }
    }
}

public class The_Campus_Notice_Broadcaster {
    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();

        Student asha = new Student("Asha", "CSE");
        asha.addChannel(new EmailChannel());
        asha.addChannel(new AppChannel());

        Student ravi = new Student("Ravi", "ECE");
        ravi.addChannel(new SmsChannel());

        board.addStudent(asha);
        board.addStudent(ravi);

        board.tryPostNotice(
            "Lab Closed Tomorrow",
            new LinkedHashSet<>(Arrays.asList("CSE"))
        );

        board.tryPostNotice(
            "Fee Deadline Extended",
            new LinkedHashSet<>(Arrays.asList("CSE", "ECE"))
        );

        board.tryPostNotice(
            "Sports Day",
            new LinkedHashSet<String>()
        );
    }
}