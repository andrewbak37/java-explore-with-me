package ru.yandex.practicum.mainservice.event.model;

import lombok.*;
import ru.yandex.practicum.mainservice.category.model.Category;
import ru.yandex.practicum.mainservice.compilation.model.Compilation;
import ru.yandex.practicum.mainservice.user.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @NotBlank
    @Size(max = 250)
    private String title;

    @NotBlank
    @Size(max = 3000)
    private String annotation;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private Boolean paid;

    @Column(name = "event_date")
    private LocalDateTime eventDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "initiator_id")
    private User initiator;

    @NotBlank
    @Size(max = 10000)
    private String description;

    @Enumerated(value = EnumType.STRING)
    private EventState state = EventState.PENDING;

    @Column(name = "created_on")
    private LocalDateTime createdOn = LocalDateTime.now();

    @OneToOne
    @JoinTable(name = "event_locations",
            joinColumns = {
                    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
            })
    private Location location;

    @Column(name = "req_moderation")
    private Boolean requestModeration;

    @ManyToMany(mappedBy = "events")
    private List<Compilation> compilations;

    @Column(name = "published_on")
    private LocalDateTime publishedOn;

    @Column(name = "part_limit")
    private Integer participantLimit;
}

