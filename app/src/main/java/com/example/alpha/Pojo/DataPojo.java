package com.example.alpha.Pojo;

public class DataPojo {

    private String msg;
    private String receiveOrSend;

    public DataPojo() {}

    public DataPojo(String msg, String receiveOrSend) {
        this.msg = msg;
        this.receiveOrSend = receiveOrSend;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getReceiveOrSend() {
        return receiveOrSend;
    }

    public void setReceiveOrSend(String receiveOrSend) {
        this.receiveOrSend = receiveOrSend;
    }
}
