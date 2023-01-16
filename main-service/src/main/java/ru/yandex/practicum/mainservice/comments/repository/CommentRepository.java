package ru.yandex.practicum.mainservice.comments.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.mainservice.comments.model.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

//    @Query(value = "SELECT c " +
//            "FROM Comment c " +
//            "WHERE c.event.id = :eventId ")
//    List<Comment> findCommentsByEventId(Long eventId, Pageable pageable);

    List<Comment> findCommentsByEventId(Long eventId, Pageable pageable);

    List<Comment> findCommentsByOwner_UserId(Long userId, Pageable pageable);
}