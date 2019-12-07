package cn.itcast.seatademo.bank2.controller;

import cn.itcast.seatademo.bank2.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Bank2Controller {
    @Autowired
    AccountInfoService accountInfoService;
    @GetMapping("/transfer")
    public String transfer(Double amount){
        accountInfoService.updateAccountBalance("2",amount);
        return "bank2"+amount;
    }
}
