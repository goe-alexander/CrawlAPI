package crawlapi.resources;

import crawlapi.dtos.PriceHistoryChartDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PriceHistoryChartResourceAssembler
    implements RepresentationModelAssembler<
        PriceHistoryChartDTO, EntityModel<PriceHistoryChartDTO>> {
  @Override
  public EntityModel<PriceHistoryChartDTO> toModel(PriceHistoryChartDTO priceHistoryChartDTO) {
    return new EntityModel<>(
        priceHistoryChartDTO,
        linkTo(
                methodOn(PriceHistoryChartResource.class)
                    .getPriceHistoryChart(priceHistoryChartDTO.getApartmentId()))
            .withSelfRel(),
        linkTo(methodOn(PriceHistoryChartResource.class).getAllPriceHistoryCharts())
            .withRel("all"));
  }
}
