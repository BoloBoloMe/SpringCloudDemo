package com.bolo.demo.entity.req;

public class QueryFruitReq {
    /**
     * 学名
     */
    String sciName;
    /**
     * 种类
     */
    String type;

    public String getSciName() {
        return sciName;
    }

    public void setSciName(String sciName) {
        this.sciName = sciName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "QueryFruitReq{" +
                "sciName='" + sciName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
