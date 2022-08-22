package by.deliveryservice.error;

public class AppException extends RuntimeException {

    private final ErrorType type;
    private final String msgCode;

    public AppException(String msgCode, ErrorType type) {
        this.msgCode = msgCode;
        this.type = type;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public ErrorType getType() {
        return type;
    }
}
