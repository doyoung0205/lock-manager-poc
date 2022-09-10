package me.doyoung.lockmanagerpoc.infra;

import me.doyoung.lockmanagerpoc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
