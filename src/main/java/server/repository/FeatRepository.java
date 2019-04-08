package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.model.Feat;

import java.util.List;

@Repository
public interface FeatRepository extends JpaRepository<Feat, Long> {
    List<Feat> findAllByOrderByIdAsc();
}
