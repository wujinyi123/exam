package com.system.exam.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData<T> implements Serializable {

    private String code;

    private String msg;

    private String count;

    private T data;

    public ResponseData(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseData(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseData(ResultEnums resultEnums) {
        this.code = resultEnums.getCode();
        this.msg = resultEnums.getMsg();
    }

    public ResponseData(ResultEnums resultEnums, T data) {
        this.code = resultEnums.getCode();
        this.msg = resultEnums.getMsg();
        this.data = data;
    }

    public ResponseData(T data, PageQO pageQO) {
        this.code = ResultEnums.SUCCESS.getCode();
        this.msg = ResultEnums.SUCCESS.getMsg();
        this.count = ((List) data).size() + "";
        if (pageQO.getPage() * pageQO.getLimit() <= ((List) data).size()) {
            this.data = (T) ((List) data).subList((pageQO.getPage() - 1) * pageQO.getLimit(), pageQO.getPage() * pageQO.getLimit());
        } else {
            this.data = (T) ((List) data).subList((pageQO.getPage() - 1) * pageQO.getLimit(), ((List) data).size());
        }
    }

    public ResponseData() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
