package cn.itcast.dtx.notifydemo.bank1.service;

import cn.itcast.dtx.notifydemo.bank1.entity.AccountPay;
import cn.itcast.dtx.notifydemo.bank1.model.AccountChangeEvent;

public interface AccountInfoService {
    //更新账户金额
    public void updateAccountBalance(AccountChangeEvent accountChange);
    //查询充值结果(远程调用)
    public AccountPay queryPayResult(String tx_no);
}
