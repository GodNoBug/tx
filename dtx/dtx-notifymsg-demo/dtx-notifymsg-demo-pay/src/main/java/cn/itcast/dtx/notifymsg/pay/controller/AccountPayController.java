package cn.itcast.dtx.notifymsg.pay.controller;

import cn.itcast.dtx.notifymsg.pay.entity.AccountPay;
import cn.itcast.dtx.notifymsg.pay.service.AccountPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AccountPayController {
    @Autowired
    AccountPayService accountPayService;

    //充值,浏览器发起一次充值请求 http://localhost:56082/pay/paydo?accountNo=1&payAmount=2
    @GetMapping(value = "/paydo")
    public AccountPay pay(AccountPay accountPay) {
        //生成事务编号
        String txNo = UUID.randomUUID().toString();
        accountPay.setId(txNo);
        return accountPayService.insertAccountPay(accountPay);
    }
    //查询充值结果
    @GetMapping(value = "/payresult/{txNo}")
    public AccountPay payresult(@PathVariable("txNo") String txNo) {
        return accountPayService.getAccountPay(txNo);
    }
}
