package cn.itcast.dtx.notifymsg.pay.service.impl;

import cn.itcast.dtx.notifymsg.pay.dao.AccountPayDao;
import cn.itcast.dtx.notifymsg.pay.entity.AccountPay;
import cn.itcast.dtx.notifymsg.pay.service.AccountPayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountPayServiceImpl implements AccountPayService {
    @Autowired
    private AccountPayDao accountPayDao;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Override
    public AccountPay insertAccountPay(AccountPay accountPay) {
        int success = accountPayDao.insertAccountPay(accountPay.getId(), accountPay.getAccountNo(), accountPay.getPayAmount(), "success");
        if (success>0){
            //发送通知,使用普通消息发送通知,TODO 注释掉这段代码,测试主动查询 http://localhost:56081/bank1/payresult/事务id
            accountPay.setResult("success");
            rocketMQTemplate.convertAndSend("topic_notifymsg",accountPay);
            return accountPay;
        }
        return null;
    }

    //查询充值记录，接收通知方调用此方法来查询充值结果
    @Override
    public AccountPay getAccountPay(String txNo) {
        return accountPayDao.findByIdTxNo(txNo);
    }
}
