package com.pl.radoslawKopec.vetApp.VetClinicAppServer.Model;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "date_of_the_visit")
public class DateOfTheVisit {

    @Id
    private int ID;

    private String dateof;

    @ManyToOne
    private TermsTime termsTime;

    @OneToOne(mappedBy = "dateOfTheVisit", cascade = CascadeType.ALL)
    private Users users;

    public DateOfTheVisit() {
    }

    ;

    @Override
    public String toString() {
        return "DateOfTheVisit{" +
                "ID=" + ID +
                ", dateof='" + dateof + '\'' +
                ", users=" + users +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDateof() {
        return dateof;
    }

    public void setDateof(String dateof) {
        this.dateof = dateof;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public DateOfTheVisit(int ID, String dateof, Users users) {
        this.ID = ID;
        this.dateof = dateof;
        this.users = users;
    }

    public DateOfTheVisit(int ID, String dateof) {
        this.ID = ID;
        this.dateof = dateof;
    }

    public String[] isWeekend(Calendar cal, String[] array, int i) {
        if ((cal.get(Calendar.DAY_OF_WEEK) == 1) || (cal.get(Calendar.DAY_OF_WEEK) == 7)) {

            return array;
        } else {
            Date date = cal.getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // String dateS = dateFormat.format(date);
            array[i] = dateFormat.format(date);
            return array;
        }
    }

    public String[] rewriteArray(String[] arrayWithDays, String[] arrayWithTimes) {
        String[] arrayWithoutWeekend = new String[5];
        int iterator = 0;
        for (int i = 0; i < arrayWithDays.length; i++) {
            if (arrayWithDays[i] != null) {
                arrayWithoutWeekend[iterator] = arrayWithDays[i];
              //  System.out.println(arrayWithoutWeekend[iterator]);
                iterator++;
            }
        }



        String[] arrayWithoutWeekendWithOurs = new String[5*arrayWithTimes.length];
        int ite = 0;
        int iteV2 =0;
        for (int i = 0; i < 5*arrayWithTimes.length; i++) {
            arrayWithoutWeekendWithOurs[i] = arrayWithoutWeekend[iteV2]+" "+arrayWithTimes[ite];
            ite++;
            if(ite==12) {
                ite = 0;
                iteV2++;
            }
        }

        return arrayWithoutWeekendWithOurs;
    }
}
