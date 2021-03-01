package pl.veterinaryClinicApplicationServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.veterinaryClinicApplicationServer.model.Admins;
import pl.veterinaryClinicApplicationServer.model.Users;

import java.util.List;

@Repository
public interface AdminsRepository extends JpaRepository<Admins,Integer> {
    List<Admins> findByNickname(String nickname);
    List<Admins> findByNameAndLastname(String name, String lastname);
    List<Admins> findById(int id);
    List<Admins> findByEmail(String email);

}
