package cn.itcast.dtx.notifymsg.pay.service;

import cn.itcast.dtx.notifymsg.pay.entity.AccountPay;

public interface AccountPayService {
    //充值
    public AccountPay insertAccountPay(AccountPay accountPay);
    //查询充值结果
    public AccountPay getAccountPay(String txNo);
}
