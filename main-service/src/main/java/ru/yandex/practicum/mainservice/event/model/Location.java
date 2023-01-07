package ru.yandex.practicum.mainservice.event.model;

import lombok.*;
import ru.yandex.practicum.mainservice.event.model.Event;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    @OneToOne(mappedBy = "location")
    private Event event;

    private Float lat;

    private Float lon;

}
