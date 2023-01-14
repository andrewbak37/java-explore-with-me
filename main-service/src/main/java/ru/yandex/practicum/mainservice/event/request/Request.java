package ru.yandex.practicum.mainservice.event.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.yandex.practicum.mainservice.event.model.Event;
import ru.yandex.practicum.mainservice.user.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "requester_id")
    private User requester;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Enumerated(value = EnumType.STRING)
    private  RequestState status;

    private LocalDateTime created = LocalDateTime.now();
}
