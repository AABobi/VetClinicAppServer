package pl.veterinaryClinicApplicationServer.model;

import javax.persistence.*;

@Entity
@Table(name = "admins")
public class Admins extends User{

    @Id
    private int admin_id;

    @OneToOne(cascade = CascadeType.ALL)
    private Passwords passwords;

    @Override
    public String toString() {
        return "Admins{" +
                "admin_id=" + admin_id +
                ", passwords=" + passwords +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", lastname='" + lastname + '\'' +
                ", permissions='" + permissions + '\'' +
                '}';
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public Passwords getPasswords() {
        return passwords;
    }

    public void setPasswords(Passwords passwords) {
        this.passwords = passwords;
    }
}
