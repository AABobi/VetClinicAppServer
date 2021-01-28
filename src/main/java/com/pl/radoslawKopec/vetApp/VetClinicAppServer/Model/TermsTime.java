package com.pl.radoslawKopec.vetApp.VetClinicAppServer.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "terms_time")
public class TermsTime {

    @Id
    private int id;

    private String time;

    @OneToMany(mappedBy = "termsTime")
    private Set<DateOfTheVisit> dateOfTheVisit;
}
