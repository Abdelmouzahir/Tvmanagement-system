import java.io.Serializable;

public class Subscriber implements Serializable {
    private String fName;
    private String lName;
    private String city;
    private int Phone;

    public Subscriber(String fName, String lName, String city, int phone) {
        this.fName = fName;
        this.lName = lName;
        this.city = city;
        Phone = phone;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getCity() {
        return city;
    }

    public int getPhone() {
        return Phone;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhone(int phone) {
        Phone = phone;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", city='" + city + '\'' +
                ", Phone=" + Phone +
                '}';
    }
}
