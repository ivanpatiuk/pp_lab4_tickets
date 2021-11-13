package lpnu.exception;

public class ServiceException extends RuntimeException{
    private int code;
    private String message;
    private String details;

    public ServiceException() { }

    public ServiceException(final int code, final String message) {
        this.code = code;
        this.message = message;
    }
    public ServiceException(final int code, final String message, final String details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }
    public ServiceException(final String message, final Throwable cause, final int code, final String details) {
        super(message, cause);
        this.code = code;
        this.message = message;
        this.details = details;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
