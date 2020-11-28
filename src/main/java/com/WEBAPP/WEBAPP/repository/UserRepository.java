package com.WEBAPP.WEBAPP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.WEBAPP.WEBAPP.model.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    @Modifying
    @Query(value = "UPDATE role SET name='ROLE_CREATOR' WHERE id =:em",nativeQuery = true)
    void promoteUserToCreator(@Param("em")Integer id);

}
