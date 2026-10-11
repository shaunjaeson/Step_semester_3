import java.util.*;

abstract class Seat {
    protected String seatId;

    public Seat(String seatId) {
        this.seatId = seatId;
    }

    public String getSeatId() {
        return seatId;
    }

    public abstract double getPrice();
}

class RegularSeat extends Seat {
    public RegularSeat(String id) {
        super(id);
    }

    public double getPrice() {
        return 150;
    }
}

class PremiumSeat extends Seat {
    public PremiumSeat(String id) {
        super(id);
    }

    public double getPrice() {
        return 250;
    }
}

class ReclinerSeat extends Seat {
    public ReclinerSeat(String id) {
        super(id);
    }

    public double getPrice() {
        return 400;
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Show {
    private String time;
    private Set<String> bookedSeats = new HashSet<>();
    private boolean started = false;

    public Show(String time) {
        this.time = time;
    }

    public boolean isAvailable(String seatId) {
        return !bookedSeats.contains(seatId);
    }

    public boolean bookSeats(List<Seat> seats) {
        for (Seat seat : seats) {
            if (!isAvailable(seat.getSeatId())) {
                System.out.println("Seat " + seat.getSeatId()
                    + " is already booked for this show.");
                return false;
            }
        }

        for (Seat seat : seats) {
            bookedSeats.add(seat.getSeatId());
        }
        return true;
    }

    public void releaseSeats(List<Seat> seats) {
        for (Seat seat : seats) {
            bookedSeats.remove(seat.getSeatId());
        }
    }

    public boolean hasStarted() {
        return started;
    }

    public void startShow() {
        started = true;
    }
}

class Booking {
    private Customer customer;
    private Show show;
    private List<Seat> seats;
    private boolean cancelled = false;

    public Booking(Customer customer, Show show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = new ArrayList<>(seats);
    }

    public void confirm() {
        if (cancelled) {
            return;
        }

        if (seats.isEmpty() || seats.size() > 6) {
            System.out.println("Booking must contain 1 to 6 seats.");
            return;
        }

        if (!show.bookSeats(seats)) {
            return;
        }

        double total = 0;
        List<String> ids = new ArrayList<>();

        for (Seat seat : seats) {
            total += seat.getPrice();
            ids.add(seat.getSeatId());
        }

        System.out.println("Booking confirmed for " +
            customer.getName() + ": " + String.join(", ", ids) + ".");

        System.out.printf(Locale.US, "Total: ₹%.2f.%n", total);
    }

    public void cancel() {
        if (cancelled) {
            System.out.println("Booking is already cancelled.");
            return;
        }

        if (show.hasStarted()) {
            System.out.println("Cannot cancel after the show starts.");
            return;
        }

        show.releaseSeats(seats);
        cancelled = true;

        List<String> ids = new ArrayList<>();
        for (Seat seat : seats) {
            ids.add(seat.getSeatId());
        }

        System.out.println(customer.getName() +
            "'s booking cancelled. Seats " +
            String.join(", ", ids) + " released.");
    }
}

public class The_Campus_Premiere_Ticket_Counter {
    public static void main(String[] args) {
        Show show = new Show("7 PM");

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Booking b1 = new Booking(asha, show, Arrays.asList(
            new RegularSeat("A1"),
            new RegularSeat("A2"),
            new PremiumSeat("F5")
        ));
        b1.confirm();

        Booking b2 = new Booking(ravi, show, Arrays.asList(
            new RegularSeat("A2")
        ));
        b2.confirm();

        Booking b3 = new Booking(ravi, show, Arrays.asList(
            new ReclinerSeat("R1")
        ));
        b3.confirm();

        b1.cancel();

        Booking b4 = new Booking(neha, show, Arrays.asList(
            new RegularSeat("A2")
        ));
        b4.confirm();
    }
}