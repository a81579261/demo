package com.example.demo.utils.Response;

import java.io.Serializable;

public class GlobalResponseCode implements Serializable {
    private static final long serialVersionUID = 2862665858875203182L;
    public static final int BIZ_ERROR_STATUS = 0;
    private static final String SYS_ERROR_CODE = "SYS_ERROR";
    private static final String SYS_BUSY_CODE = "SYS_BUSY";
    private static final String SYS_OK = "SYS_OK";
    private static final String SYS_NOT_LOGIN = "SYS_NOT_LOGIN";
    private static final String SYS_POJO_COPY_ERROR = "SYS_POJO_COPY_FAILED";
    private static final String SYS_PARAM_ERROR_CODE = "SYS_PARMA_ERROR";
    private static final String SYS_REMOTE_CALL_ERROR_CODE = "SYS_REMOTE_CALL_ERROR";
    private static final String SYS_BIZ_DATA_ERROR_CODE = "SYS_BIZ_DATA_ERROR_CODE";
    public static final GlobalResponseCode SYS_SUCCUES = new GlobalResponseCode(1, "SYS_OK", "请求成功！");
    public static final GlobalResponseCode SYS_FAILED = new GlobalResponseCode(0, "SYS_ERROR", "应用程序发生异常，请稍后再试！");
    public static final GlobalResponseCode SYS_BUSY = new GlobalResponseCode(0, "SYS_BUSY", "系统繁忙，请稍后再试！");
    public static final GlobalResponseCode SYS_NO_LOGIN = new GlobalResponseCode(-1, "SYS_NOT_LOGIN", "未登录!");
    public static final GlobalResponseCode SYS_NO_PERMISSION = new GlobalResponseCode(-2, "SYS_NO_PERMISSION", "无访问权限!");
    public static final GlobalResponseCode SYS_TOKEN_INVALID = new GlobalResponseCode(-3, "SYS_TOKEN_INVALID", "无效token!");
    public static final GlobalResponseCode SYS_POJO_COPY_FAILED = new GlobalResponseCode(0, "SYS_POJO_COPY_FAILED", "属性拷贝异常！");
    public static final GlobalResponseCode SYS_PARAM_ERROR = new GlobalResponseCode("SYS_PARMA_ERROR", "[%s]");
    public static final GlobalResponseCode SYS_REMOTE_CALL_ERROR = new GlobalResponseCode("SYS_REMOTE_CALL_ERROR", "[%s]");
    public static final GlobalResponseCode SYS_BIZ_DATA_ERROR = new GlobalResponseCode("SYS_BIZ_DATA_ERROR_CODE", "[%s]");
    public static final GlobalResponseCode EXAMPLE_EXCEPTION = new GlobalResponseCode("EXAMPLE_EXCEPTION", "异常自定义示例%s");
    private int status;
    private String code;
    private String message;

    public static GlobalResponseCode buildBizErrorConst(String errMsg) {
        return new GlobalResponseCode("SYS_ERROR", errMsg);
    }

    private GlobalResponseCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public GlobalResponseCode(String code, String message) {
        this.status = 0;
        this.code = code;
        this.message = message;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GlobalResponseCode changeMessage(String message) {
        this.message = message;
        return this;
    }

    public GlobalResponseCode format(String... formatArgs) {
        if (formatArgs != null && formatArgs.length > 0) {
            this.message = String.format(this.message, formatArgs);
        }

        return this;
    }
}
