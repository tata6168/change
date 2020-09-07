package com.tata.change.util.result;

import com.tata.change.base.demo.Demo;

import java.util.ArrayList;
import java.util.List;

public class ResultJson {
    Long count;
    List<? extends Demo> data = new ArrayList<>();
    String message;

    public ResultJson() {
    }

    public ResultJson(Long count, List<? extends Demo> data, String message) {
        this.count = count;
        this.data = data;
        this.message = message;
    }

    public ResultJson(String request_failure) {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<? extends Demo> getData() {
        return data;
    }

    public void setData(List<? extends Demo> data) {
        this.data = data;
    }
}
