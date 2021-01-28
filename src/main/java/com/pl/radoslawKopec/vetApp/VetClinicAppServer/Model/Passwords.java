package com.pl.radoslawKopec.vetApp.VetClinicAppServer.Model;

import javax.persistence.*;

@Entity
@Table(name = "PASSWORDS")
public class Passwords {

    @Id
    private int id;

    private String password;

    @OneToOne(mappedBy = "passwords", cascade = CascadeType.ALL)
    private Users users;


    public Passwords(){super();}

    public Passwords(int id, String password){
        this.id = id;
        this.password = password;

    }

    public Passwords(int id, String password, Users users){
        this.id = id;
        this.password = password;
        this.users = users;
    }

    @Override
    public String toString() {
        return "Passwords{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", users=" + users +
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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }


}
