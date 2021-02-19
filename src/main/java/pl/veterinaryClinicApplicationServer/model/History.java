package pl.veterinaryClinicApplicationServer.model;

import javax.persistence.*;

@Entity
@Table(name = "history")
public class History {

    @Id
    private int id;

    private String name;

    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    private Users users;

    @OneToOne(cascade = CascadeType.ALL)
    private Doctors doctors;

    public History(int id) {
        this.id = id;
    }

    public History() {

    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", users=" + users +
                ", doctors=" + doctors +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Doctors getDoctors() {
        return doctors;
    }

    public void setDoctors(Doctors doctors) {
        this.doctors = doctors;
    }
}
