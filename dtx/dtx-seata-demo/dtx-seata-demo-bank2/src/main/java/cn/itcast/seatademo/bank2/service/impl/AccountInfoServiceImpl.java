package cn.itcast.seatademo.bank2.service.impl;

import cn.itcast.seatademo.bank2.dao.AccountInfoDao;
import cn.itcast.seatademo.bank2.service.AccountInfoService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AccountInfoServiceImpl implements AccountInfoService {
    @Autowired
    private AccountInfoDao accountInfoDao;
    //李四增加金额

    @Transactional
    @Override
    public void updateAccountBalance(String accountNo, Double amount) {
        log.info("bank2 service begin, XID:{}",RootContext.getXID());
        accountInfoDao.updateAccountBalance(accountNo,amount);
        //制造异常
        if(amount==3){
            throw new RuntimeException("bank1 make exception 2");
        }
    }
}
