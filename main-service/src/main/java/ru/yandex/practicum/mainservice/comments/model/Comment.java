package ru.yandex.practicum.mainservice.comments.model;

import lombok.*;
import ru.yandex.practicum.mainservice.event.model.Event;
import ru.yandex.practicum.mainservice.user.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @NotNull
    @Size(max = 1000)
    private String description;

    @Column(name = "created_on")
    private LocalDateTime createdOn = LocalDateTime.now();

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}