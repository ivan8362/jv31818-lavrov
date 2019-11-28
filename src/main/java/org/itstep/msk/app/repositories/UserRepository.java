package org.itstep.msk.app.repositories;

import org.itstep.msk.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//    Optional<User> findByLastName(String lastName);

    List<User> findAll();
}
