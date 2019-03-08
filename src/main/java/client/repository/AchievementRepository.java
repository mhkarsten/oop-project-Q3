package client.repository;

import client.model.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}
