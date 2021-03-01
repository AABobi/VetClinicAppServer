package pl.veterinaryClinicApplicationServer.model;

import javax.persistence.*;

@Entity
public class Doctors extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

  /*  @OneToOne(mappedBy = "doctors", cascade = CascadeType.ALL)
    private DateOfTheVisit dateOfTheVisit;*/

    @OneToOne(cascade = CascadeType.ALL)
    private Passwords passwords;

    /*@OneToOne(mappedBy = "doctors", cascade = CascadeType.ALL)
    private History history;*/

    public Doctors(){

    };

    @Override
    public String toString() {
        return "Doctors{" +
                "id=" + id +
               // ", dateOfTheVisit=" + dateOfTheVisit +
                ", passwords=" + passwords +
                //", history=" + history +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", lastname='" + lastname + '\'' +
                ", permissions='" + permissions + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*public DateOfTheVisit getDateOfTheVisit() {
        return dateOfTheVisit;
    }

    public void setDateOfTheVisit(DateOfTheVisit dateOfTheVisit) {
        this.dateOfTheVisit = dateOfTheVisit;
    }*/

    public Passwords getPasswords() {
        return passwords;
    }

    public void setPasswords(Passwords passwords) {
        this.passwords = passwords;
    }

   /*public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }*/


}
