package org.mj.audio.repository;

import org.mj.audio.model.UserModelIP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModelIP, Long> {
    Optional<UserModelIP> findByUsername(@Param("username") String username);
}
