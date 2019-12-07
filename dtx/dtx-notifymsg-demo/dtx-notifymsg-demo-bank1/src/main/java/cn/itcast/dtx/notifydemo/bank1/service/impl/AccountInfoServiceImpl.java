package cn.itcast.dtx.notifydemo.bank1.service.impl;

import cn.itcast.dtx.notifydemo.bank1.dao.AccountInfoDao;
import cn.itcast.dtx.notifydemo.bank1.entity.AccountPay;
import cn.itcast.dtx.notifydemo.bank1.model.AccountChangeEvent;
import cn.itcast.dtx.notifydemo.bank1.service.AccountInfoService;
import cn.itcast.dtx.notifydemo.bank1.spring.PayClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AccountInfoServiceImpl implements AccountInfoService {
    @Autowired
    private AccountInfoDao accountInfoDao;
    @Autowired
    private PayClient payClient;
    //监听服务来调用
    @Transactional
    @Override
    public void updateAccountBalance(AccountChangeEvent accountChange) {
        //幂等校验
        if (accountInfoDao.isExistTx(accountChange.getTxNo())>0){
            return;
        }
        int i = accountInfoDao.updateAccountBalance(accountChange.getAccountNo(), accountChange.getAmount());
        //插入事务记录用于幂等控制
        accountInfoDao.addTx(accountChange.getTxNo());
    }
    //主动查询充值结果
    @Override
    public AccountPay queryPayResult(String tx_no) {
        AccountPay payResult= payClient.queryPayResult(tx_no);
        if ("success".equals(payResult.getResult())){
            //更新账户余额
            AccountChangeEvent accountChangeEvent=new AccountChangeEvent();
            accountChangeEvent.setAccountNo(payResult.getAccountNo());//账号
            accountChangeEvent.setAmount(payResult.getPayAmount());//金额
            accountChangeEvent.setTxNo(payResult.getId());//充值事务号
            updateAccountBalance(accountChangeEvent);
        }
        return payResult;
    }
}
