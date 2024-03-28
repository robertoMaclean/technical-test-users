package cl.technicaltest.user.repository;

import cl.technicaltest.user.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
