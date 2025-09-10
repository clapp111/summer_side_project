package server.board.domain.assignment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import server.board.domain.assignment.entity.Assignment;
import server.board.domain.user.entity.User;

import java.util.Optional;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    @Query("select a from Assignment a join fetch a.user left join fetch a.recommendationList where a.id = :id")
    Optional<Assignment> findByIdWithUserAndRecommendations(@Param("id") Long id);

    @Query("SELECT a FROM Assignment a ORDER BY a.createdAt DESC")
    Page<Assignment> findAllOrderByCreatedAt(Pageable pageable);

    @Query("SELECT a FROM Assignment a LEFT JOIN a.recommendationList r GROUP BY a " +
            "ORDER BY COUNT(r) DESC, a.createdAt DESC")
    Page<Assignment> findAllOrderByRecommendation(Pageable pageable);

    @Query("SELECT a FROM Assignment a LEFT JOIN a.recommendationList r WHERE a.user.id = :userId GROUP BY a " +
            "ORDER BY COUNT(r) DESC, a.createdAt DESC")
    Page<Assignment> findUserAssignmentsOrderByRecommendation(@Param("userId") Long userId, Pageable pageable);

    Page<Assignment> findByUser(User user, Pageable pageable);
}
