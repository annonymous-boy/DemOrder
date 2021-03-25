package com.pear.testdemo.bean;

import com.pear.testdemo.bean.Item;

import java.util.ArrayList;

public class RestBean {

    String id,tmStp,resNm,resImg,resLoc,resPrc;
    ArrayList<Item> items;

    public RestBean(String id, String tmStp, String resNm, String resImg, String resLoc, String resPrc, ArrayList<Item> items) {
        this.id = id;
        this.tmStp = tmStp;
        this.resNm = resNm;
        this.resImg = resImg;
        this.resLoc = resLoc;
        this.resPrc = resPrc;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTmStp() {
        return tmStp;
    }

    public void setTmStp(String tmStp) {
        this.tmStp = tmStp;
    }

    public String getResNm() {
        return resNm;
    }

    public void setResNm(String resNm) {
        this.resNm = resNm;
    }

    public String getResImg() {
        return resImg;
    }

    public void setResImg(String resImg) {
        this.resImg = resImg;
    }

    public String getResLoc() {
        return resLoc;
    }

    public void setResLoc(String resLoc) {
        this.resLoc = resLoc;
    }

    public String getResPrc() {
        return resPrc;
    }

    public void setResPrc(String resPrc) {
        this.resPrc = resPrc;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
