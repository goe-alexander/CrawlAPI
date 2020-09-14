package crawlapi.resources;

import crawlapi.entities.Apartment;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ApartmentResourceAssembler
    implements RepresentationModelAssembler<Apartment, EntityModel<Apartment>> {
  @Override
  public EntityModel<Apartment> toModel(Apartment apartment) {
    return new EntityModel<>(
        apartment,
        linkTo(methodOn(ApartmentResource.class).getApartment(apartment.getId())).withSelfRel(),
        linkTo(methodOn(ApartmentResource.class).getAllApartments()).withRel("all"));
  }
}
