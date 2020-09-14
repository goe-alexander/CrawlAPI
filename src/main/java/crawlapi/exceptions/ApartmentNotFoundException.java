package crawlapi.exceptions;

public class ApartmentNotFoundException extends RuntimeException{
    public ApartmentNotFoundException(Long id) {
        super("Could not find apartment : " + id);
    }
}
