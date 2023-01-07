package ru.yandex.practicum.mainservice.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.mainservice.event.request.Request;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query(value = "SELECT COUNT (R.id) " +
                    "FROM Request R " +
                    "WHERE R.event.id = :eventId AND R.status = 'CONFIRMED'")
    Long countConfirmedRequests(Long eventId);

    List<Request> findRequestsByEventId(Long eventId);

    List<Request> findRequestsByRequester_userId(Long userId);

}
