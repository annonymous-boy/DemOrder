package com.pear.testdemo.bean;

public class Item {

    String name, qnty;

    public Item(String name, String qnty) {
        this.name = name;
        this.qnty = qnty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQnty() {
        return qnty;
    }

    public void setQnty(String qnty) {
        this.qnty = qnty;
    }
}
