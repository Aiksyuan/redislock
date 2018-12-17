package com.ly.redislock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping("/decStock")
    public String decStock() throws Exception {
        System.out.println("++++++++++++++++++++++++++接口开始+++++++++++++++++++++++++");
        long start = System.currentTimeMillis();
        String s = stockService.decStock();
        System.out.println("接口返回：" + s + "接口耗时：" + (System.currentTimeMillis() - start));
        System.out.println("++++++++++++++++++++++++++接口结束+++++++++++++++++++++++++");
        return s;
    }
    @GetMapping("/resetStock")
    public String resetStock() {
        stockService.resetStock();
        return stockService.resetStock();
    }
}
