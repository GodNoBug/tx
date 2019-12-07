package cn.itcast.dtx.tccdemo.bank1.controller;

import cn.itcast.dtx.tccdemo.bank1.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Bank1Controller {
    @Autowired
    AccountInfoService accountInfoService;

    @RequestMapping("/transfer")
    public Boolean transfer(@RequestParam("amount") Double amount) {
        this.accountInfoService.updateAccountBalance("1", amount);
        return true;
    }

}

