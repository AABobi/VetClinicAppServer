package pl.veterinaryClinicApplicationServer.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
public class Users extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @OneToOne(cascade = CascadeType.ALL)
    private Passwords passwords;

    /*@OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private History history;*/


    public Users() {

    }

    public Users(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public Users(int id,String name, String lastname, String email) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }
    public Users(String nickname, String name, String lastname) {
        this.nickname = nickname;
        this.name = name;
        this.lastname = lastname;
    }

    public Users(String nickname, String name, String lastname, String email) {
        this.nickname = nickname;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }

    public Users(String f) {
        this.nickname = f;
        this.name = f;
        this.lastname = f;
    }

    public Users(String nickname, String name, String lastname, String email, Passwords passwords) {
        this.nickname = nickname;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.passwords = passwords;
    }
    public Users(String email, String name, String lastname, String nickname,String permissions, Passwords passwords) {
        this.nickname = nickname;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.permissions = permissions;
        this.passwords = passwords;
    }
    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", passwords=" + passwords +
               // ", history=" + history +
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
