package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.model.Feat;
import java.util.List;
import java.util.Set;

@Repository
public interface FeatRepository extends JpaRepository<Feat, Long> {
    List<Feat> findAllByOrderByIdAsc();
    Set<Feat> findByUserId(long userId);
}
