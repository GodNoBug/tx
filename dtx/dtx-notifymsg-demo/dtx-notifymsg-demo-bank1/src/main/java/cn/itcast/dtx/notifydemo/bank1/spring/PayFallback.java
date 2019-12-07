package cn.itcast.dtx.notifydemo.bank1.spring;

import cn.itcast.dtx.notifydemo.bank1.entity.AccountPay;
import org.springframework.stereotype.Component;

@Component
public class PayFallback implements PayClient {
    @Override
    public AccountPay queryPayResult(String txNo) {
        AccountPay accountPay = new AccountPay();
        accountPay.setResult("fail");
        return accountPay;
    }
}
