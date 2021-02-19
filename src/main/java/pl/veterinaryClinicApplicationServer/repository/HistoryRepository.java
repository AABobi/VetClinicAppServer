package pl.veterinaryClinicApplicationServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.veterinaryClinicApplicationServer.model.History;

import javax.persistence.Entity;


public interface HistoryRepository extends JpaRepository<History,Integer> {
}
