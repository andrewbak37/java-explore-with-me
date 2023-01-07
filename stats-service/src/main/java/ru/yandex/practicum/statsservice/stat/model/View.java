package ru.yandex.practicum.statsservice.stat.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "views")
public class View {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "view_id")
    private Long id;

    @NotNull
    @Size(max = 100)
    private String app;

    @NotNull
    @Size(max = 100)
    private String uri;

    @NotNull
    @Size(max = 100)
    private String ip;

    private LocalDateTime timestamp;
}