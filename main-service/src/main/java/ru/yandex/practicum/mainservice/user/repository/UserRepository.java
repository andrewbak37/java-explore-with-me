package ru.yandex.practicum.mainservice.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.mainservice.user.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT U " +
                    "FROM User U " +
                    "WHERE U.userId IN :userIds")
    List<User> findUsersByIds(List<Long> userIds);

}
