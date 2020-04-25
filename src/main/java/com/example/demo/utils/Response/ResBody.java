package com.example.demo.utils.Response;

public class ResBody<T> {
    private int status;
    private String code;
    private String message;
    private T data;

    private ResBody() {
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static ResBody buildSuccessResBody() {
        return buildSuccessResBody((Object)null, GlobalResponseCode.SYS_SUCCUES);
    }

    public static ResBody buildSuccessResBody(Object data) {
        return buildSuccessResBody(data, GlobalResponseCode.SYS_SUCCUES);
    }

//    public static ResBody buildSuccessResBody(ResPageDTO page) {
//        return buildSuccessResBody(page, GlobalResponseCode.SYS_SUCCUES);
//    }

    public static ResBody buildSuccessResBody(Object data, GlobalResponseCode successConst) {
        ResBody res = new ResBody();
        res.setStatus(successConst.getStatus());
        res.setCode(successConst.getCode());
        res.setMessage(successConst.getMessage());
        if (data != null) {
            res.setData(data);
        }

        return res;
    }

    public static ResBody buildFailResBody() {
        return buildFailResBody(GlobalResponseCode.SYS_FAILED);
    }

    public static ResBody buildBusyResBody() {
        return buildFailResBody(GlobalResponseCode.SYS_BUSY);
    }

    public static ResBody buildFailResBody(GlobalResponseCode errorConst) {
        ResBody res = new ResBody();
        res.setStatus(errorConst.getStatus());
        res.setCode(errorConst.getCode());
        res.setMessage(errorConst.getMessage());
        return res;
    }

    public static ResBody buildFailTextResBody(String code, String message) {
        ResBody res = new ResBody();
        res.setStatus(GlobalResponseCode.SYS_FAILED.getStatus());
        res.setCode(code);
        res.setMessage(message);
        return res;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static ResBody BuildResBody(int status, String code, String message, Object data) {
        ResBody res = new ResBody();
        res.setStatus(status);
        res.setCode(code);
        res.setMessage(message);
        res.setData(data);
        return res;
    }
}