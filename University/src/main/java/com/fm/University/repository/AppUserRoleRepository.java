package com.fm.University.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fm.University.model.AppUserRole;

@Repository
public interface AppUserRoleRepository extends JpaRepository<AppUserRole, Long> {
}
