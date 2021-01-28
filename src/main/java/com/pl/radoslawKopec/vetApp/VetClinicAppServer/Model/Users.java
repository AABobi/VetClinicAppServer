package com.pl.radoslawKopec.vetApp.VetClinicAppServer.Model;

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

    @Column(name = "last_name")
    private String lastname;

    @OneToOne(cascade = CascadeType.ALL)
    private Passwords passwords;

    @OneToOne(cascade = CascadeType.ALL)
    private DateOfTheVisit dateOfTheVisit;

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

    public Users(String f){
        this.nickname = f;
        this.name = f;
        this.lastname = f;
    }
    public Users( String nickname, String name, String lastname, Passwords passwords) {
        this.nickname = nickname;
        this.name = name;
        this.lastname = lastname;
        this.passwords = passwords;
    }

    public Users(int id, String nickname, String name, String lastname, Passwords passwords, DateOfTheVisit dateOfTheVisit) {
        this.id = id;
        this.nickname = nickname;
        this.name = name;
        this.lastname = lastname;
        this.passwords = passwords;
        this.dateOfTheVisit = dateOfTheVisit;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", passwords=" + passwords +
                ", dateOfTheVisit=" + dateOfTheVisit +
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Passwords getPasswords() {
        return passwords;
    }

    public void setPasswords(Passwords passwords) {
        this.passwords = passwords;
    }

    public DateOfTheVisit getDateOfTheVisit() {
        return dateOfTheVisit;
    }

    public void setDateOfTheVisit(DateOfTheVisit dateOfTheVisit) {
        this.dateOfTheVisit = dateOfTheVisit;
    }
}
