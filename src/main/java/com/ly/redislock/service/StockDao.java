package com.ly.redislock.service;

import org.springframework.stereotype.Component;

@Component
public  class StockDao {

    private Integer num = 100;

    public Integer getNum() {
        return num;
    }

    public void decNum(Integer num) {
        this.num = num - 1;
        System.out.println("扣减后商品数量：" + this.num);
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
