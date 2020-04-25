package com.example.demo.utils.Response;

public class MyException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final GlobalResponseCode code;
    private String[] formatArgs;

    public MyException() {
        this.code = GlobalResponseCode.SYS_FAILED;
    }

    public MyException(GlobalResponseCode code) {
        this.code = code;
    }

    public MyException(GlobalResponseCode code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public MyException(GlobalResponseCode code, String... formatArgs) {
        this.code = code;
        this.formatArgs = formatArgs;
    }

    public MyException(GlobalResponseCode code, Throwable cause, String... formatArgs) {
        super(cause);
        this.code = code;
        this.formatArgs = formatArgs;
    }

    public String getMessage() {
        String errorCode = this.getErrorCode();
        return this.formatArgs != null ? "[" + errorCode + "] " + String.format(this.code.getMessage(), (Object[])this.formatArgs) : "[" + errorCode + "] " + this.code.getMessage();
    }

    public String getContent() {
        return this.formatArgs != null ? String.format(this.code.getMessage(), (Object[])this.formatArgs) : this.code.getMessage();
    }

    public String getErrorCode() {
        return this.code.getCode();
    }
}
