package by.epam.rentacar.service.exception;

public class CarNotAvailableException extends ServiceException {

    public CarNotAvailableException() {
    }

    public CarNotAvailableException(String message) {
        super(message);
    }

    public CarNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public CarNotAvailableException(Throwable cause) {
        super(cause);
    }

    public CarNotAvailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
