package org.boot.security.repository;

import org.boot.security.dto.UserDTO;
import org.boot.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select new org.boot.security.dto.UserDTO(u.id, u.username, u.role) from User u")
    List<UserDTO> findAllUsers();

    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.role.name = :roleName")
    List<User> findAllUsersByRoleName(@Param("roleName") String roleName);
}
