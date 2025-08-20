package server.board.domain.assignment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import server.board.domain.assignment.entity.Assignment;
import server.board.domain.user.entity.User;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    @Query("SELECT a FROM Assignment a ORDER BY a.createdAt DESC")
    Page<Assignment> findAllOrderByCreatedAt(Pageable pageable);

    @Query("SELECT a FROM Assignment a LEFT JOIN a.recommendationList r GROUP BY a " +
            "ORDER BY COUNT(r) DESC, a.createdAt DESC")
    Page<Assignment> findAllOrderByRecommendation(Pageable pageable);

    @Query("SELECT a FROM Assignment a LEFT JOIN a.recommendationList r WHERE a.user.id = :userId GROUP BY a " +
            "ORDER BY COUNT(r) DESC, a.createdAt DESC")
    Page<Assignment> findUserAssignmentsOrderByRecommendation(@Param("userId") Long userId, Pageable pageable);

    Page<Assignment> findByUserId(Long userId, Pageable pageable);
}
