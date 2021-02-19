package pl.veterinaryClinicApplicationServer.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.veterinaryClinicApplicationServer.model.Passwords;

import java.util.List;

public interface PasswordsRepository extends JpaRepository<Passwords,Integer> {
    List<Passwords> findByPassword(String password);
    List<Passwords> findById(int id);
}
