package com.example.exam;

public class objectlist {
    private int count;
    private int imgDrs;
    private String imgNames;

    public objectlist(int count, int imgDrs, String imgNames) {
        this.count = count;
        this.imgDrs = imgDrs;
        this.imgNames = imgNames;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getImgDrs() {
        return imgDrs;
    }

    public void setImgDrs(int imgDrs) {
        this.imgDrs = imgDrs;
    }

    public String getImgNames() {
        return imgNames;
    }

    public void setImgNames(String imgNames) {
        this.imgNames = imgNames;
    }
}
