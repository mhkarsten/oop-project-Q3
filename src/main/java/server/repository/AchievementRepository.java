package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.model.Achievement;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}
