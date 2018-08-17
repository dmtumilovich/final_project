package by.epam.rentacar.service.exception;

public class PasswordsNotEqualException extends ServiceException {

    public PasswordsNotEqualException() {
    }

    public PasswordsNotEqualException(String message) {
        super(message);
    }

    public PasswordsNotEqualException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordsNotEqualException(Throwable cause) {
        super(cause);
    }

    public PasswordsNotEqualException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
