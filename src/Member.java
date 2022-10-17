import java.time.LocalDate;

public class Member {

    private final String idNumber;
    private final String name;
    private final LocalDate date;

    public Member(String idNumber, String name, LocalDate date) {
        this.idNumber = idNumber;
        this.name = name;
        this.date = date;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }
}

