package pl.veterinaryClinicApplicationServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.veterinaryClinicApplicationServer.model.Doctors;

@Repository
public interface DoctorsRepository extends JpaRepository<Doctors,Integer> {
}
