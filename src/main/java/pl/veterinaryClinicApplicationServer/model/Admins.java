package pl.veterinaryClinicApplicationServer.model;

import javax.persistence.*;

@Entity
public class Admins extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    private Passwords passwords;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Passwords getPasswords() {
        return passwords;
    }

    public void setPasswords(Passwords passwords) {
        this.passwords = passwords;
    }

    public Admins(){

    }

    public Admins(int id, String nickname, String name, String lastname){
        this.id = id;
        this.nickname = nickname;
        this.name = name;
        this.lastname = lastname;
    }
    @Override
    public String toString() {
        return "Admins{" +
                "id=" + id +
                ", passwords=" + passwords +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", lastname='" + lastname + '\'' +
                ", permissions='" + permissions + '\'' +
                '}';
    }
}
