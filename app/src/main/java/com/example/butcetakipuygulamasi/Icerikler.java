package com.example.butcetakipuygulamasi;

public class Icerikler {
    private String icerikIsim;
    private int icerikImg;

    public Icerikler(String icerikIsim, int icerikImg) {
        this.icerikIsim = icerikIsim;
        this.icerikImg = icerikImg;
    }

    public String getIcerikIsim() {
        return icerikIsim;
    }

    public void setIcerikIsim(String icerikIsim) {
        this.icerikIsim = icerikIsim;
    }

    public int getIcerikImg() {
        return icerikImg;
    }

    public void setIcerikImg(int icerikImg) {
        this.icerikImg = icerikImg;
    }
}