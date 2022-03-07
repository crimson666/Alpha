package com.example.alpha.Pojo;

import android.graphics.Bitmap;

public class DataPojo {

    private String msg;
    private String receiveOrSend;
    private Bitmap imgBitmap;

    public DataPojo() {
    }

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

    public Bitmap getImgBitmap() {
        return imgBitmap;
    }

    public void setImgBitmap(Bitmap imgBitmap) {
        this.imgBitmap = imgBitmap;
    }

    public String getReceiveOrSend() {
        return receiveOrSend;
    }

    public void setReceiveOrSend(String receiveOrSend) {
        this.receiveOrSend = receiveOrSend;
    }
}
