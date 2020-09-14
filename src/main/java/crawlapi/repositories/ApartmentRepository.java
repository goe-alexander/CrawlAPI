package crawlapi.repositories;

import crawlapi.entities.Apartment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends CrudRepository<Apartment, Long> {
    Apartment findByExternalIdAndSource(String externalId, String source);
}
