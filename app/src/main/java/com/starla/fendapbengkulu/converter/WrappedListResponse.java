package com.starla.fendapbengkulu.converter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WrappedListResponse<T> {
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private int status;
    @SerializedName("data")
    private List<T> data = new ArrayList<>();

    public WrappedListResponse() { }

    public WrappedListResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public List<T> getData() { return data; }

    public void setData(List<T> data) { this.data = data; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }
}
