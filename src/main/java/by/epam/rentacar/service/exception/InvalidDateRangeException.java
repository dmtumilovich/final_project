package by.epam.rentacar.service.exception;

public class InvalidDateRangeException extends ServiceException {

    public InvalidDateRangeException() {
    }

    public InvalidDateRangeException(String message) {
        super(message);
    }

    public InvalidDateRangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDateRangeException(Throwable cause) {
        super(cause);
    }

    public InvalidDateRangeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
