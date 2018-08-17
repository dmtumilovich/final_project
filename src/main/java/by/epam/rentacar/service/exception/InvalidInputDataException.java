package by.epam.rentacar.service.exception;

public class InvalidInputDataException extends ServiceException {

    public InvalidInputDataException() {
    }

    public InvalidInputDataException(String message) {
        super(message);
    }

    public InvalidInputDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputDataException(Throwable cause) {
        super(cause);
    }

    public InvalidInputDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
