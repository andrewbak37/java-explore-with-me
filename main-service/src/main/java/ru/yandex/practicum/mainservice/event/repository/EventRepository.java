package ru.yandex.practicum.mainservice.event.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.mainservice.event.model.Event;
import ru.yandex.practicum.mainservice.event.model.EventSearchParams;
import ru.yandex.practicum.mainservice.event.model.EventState;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {

    default Page<Event> findEventsWithParams(EventSearchParams params, Pageable pageable, Boolean adminReq) {
        if (adminReq) {
            return findAll(getSpecificationByParams(params, adminReq), pageable);
        }
        return findPublishedEvents(getSpecificationByParams(params, adminReq), pageable);
    }

    default Specification<Event> getSpecificationByParams(EventSearchParams params, Boolean adminReq) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            if (adminReq) {
                if (params.getUsers() != null) {
                    for (Long id : params.getUsers()) {
                        predicates.add(criteriaBuilder.in(root.get("initiator").get("userId")).value(id));
                    }
                }
                if (params.getStates() != null) {
                    for (EventState requestState : params.getStates()) {
                        predicates.add(criteriaBuilder.in(root.get("state")).value(requestState));
                    }
                }
            }

            if (params.getText() != null) {
                predicates.add(
                        criteriaBuilder.or(
                                criteriaBuilder.like(root.get("annotation"), "%" + params.getText() + "%"),
                                criteriaBuilder.like(root.get("description"), "%" + params.getText() + "%"))
                );
            }
            if (params.getCategories() != null) {
                for (Long id : params.getCategories()) {
                    predicates.add(criteriaBuilder.in(root.get("category").get("id")).value(id));
                }
            }
            if (params.getPaid() != null) {
                predicates.add(criteriaBuilder.equal(root.get("paid"), params.getPaid()));
            }
            if (params.getRangeStart() != null) {
                predicates.add(criteriaBuilder.greaterThan(
                        root.get("publishedOn"),
                        LocalDateTime.parse(params.getRangeStart(), formatter)));
            }
            if (params.getRangeEnd() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("publishedOn"),
                        LocalDateTime.parse(params.getRangeEnd(), formatter)));
            }

            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        });
    }

    List<Event> findEventsByInitiatorUserId(Long userId, Pageable pageable);

    @Query(value = "SELECT e " +
            "FROM Event e " +
            "WHERE e.id = :eventId AND e.state = 'PUBLISHED'")
    Optional<Event> findPublishedEvent(Long eventId);

    @Query(value = "SELECT e " +
            "FROM Event e " +
            "WHERE e.state = 'PUBLISHED'")
    Page<Event> findPublishedEvents(Specification<Event> specification, Pageable pageable);

    @Query(value = "SELECT e " +
            "FROM Event e " +
            "WHERE e.id IN :ids")
    List<Event> findEventsByIds(List<Long> ids);
}