package com.ecommerce.backend.UserData;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    boolean existsByEmail(String email);

    @Query("SELECT u.role FROM UserEntity u WHERE u.username = :username")
    String findRoleByUsername(@Param("username") String username);

    
}
