package com.starla.fendapbengkulu.converter;

import com.google.gson.annotations.SerializedName;

public class WrappedResponse<T> {
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private int status;
    @SerializedName("data")
    private T data;

    public WrappedResponse() { }

    public WrappedResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public T getData() { return data; }

    public void setData(T data) { this.data = data; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }
}
