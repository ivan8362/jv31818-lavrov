package org.itstep.msk.app.repositories;

import org.itstep.msk.app.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
//    Optional<User> findByLastName(String lastName);

    Iterable<User> findAll();
}
