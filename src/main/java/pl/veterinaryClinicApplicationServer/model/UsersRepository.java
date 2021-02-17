package pl.veterinaryClinicApplicationServer.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {
    List<Users> findByNameOrLastname(String name, String lastname);
    List<Users> findByNameAndLastname(String name, String lastname);
    List<Users> findById(int id);
    List<Users> findByNickname(String nickname);
    List<Users> findByEmail(String email);

}
