package org.itstep.msk.app.repositories;

import org.itstep.msk.app.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query(
            value = "SELECT user_id FROM USERSEVENTS WHERE USERSEVENTS.EVENT_ID = ?1",
            nativeQuery = true
    )
    Set<Integer> findUsersForEvent(Integer id);
}
