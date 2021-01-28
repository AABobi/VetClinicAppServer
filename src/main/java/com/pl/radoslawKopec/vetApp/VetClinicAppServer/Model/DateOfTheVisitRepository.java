package com.pl.radoslawKopec.vetApp.VetClinicAppServer.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DateOfTheVisitRepository  extends JpaRepository<DateOfTheVisit,Integer> {
List<DateOfTheVisit> findById(int id);
List<DateOfTheVisit> findByDateof(String date);
}
