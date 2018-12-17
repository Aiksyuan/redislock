package com.ly.redislock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockService {
    @Autowired
    private LockService lockService;
    @Autowired
    private StockDao stock;
    private static final String key = "tc_decStock";

    public String decStock() throws Exception {
        try {
            String value = System.currentTimeMillis() + 500 + "";
            boolean lock = lockService.lock(key, value);
            Integer num = stock.getNum();
            if (num <= 0) {
                System.out.println("该商品已售完！");
                lockService.unLook(key, value);
                return "-1";
            }
            if (lock) {
                stock.decNum(num);
                lockService.unLook(key, value);
                return "1";
            }else{
                System.out.println("未获得锁，请稍后！");
                return "0";
            }
        } catch (Exception e) {
            System.out.println("异常"+e);
            throw new Exception(e);
        }
    }

    public String resetStock() {
        try {
            stock.setNum(100);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

}