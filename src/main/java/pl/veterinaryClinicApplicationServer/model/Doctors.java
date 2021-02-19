package pl.veterinaryClinicApplicationServer.model;

import javax.persistence.*;

@Entity
@Table(name = "doctors")
public class Doctors extends User{

    @Id
    private int doctors_id;

    @OneToOne(mappedBy = "doctors", cascade = CascadeType.ALL)
    private DateOfTheVisit dateOfTheVisit;

    @OneToOne(cascade = CascadeType.ALL)
    private Passwords passwords;

    @OneToOne(mappedBy = "doctors", cascade = CascadeType.ALL)
    private History history;

    public Doctors(){

    };

    @Override
    public String toString() {
        return "Doctors{" +
                "doctors_id=" + doctors_id +
                ", dateOfTheVisit=" + dateOfTheVisit +
                ", passwords=" + passwords +
                ", history=" + history +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", lastname='" + lastname + '\'' +
                ", permissions='" + permissions + '\'' +
                '}';
    }

    public int getDoctors_id() {
        return doctors_id;
    }

    public void setDoctors_id(int doctors_id) {
        this.doctors_id = doctors_id;
    }

    public DateOfTheVisit getDateOfTheVisit() {
        return dateOfTheVisit;
    }

    public void setDateOfTheVisit(DateOfTheVisit dateOfTheVisit) {
        this.dateOfTheVisit = dateOfTheVisit;
    }

    public Passwords getPasswords() {
        return passwords;
    }

    public void setPasswords(Passwords passwords) {
        this.passwords = passwords;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }
}
