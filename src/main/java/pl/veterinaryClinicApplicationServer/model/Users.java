package pl.veterinaryClinicApplicationServer.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "USERS" )
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    private String nickname;

    private String name;

    private String email;

    private String lastname;

    private int permissions;

    @OneToOne(cascade = CascadeType.ALL)
    private Passwords passwords;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private History history;


    public Users() {

    }

    public Users(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public Users( String nickname,String name, String lastname) {
        this.nickname = nickname;
        this.name = name;
        this.lastname = lastname;
    }

    public Users( String nickname,String name, String lastname, String email) {
        this.nickname = nickname;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }

    public Users(String f){
        this.nickname = f;
        this.name = f;
        this.lastname = f;
    }
    public Users( String nickname, String name, String lastname,String email, Passwords passwords) {
        this.nickname = nickname;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.passwords = passwords;
    }


    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", lastname='" + lastname + '\'' +
                ", permissions='" + permissions + '\'' +
                ", passwords=" + passwords +
                ", history=" + history +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getPermissions() {
        return permissions;
    }

    public void setPermissions(int permissions) {
        this.permissions = permissions;
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
