package com.pl.radoslawKopec.vetApp.VetClinicAppServer.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    @Id
    private int id;
    private String name;
    @Column(name = "last_name")
    private String lastname;

    public User(){};

    public User( String name, String lastname){
        this.name = name;
        this.lastname = lastname;
    };


    public User(int id, String name, String lastname){
        this.id = id;
        this.name = name;
        this.lastname = lastname;
    };



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName;
    }
}
