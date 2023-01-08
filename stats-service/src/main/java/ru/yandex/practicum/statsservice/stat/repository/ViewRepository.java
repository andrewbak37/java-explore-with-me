package ru.yandex.practicum.statsservice.stat.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.statsservice.stat.model.StatsSearchParams;
import ru.yandex.practicum.statsservice.stat.model.View;

import javax.persistence.criteria.Predicate;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface ViewRepository extends JpaRepository<View, Long>, JpaSpecificationExecutor<View> {

    Long countViewByUri(String uri);

    @Query(value = "SELECT DISTINCT COUNT(v) " +
            "FROM View v " +
            "WHERE v.uri = :uri")
    Long countUniqueView(String uri);

    default List<View> searchStatsByParams(StatsSearchParams params) {
        return findAll(getSpecificationByParams(params));
    }

    default Specification<View> getSpecificationByParams(StatsSearchParams params) {
        return (((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            if (params.getUris() != null) {
                for (String uri : params.getUris()) {
                    predicates.add(criteriaBuilder.like(root.get("uri"), uri + "%"));
                }
            }
            if (params.getStart() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("timestamp"),
                        LocalDateTime.parse(URLDecoder.decode(params.getStart(), StandardCharsets.UTF_8), formatter))
                );
            }
            if (params.getEnd() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("timestamp"),
                        LocalDateTime.parse(URLDecoder.decode(params.getEnd(), StandardCharsets.UTF_8), formatter))
                );
            }

            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        }));
    }
}
