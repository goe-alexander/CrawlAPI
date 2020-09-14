package crawlapi.repositories;

import crawlapi.entities.PriceHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceHistoryRepository extends CrudRepository<PriceHistory, Long> {
    List<PriceHistory> findAllByApartmentId(Long apartmentId);
}
