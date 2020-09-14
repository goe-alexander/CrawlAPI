package crawlapi.services;

import crawlapi.dtos.PriceHistoryChartDTO;
import crawlapi.entities.PriceHistory;
import crawlapi.repositories.PriceHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class, timeout = 120)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PriceHistoryService {

  private final PriceHistoryRepository priceHistoryRepository;

  public List<PriceHistoryChartDTO> getAllPriceHistoryCharts() {
    List<PriceHistory> allPriceHistories = (List<PriceHistory>) priceHistoryRepository.findAll();
    Map<Long, List<PriceHistory>> priceHistoryMap =
        allPriceHistories.stream()
            .collect(
                Collectors.groupingBy(
                    priceHistory -> priceHistory.getApartment().getId(), Collectors.toList()));
    List<PriceHistoryChartDTO> priceHistoryChartDTOS = new ArrayList<>();
    priceHistoryMap
        .entrySet()
        .forEach(
            priceHistoryEntry ->
                generatePriceHistoryChartDTO(
                        priceHistoryEntry.getKey(), priceHistoryEntry.getValue())
                    .ifPresent(priceHistoryChart -> priceHistoryChartDTOS.add(priceHistoryChart)));
    return priceHistoryChartDTOS;
  }

  public Optional<PriceHistoryChartDTO> getPriceHistoryChartForApartment(Long apartmentId) {
    List<PriceHistory> priceHistoriesForApartment =
        priceHistoryRepository.findAllByApartmentId(apartmentId);
    return generatePriceHistoryChartDTO(apartmentId, priceHistoriesForApartment);
  }

  private Optional<PriceHistoryChartDTO> generatePriceHistoryChartDTO(
      Long apartmentId, List<PriceHistory> priceHistoriesForApartment) {
    PriceHistoryChartDTO priceHistoryChartDTO = new PriceHistoryChartDTO();
    priceHistoryChartDTO.setApartmentId(apartmentId);
    priceHistoryChartDTO.setFirstPrice(
        priceHistoriesForApartment.stream()
            .min(Comparator.comparing(PriceHistory::getChangedAt))
            .get()
            .getOldPrice());
    priceHistoryChartDTO.setLatestPrice(
        priceHistoriesForApartment.stream()
            .max(Comparator.comparing(PriceHistory::getChangedAt))
            .get()
            .getNewPrice());
    priceHistoryChartDTO.setPriceHistoryMap(
        priceHistoriesForApartment.stream()
            .sorted(
                Comparator.comparing(
                    PriceHistory::getChangedAt, Comparator.nullsLast(Comparator.reverseOrder())))
            .collect(
                Collectors.toMap(
                    PriceHistory::getNewPrice,
                    PriceHistory::getChangedAt,
                    (oldValue, newValue) -> oldValue,
                    LinkedHashMap::new)));

    return Optional.ofNullable(priceHistoryChartDTO);
  }
}
