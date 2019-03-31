package com.bolo.demo.dao.entitys;

public class Fruit {
    /**
     * id
     */
    private int id;

    /**
     * 学名
     */
    private String sciName;

    /**
     * 种类
     */
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
