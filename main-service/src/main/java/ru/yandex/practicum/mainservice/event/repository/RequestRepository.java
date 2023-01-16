package ru.yandex.practicum.mainservice.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.mainservice.event.request.Request;
import ru.yandex.practicum.mainservice.event.request.RequestCountConfirmedView;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    Optional<Request> findRequestByIdAndEvent_IdAndEvent_Initiator_UserId(Long id, Long eventId, Long userId);

    @Query(value = "SELECT COUNT (R.id) " +
                    "FROM Request R " +
                    "WHERE R.event.id = :eventId AND R.status = 'CONFIRMED'")
    Long countConfirmedRequests(Long eventId);

    @Query(value = "SELECT R.event.id, COUNT (R.id) " +
            "FROM Request R " +
            "WHERE R.event.id IN (:eventIds) AND R.status = 'CONFIRMED' " +
            "GROUP BY R.event.id")
    List<RequestCountConfirmedView> countConfirmedRequests(List<Long> eventIds);

    List<Request> findRequestsByEventId(Long eventId);

    List<Request> findRequestsByRequester_userId(Long userId);

}
