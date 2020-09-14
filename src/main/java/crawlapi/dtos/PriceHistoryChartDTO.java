package crawlapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PriceHistoryChartDTO extends RepresentationModel<PriceHistoryChartDTO> {
    private Long apartmentId;
    private Double firstPrice;
    private Double latestPrice;
    private Map<Double, Date> priceHistoryMap;
}
