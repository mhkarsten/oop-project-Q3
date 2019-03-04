package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.model.TrackingOption;

@Repository
public interface TrackingOptionRepository extends JpaRepository<TrackingOption, Long> {

}
