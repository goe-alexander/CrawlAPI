package crawlapi.resources;

import crawlapi.entities.Apartment;
import crawlapi.exceptions.ApartmentNotFoundException;
import crawlapi.services.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/apartment")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApartmentResource {

  private final ApartmentService apartmentService;
  private final ApartmentResourceAssembler apartmentAssembler;

  /*    @GetMapping("/details/{id}")
  Apartment getApartment(@PathVariable Long id) {
      return apartmentService.getApartmentDetails(id).orElseThrow(() -> new ApartmentNotFoundException(id));
  }*/
  @GetMapping("/details/{id}")
  EntityModel<Apartment> getApartment(@PathVariable Long id) {
    Apartment apartment =
        apartmentService
            .getApartmentDetails(id)
            .orElseThrow(() -> new ApartmentNotFoundException(id));
    return apartmentAssembler.toModel(apartment);
  }

  @GetMapping("/all")
  CollectionModel<EntityModel<Apartment>> getAllApartments() {
    List<EntityModel<Apartment>> apartmentDetails =
        apartmentService.getAllApartments().stream()
            .map(apartmentAssembler::toModel)
            .collect(Collectors.toList());

    return new CollectionModel<>(
        apartmentDetails,
        linkTo(methodOn(ApartmentResource.class).getAllApartments()).withSelfRel());
  }
}
