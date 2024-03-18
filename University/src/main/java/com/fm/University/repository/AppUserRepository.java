package com.fm.University.repository;

import com.fm.University.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	@Query("SELECT COUNT(c) FROM AppUser c")
	Long getCount();

	@Query(value = "SELECT c.* FROM app_user c WHERE username = :username", nativeQuery = true)
	Optional<AppUser> findByUsername(@Param("username") String username);

}
