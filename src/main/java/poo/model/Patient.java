package poo.model;

public class Patient {

    private String name;
    private String lastName;
    private long id;
    private int age;

    private Appointment appointment;

    public Patient() {
    }

    public Patient(String name, String lastName, long id, int age) {
        this.name = name;
        this.lastName = lastName;
        this.id = id;
        this.age = age;
    }

    public Patient(String name, String lastName, long id, int age, Appointment date) {
        this.name = name;
        this.lastName = lastName;
        this.id = id;
        this.age = age;
        this.appointment = date;
    }

    public String getName() {
        return name+" "+lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Appointment getDate() {
        return appointment;
    }

    public void setDate(Appointment date) {
        this.appointment = date;
    }
}
