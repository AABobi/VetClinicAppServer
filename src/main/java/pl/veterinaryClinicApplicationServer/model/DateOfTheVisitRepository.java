package pl.veterinaryClinicApplicationServer.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DateOfTheVisitRepository  extends JpaRepository<DateOfTheVisit,Integer> {
List<DateOfTheVisit> findById(int id);
List<DateOfTheVisit> findByDateof(String date);
}
