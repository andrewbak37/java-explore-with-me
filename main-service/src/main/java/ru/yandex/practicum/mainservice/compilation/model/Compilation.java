package ru.yandex.practicum.mainservice.compilation.model;

import lombok.*;
import ru.yandex.practicum.mainservice.event.model.Event;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "compilations")
public class Compilation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compilation_id")
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotNull
    private Boolean pinned;

    @ManyToMany
    @JoinTable(name = "compilation_events",
            joinColumns = {
                    @JoinColumn(name = "compilation_id", referencedColumnName = "compilation_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
            })
    private List<Event> events;
}