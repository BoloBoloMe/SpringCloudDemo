package com.bolo.demo.entity.resp;

import com.bolo.demo.entity.pojo.*;

import java.util.List;

public class QueryFruitResp extends BaseResp {
    /**
     * 数量
     */
    private int size;

    /**
     * 水果集合
     */
    private List<FruitPojo> pojos;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<FruitPojo> getPojos() {
        return pojos;
    }

    public void setPojos(List<FruitPojo> pojos) {
        this.pojos = pojos;
    }
}
