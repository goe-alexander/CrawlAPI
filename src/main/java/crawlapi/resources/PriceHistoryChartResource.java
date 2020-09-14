package crawlapi.resources;

import crawlapi.dtos.PriceHistoryChartDTO;
import crawlapi.exceptions.PriceHistoryChartNotFoundException;
import crawlapi.services.PriceHistoryService;
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
@RequestMapping("/price_history")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PriceHistoryChartResource {
  private final PriceHistoryService priceHistoryService;
  private final PriceHistoryChartResourceAssembler priceHistoryChartAssembler;

  @GetMapping("/chart/{apartment_id}")
  EntityModel<PriceHistoryChartDTO> getPriceHistoryChart(@PathVariable Long apartment_id) {
    PriceHistoryChartDTO priceHistoryChartDTO =
        priceHistoryService
            .getPriceHistoryChartForApartment(apartment_id)
            .orElseThrow(() -> new PriceHistoryChartNotFoundException(apartment_id));
    return priceHistoryChartAssembler.toModel(priceHistoryChartDTO);
  }

  @GetMapping("/all")
  CollectionModel<EntityModel<PriceHistoryChartDTO>> getAllPriceHistoryCharts() {
      List<EntityModel<PriceHistoryChartDTO>> priceHistoryCharts = priceHistoryService
              .getAllPriceHistoryCharts()
              .stream()
              .map(priceHistoryChartAssembler::toModel)
              .collect(Collectors.toList());

      return new CollectionModel<>(priceHistoryCharts,
              linkTo(methodOn(PriceHistoryChartResource.class).getAllPriceHistoryCharts()).withSelfRel());
  }


}
