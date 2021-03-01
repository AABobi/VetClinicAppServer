package pl.veterinaryClinicApplicationServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.veterinaryClinicApplicationServer.model.Admins;
import pl.veterinaryClinicApplicationServer.model.Doctors;

import java.util.List;

@Repository
public interface DoctorsRepository extends JpaRepository<Doctors,Integer> {
    List<Doctors> findByNickname(String nickname);
    List<Doctors> findByNameAndLastname(String name, String lastname);
    List<Doctors> findById(int id);
    List<Doctors> findByEmail(String email);
}
