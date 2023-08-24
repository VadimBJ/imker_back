package de.imker.repositories;

import de.imker.models.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);
  List<User> findAll();
}
