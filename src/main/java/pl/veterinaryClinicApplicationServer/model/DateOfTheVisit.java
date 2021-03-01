package pl.veterinaryClinicApplicationServer.model;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "date_of_the_visit")
public class DateOfTheVisit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String dateof;

    @OneToOne(cascade = CascadeType.ALL)
    private Users users;

    @OneToOne(cascade = CascadeType.ALL)
    private Doctors doctors;


    public DateOfTheVisit() {
    }

    public DateOfTheVisit(String dateof, Users users){
        this.dateof = dateof;
        this.users = users;
    }
    @Override
    public String toString() {
        return "DateOfTheVisit{" +
                "ID=" + id +
                ", dateof='" + dateof + '\'' +
                ", users=" + users +
                ", doctors=" + doctors +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int ID) {
        this.id = ID;
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

    public Doctors getDoctors() {
        return doctors;
    }

    public void setDoctors(Doctors doctors) {
        this.doctors = doctors;
    }

    public DateOfTheVisit(int id, String dateof) {
        this.id = id;
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
