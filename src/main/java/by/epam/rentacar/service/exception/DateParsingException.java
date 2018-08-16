package by.epam.rentacar.service.exception;

public class DateParsingException extends ServiceException {

    public DateParsingException() {
    }

    public DateParsingException(String message) {
        super(message);
    }

    public DateParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public DateParsingException(Throwable cause) {
        super(cause);
    }

    public DateParsingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
