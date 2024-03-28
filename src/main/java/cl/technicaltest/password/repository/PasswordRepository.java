package cl.technicaltest.password.repository;

import cl.technicaltest.password.model.Password;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<Password, Long> {
}
