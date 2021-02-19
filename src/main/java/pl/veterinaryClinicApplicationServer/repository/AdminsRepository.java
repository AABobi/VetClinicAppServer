package pl.veterinaryClinicApplicationServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.veterinaryClinicApplicationServer.model.Admins;

@Repository
public interface AdminsRepository extends JpaRepository<Admins,Integer> {
}
