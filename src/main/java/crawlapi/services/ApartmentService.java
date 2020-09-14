package crawlapi.services;

import crawlapi.entities.Apartment;
import crawlapi.repositories.ApartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Throwable.class, timeout = 120)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApartmentService {
    private  final ApartmentRepository apartmentRepository;

    public Optional<Apartment> getApartmentDetails(Long id){
        return apartmentRepository.findById(id);
    }

    public List<Apartment> getAllApartments(){
        return (List<Apartment>) apartmentRepository.findAll();
    }
}
