package de.imker.repositories;


import de.imker.dto.EventFollowDto;
import de.imker.models.EventFollow;
import de.imker.models.FileUpload;
import de.imker.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UsersOnEventsRepository extends JpaRepository <EventFollow, Long> {

//    List<EventFollow> findAllByEvent_id(Long eventId);
//    List<EventFollow> Fi(Long eventId);
//    List<EventFollow> findEventFollowByUser_id(Long userId);

}
