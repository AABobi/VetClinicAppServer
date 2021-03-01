package pl.veterinaryClinicApplicationServer.model;

import javax.persistence.*;

@Entity
@Table(name = "PASSWORDS")
public class Passwords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String password;

   /* @OneToOne(mappedBy = "passwords", cascade = CascadeType.ALL)
    private Users users;*/

   /* @OneToOne(mappedBy = "passwords", cascade = CascadeType.ALL)
    private Doctors doctors;

    @OneToOne(mappedBy = "passwords", cascade = CascadeType.ALL)
    private Admins admins;*/

    public Passwords(){}
    public Passwords(String password){
        this.password = password;
    }
    public Passwords(int id,String password){
        this.id = id;
        this.password = password;
    }
    @Override
    public String toString() {
        return "Passwords{" +
                "id=" + id +
                ", password='" + password + '\'' +
               // ", users=" + users +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   /* public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }*/
}
