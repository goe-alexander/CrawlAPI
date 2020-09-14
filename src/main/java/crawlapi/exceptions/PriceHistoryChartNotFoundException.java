package crawlapi.exceptions;

public class PriceHistoryChartNotFoundException extends RuntimeException{
    public PriceHistoryChartNotFoundException(Long apartmentId) {
        super("Could not find price history chart for apartment : " + apartmentId);
    }
}
