package com.zerobase.reservation.domain.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.zerobase.reservation.domain.manager.entity.ManagerEntity;

@Repository
public interface ManagerRepository extends JpaRepository<ManagerEntity, Long> {

    boolean existsByUsername(String username);

    ManagerEntity findByUsername(String username);

}
