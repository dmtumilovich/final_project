package by.epam.rentacar.service.exception;

public class DateRangeAlreadyTakenException extends ServiceException {

    public DateRangeAlreadyTakenException() {
    }

    public DateRangeAlreadyTakenException(String message) {
        super(message);
    }

    public DateRangeAlreadyTakenException(String message, Throwable cause) {
        super(message, cause);
    }

    public DateRangeAlreadyTakenException(Throwable cause) {
        super(cause);
    }

    public DateRangeAlreadyTakenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
