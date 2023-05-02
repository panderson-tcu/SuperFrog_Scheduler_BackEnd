package edu.tcu.cs.superfrogscheduler.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SchedulerUser, Integer> {

    Optional<SchedulerUser> findByUsername(String username);

}
