package pl.veterinaryClinicApplicationServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.veterinaryClinicApplicationServer.model.DateOfTheVisit;

import java.util.List;


public interface DateOfTheVisitRepository  extends JpaRepository<DateOfTheVisit,Integer> {
List<DateOfTheVisit> findById(int id);
List<DateOfTheVisit> findByDateof(String date);
}
