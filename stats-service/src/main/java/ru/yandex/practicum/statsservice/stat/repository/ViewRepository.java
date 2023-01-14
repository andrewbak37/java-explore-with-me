package ru.yandex.practicum.statsservice.stat.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.statsservice.stat.model.StatsSearchParams;
import ru.yandex.practicum.statsservice.stat.model.View;
import ru.yandex.practicum.statsservice.stat.model.ViewStatsInterface;

import javax.persistence.criteria.Predicate;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface ViewRepository extends JpaRepository<View, Long>, JpaSpecificationExecutor<View> {

    @Query(value = "select v.app AS app, v.uri AS uri, COUNT(*) as hits FROM VIEWS v WHERE view_id in (:ids)" +
            "GROUP BY v.app, v.uri", nativeQuery = true)
    List<ViewStatsInterface> countViewByIds(@Param("ids") List<Long> ids);

    @Query(value = "SELECT v.app AS app, v.uri AS uri, COUNT(*) as hits FROM " +
            "(SELECT app, uri from VIEWS group by ip, uri WHERE view_id in (:ids)) AS v " +
            "GROUP BY v.app, v.uri", nativeQuery = true)
    List<ViewStatsInterface> countUniqueView(@Param("ids") List<Long> ids);

    default List<View> searchStatsByParams(StatsSearchParams params) {
        return findAll(getSpecificationByParams(params));
    }

    default Specification<View> getSpecificationByParams(StatsSearchParams params) {
        return (((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            if (params.getUris() != null && !params.getUris().isEmpty()) {
                List<Predicate> orPredicates = new ArrayList<>();
                for (String uri : params.getUris()) {
                    orPredicates.add(criteriaBuilder.like(root.get("uri"), uri + "%"));
                }
                predicates.add(criteriaBuilder.or(orPredicates.toArray(Predicate[]::new)));
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
